package com.thoughtworks.people.presentation.model

import com.thoughtworks.people.business.Person
import com.thoughtworks.people.persistence.model.PersonEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class PersonRespectfullyViewModelTest {

    @Test
    fun `when user is young than name prefix should be avoid`() {
        val hueyDuck = PersonRespectfullViewModel(
                Person(
                    firstName = "Huey",
                    secondName = "Duck",
                    birthDate = LocalDate.of(2000,1,1),
                    sex = Person.Sex.MAN,
                    avatartUrl = "https://avatars.dicebear.com/v2/male/huey-duck.svg",
                    favoriteQuote = "who knows?"
                )
        )

        assertFalse(hueyDuck.title().contains("Mr"))
        assertFalse(hueyDuck.title().contains("Mrs"))
    }

    @Test
    fun `when user is amateur man than name prefix Mr should be present`() {
        val donaldDuck = PersonRespectfullViewModel(
                Person(
                        firstName = "Donald",
                        secondName = "Duck",
                        birthDate = LocalDate.of(1956,1,1),
                        sex = Person.Sex.MAN,
                        avatartUrl = "https://avatars.dicebear.com/v2/male/donald-duck.svg",
                        favoriteQuote = "$$$"
                )
        )

        assertTrue(donaldDuck.title().contains("Mr"))
    }

    @Test
    fun `when user is amateur woman than name prefix Mrs should be present`() {
        val duck = PersonRespectfullViewModel(
                Person(
                        firstName = "Donna",
                        secondName = "Duck",
                        birthDate = LocalDate.of(1956,1,1),
                        sex = Person.Sex.WOMAN,
                        avatartUrl = "https://avatars.dicebear.com/v2/male/donald-duck.svg",
                        favoriteQuote = "$$$"
                )
        )

        assertTrue(duck.title().contains("Mrs"))
    }

    @Test
    fun `person birthdate should be displayed in format d MONTH yyyy`() {
        val duck = PersonRespectfullViewModel(
                Person(
                        firstName = "Donna",
                        secondName = "Duck",
                        birthDate = LocalDate.of(1956,1,1),
                        sex = Person.Sex.WOMAN,
                        avatartUrl = "https://avatars.dicebear.com/v2/male/donald-duck.svg",
                        favoriteQuote = "$$$"
                )
        )

        assertEquals("1 JANUARY 1956", duck.birthDate())
    }
}