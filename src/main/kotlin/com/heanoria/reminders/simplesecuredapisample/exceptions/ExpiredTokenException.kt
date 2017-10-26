package com.heanoria.reminders.simplesecuredapisample.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.FORBIDDEN, reason = "Token has been expired")
class ExpiredTokenException(mess:String?) : Exception(mess)