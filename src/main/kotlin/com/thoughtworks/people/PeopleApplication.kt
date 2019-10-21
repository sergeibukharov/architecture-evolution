package com.thoughtworks.people

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PeopleApplication

fun main(args: Array<String>) {
	runApplication<PeopleApplication>(*args)
}
