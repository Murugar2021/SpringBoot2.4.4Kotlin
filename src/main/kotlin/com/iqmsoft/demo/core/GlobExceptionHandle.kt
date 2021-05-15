package com.iqmsoft.demo.core

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.NoHandlerFoundException


class ApiException(val code: ErrorCode, val data: Any? = null) : Exception(code.msg)

@ControllerAdvice
class GlobExceptionHandle  {

    private val LOG = LoggerFactory.getLogger(LogAspect::class.java)

    @ExceptionHandler(ApiException::class)
    fun handleControllerException( ex: ApiException) = JsonResult(ex.data).send(ex.code)

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNotFoundException(e: NoHandlerFoundException) : ResponseEntity<JsonResult> {
        LOG.info("handleNotFoundException----------------")
        val status = HttpStatus.NOT_FOUND

        return JsonResult().send(status.value(), e.message)
    }
    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingServletRequestParameterException(e: MissingServletRequestParameterException) : ResponseEntity<JsonResult> {
        val status = HttpStatus.BAD_REQUEST
        LOG.info("handleMissingServletRequestParameterException-------------------")
        return JsonResult().send(status.value(), e.message)
    }
}