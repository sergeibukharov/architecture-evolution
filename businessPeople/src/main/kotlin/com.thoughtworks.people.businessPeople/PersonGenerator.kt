package com.thoughtworks.people.businessPeople

import java.time.LocalDate
import javax.inject.Named

@Named
class PersonGenerator(
        private val quoteProvider: QuotesProvider
) {

    fun generate(
            firstName: String,
            secondName: String,
            birthDate: LocalDate,
            sex: Person.Sex
    ): Person =
            Person(
                    firstName = firstName,
                    secondName = secondName,
                    birthDate = birthDate,
                    sex = sex,
                    favoriteQuote = quoteProvider.randomQuote(),
                    avatartUrl = ""
            )
}