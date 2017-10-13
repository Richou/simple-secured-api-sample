package com.heanoria.reminders.simplesecuredapisample.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNAUTHORIZED, reason = "Invalid Token Signature")
class InvalidTokenSignatureException : Exception()