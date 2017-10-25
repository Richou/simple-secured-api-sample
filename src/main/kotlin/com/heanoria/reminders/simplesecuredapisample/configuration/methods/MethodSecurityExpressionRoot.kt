package com.heanoria.reminders.simplesecuredapisample.configuration.methods

import org.springframework.security.access.expression.SecurityExpressionRoot
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations
import org.springframework.security.core.Authentication


class MethodSecurityExpressionRoot(authentication: Authentication?) : SecurityExpressionRoot(authentication), MethodSecurityExpressionOperations {

    private var filterObject: Any? = null
    private var returnObject: Any? = null
    private var target: Any? = null

    override fun setReturnObject(returnObject: Any?) {
        this.returnObject = returnObject
    }

    override fun getFilterObject() = this.filterObject

    override fun setFilterObject(filterObject: Any?) {
        this.filterObject = filterObject
    }

    override fun getReturnObject() = this.returnObject

    fun setThis(target: Any?) {
        this.target = target
    }

    override fun getThis() = this.target

}