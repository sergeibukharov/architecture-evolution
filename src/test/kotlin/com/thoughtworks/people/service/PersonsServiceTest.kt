package com.thoughtworks.people.service

import com.thoughtworks.people.fake.InMemoryRepository
import com.thoughtworks.people.model.Person
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

internal class PersonsServiceTest {

    @Test
    fun `me should return Sergey person`() {
        val sergey = Person(
                id = UUID.fromString("29f4d7e3-fd7c-4664-ad07-763326215ec4"),
                firstName = "Sergey",
                secondName = "Bukharov",
                birthDate = LocalDate.of(1987,12,1),
                sex = Person.Sex.MAN,
                avatartUrl = "https://avatars.dicebear.com/v2/male/my-somffething.svg",
                favoriteQuote = "make the easy things easy, and the hard things possible"
        )
        val service = PersonsService(InMemoryRepository())
        assertEquals(
                sergey,
                service.me()
        )
    }

    @Test
    fun `when new person is created it can be found`() {
        val trump = PersonInput(
                firstName = "Donald",
                secondName = "Trump",
                birthDate = "1946-06-16",
                gender = "male"
        )

        val service = PersonsService(InMemoryRepository())
        val savedTrump = service.createNewPerson(trump)
        assertNotNull(service.get(savedTrump.id))
    }
}