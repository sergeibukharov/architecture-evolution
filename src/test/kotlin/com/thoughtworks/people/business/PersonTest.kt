package com.thoughtworks.people.business

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class PersonTest {

    @Test
    fun `age should be calculated properly`() {
        val duck = Person(
                firstName = "Donna",
                secondName = "Duck",
                birthDate = LocalDate.of(1956,1,1),
                sex = Person.Sex.WOMAN,
                avatartUrl = "https://avatars.dicebear.com/v2/male/donald-duck.svg",
                favoriteQuote = "$$$"
        )

        assertEquals(
                63,
                duck.age(LocalDate.of(2019, 12, 1))
        )
    }

    @Test
    fun `person is be mature if he is more 40 years old`() {
        val duck = Person(
                firstName = "Donna",
                secondName = "Duck",
                birthDate = LocalDate.of(1956,1,1),
                sex = Person.Sex.WOMAN,
                avatartUrl = "https://avatars.dicebear.com/v2/male/donald-duck.svg",
                favoriteQuote = "$$$"
        )

        assertTrue(duck.mature())
    }
}