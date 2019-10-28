package com.thoughtworks.people.useCasePeople

import com.thoughtworks.people.businessPeople.Person
import java.util.*

class GeneratedAvatar(
        private val sex: Person.Sex,
        private val uniqueValue: String = UUID.randomUUID().toString()
) {
    fun toUrl(): String =
            "https://avatars.dicebear.com/v2/${if (sex == Person.Sex.MAN) "male" else "female"}/$uniqueValue.svg"

}