package com.iqmsoft.demo

import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.*

@Entity 
@Table(name = "customers")
class Customers{

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO) 
    @Column(name = "id")
    var id:Int = 0

    @Column(name = "name")
    var name : String? = null

    @Column(name = "address")
    var address : String? = null

    @Column(name = "city")
    var city : String? = null

    @Column(name = "age")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    var age : Int = 0

    @Column(name = "love")
    var love : String? = null
}