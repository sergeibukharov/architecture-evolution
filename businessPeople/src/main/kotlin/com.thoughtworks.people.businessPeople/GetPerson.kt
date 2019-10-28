package com.thoughtworks.people.businessPeople

import java.util.*

interface GetPerson {

    fun get(id: UUID): Person?
}