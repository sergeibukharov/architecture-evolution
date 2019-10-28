package com.thoughtworks.people

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(value = ["com.thoughtworks.people"])
class PeopleApplication

fun main(args: Array<String>) {
	runApplication<PeopleApplication>(*args)
}
