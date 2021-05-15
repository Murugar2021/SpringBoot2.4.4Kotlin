package com.iqmsoft.demo.controller

import com.iqmsoft.demo.Customers
import com.iqmsoft.demo.CustomersRepository
import com.iqmsoft.demo.core.JsonResult
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomersController{


    @Autowired 
    val customersRepository: CustomersRepository? = null

   
    @GetMapping("/queryAll")
    fun queryAll(): ResponseEntity<JsonResult> {
        val all = customersRepository?.findAll()

        return JsonResult(all!!).ok()

    }
    @RequestMapping("/addCustomer")
    fun addCustomer(name:String,address:String,city:String,age:Int,love:String):String{

        val customer = Customers()
        customer.name = name
        customer.address = address
        customer.city = city
        customer.age = age
        customer.love = love

        customersRepository!!.save(customer)
        return "Success"
    }
    @RequestMapping("/test")
    fun test():String{

        return "This is a Kotlin Test"
    }
}