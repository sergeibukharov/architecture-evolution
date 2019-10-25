package com.thoughtworks.people.business

import java.time.LocalDate
import java.time.Period
import java.util.*

data class Person(
        val id: UUID = UUID.randomUUID(),
        val firstName: String,
        val secondName: String,
        val birthDate: LocalDate,
        val sex: Sex,
        val avatartUrl: String,
        val favoriteQuote: String
) {

    fun mature(forDate: LocalDate = LocalDate.now()): Boolean =
            age(forDate) > 40


    fun age(forDate: LocalDate = LocalDate.now()): Year =
            Period.between(birthDate, forDate).years

    enum class Sex {
        MAN, WOMAN
    }
}

typealias Year = Int