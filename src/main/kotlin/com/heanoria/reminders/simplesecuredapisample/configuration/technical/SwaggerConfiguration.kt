package com.heanoria.reminders.simplesecuredapisample.configuration.technical

import com.google.common.base.Predicates
import com.heanoria.reminders.simplesecuredapisample.configuration.properties.SwaggerProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration(val swaggerProperties: SwaggerProperties) {

    @Bean
    fun newsApi() : Docket {
        return Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
    }

    private fun apiInfo() : ApiInfo {
        return ApiInfoBuilder().title(swaggerProperties.title).description(swaggerProperties.description).termsOfServiceUrl(swaggerProperties.termsOfServiceUrl).license(swaggerProperties.license)
                .licenseUrl(swaggerProperties.licenseUrl).contact(Contact(swaggerProperties.contact, "", "")).version(swaggerProperties.version).build()
    }
}