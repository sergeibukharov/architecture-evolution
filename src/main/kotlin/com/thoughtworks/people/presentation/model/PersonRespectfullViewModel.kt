package com.thoughtworks.people.presentation.model

import com.thoughtworks.people.model.Person
import java.time.LocalDate
import java.time.Period

class PersonRespectfullViewModel(
        val person: Person
) {

    fun title() =
            "${prefixIfNeeded()} ${person.firstName} ${person.secondName}"

    private fun prefixIfNeeded() =
            if (ageYears() > 40)
                when (person.sex) {
                    Person.Sex.MAN -> "Mr"
                    Person.Sex.WOMAN -> "Mrs"
                }
            else ""

    private fun ageYears() = Period.between(person.birthDate, LocalDate.now()).years

    fun avatarUrl() = person.avatartUrl

    fun birthDate() = "${person.birthDate.dayOfMonth} ${person.birthDate.month} ${person.birthDate.year}"

    fun favoriteQuote() = person.favoriteQuote
}