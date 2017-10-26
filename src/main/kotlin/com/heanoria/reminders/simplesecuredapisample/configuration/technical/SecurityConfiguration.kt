package com.heanoria.reminders.simplesecuredapisample.configuration.technical

import com.heanoria.reminders.simplesecuredapisample.configuration.properties.KeyPairProperties
import com.heanoria.reminders.simplesecuredapisample.configuration.properties.TokenProperties
import com.heanoria.reminders.simplesecuredapisample.security.*
import com.heanoria.reminders.simplesecuredapisample.services.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.security.KeyPair

@Configuration
@EnableWebSecurity
class SecurityConfiguration(private val userService: UserService) : WebSecurityConfigurerAdapter() {

    private val LOGIN_URL_PATTERN = "/v1/login"
    private lateinit var statelessAuthenticationFilter: StatelessAuthenticationFilter
    private lateinit var statelessLoginFilter : StatelessLoginFilter

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()?.exceptionHandling()?.and()?.anonymous()?.and()?.servletApi()?.and()?.authorizeRequests()
                ?.antMatchers(HttpMethod.POST, LOGIN_URL_PATTERN)?.permitAll()?.and()
                ?.addFilterBefore(statelessLoginFilter, UsernamePasswordAuthenticationFilter::class.java)
                ?.headers()?.cacheControl()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userService)?.passwordEncoder(BCryptPasswordEncoder())
    }

    @Bean
    fun tokenHandler(keiPair: KeyPair, userService: UserService, tokenProperties: TokenProperties) = TokenHandler(keiPair, userService, tokenProperties)

    @Bean
    fun tokenAuthenticationService(tokenHandler: TokenHandler) = TokenAuthenticationService(tokenHandler)

    @Bean
    fun statelessAuthenticationFilter(tokenAuthenticationService: TokenAuthenticationService) : StatelessAuthenticationFilter {
        this.statelessAuthenticationFilter = StatelessAuthenticationFilter(tokenAuthenticationService, authenticationManager())
        return this.statelessAuthenticationFilter
    }

    @Bean
    fun statelessLoginFilter(userService: UserService, tokenAuthenticationService: TokenAuthenticationService) : StatelessLoginFilter {
        this.statelessLoginFilter = StatelessLoginFilter(LOGIN_URL_PATTERN, authenticationManager(), userService, tokenAuthenticationService)
        return this.statelessLoginFilter
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

    @Bean
    fun keyPairHandler(keyPairProperties: KeyPairProperties) = KeyPairHandler(keyPairProperties).loadKeyPair()
}