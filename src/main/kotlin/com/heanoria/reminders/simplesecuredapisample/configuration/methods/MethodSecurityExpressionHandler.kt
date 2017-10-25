package com.heanoria.reminders.simplesecuredapisample.configuration.methods

import org.aopalliance.intercept.MethodInvocation
import org.springframework.expression.EvaluationContext
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations
import org.springframework.security.core.Authentication


class MethodSecurityExpressionHandler : DefaultMethodSecurityExpressionHandler() {
    override fun setReturnObject(returnObject: Any?, ctx: EvaluationContext?) {
        (ctx?.rootObject?.value as MethodSecurityExpressionRoot).returnObject = returnObject
    }

    override fun createSecurityExpressionRoot(authentication: Authentication?, invocation: MethodInvocation?): MethodSecurityExpressionOperations {
        val root = MethodSecurityExpressionRoot(authentication)
        root.`this` = invocation?.`this`
        root.setPermissionEvaluator(permissionEvaluator)
        root.setTrustResolver(trustResolver)
        root.setRoleHierarchy(roleHierarchy)
        return root
    }
}