package com.iqmsoft.demo

import org.springframework.data.jpa.repository.JpaRepository

interface CustomersRepository : JpaRepository<Customers,String>