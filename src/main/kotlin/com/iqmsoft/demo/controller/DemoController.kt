package com.iqmsoft.demo.controller

import com.iqmsoft.demo.core.ApiException
import com.iqmsoft.demo.core.ErrorCode
import com.iqmsoft.demo.core.JsonResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class DemoController{


    @GetMapping("/hello")
    fun hello(@RequestParam(value = "name",defaultValue = "World")name:String): ResponseEntity<JsonResult> {
        if(name != "abc") throw ApiException(ErrorCode.ERROR_USER_PASSWORD)
        return JsonResult("hello $name").ok()
    }
}