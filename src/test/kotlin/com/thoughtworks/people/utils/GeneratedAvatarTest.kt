package com.thoughtworks.people.utils

import com.thoughtworks.people.businessPeople.Person
import com.thoughtworks.people.persistence.model.PersonEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GeneratedAvatarTest {

    @Test
    fun `when avatar generated for man it returns proper URL`() {
        assertEquals(
                "https://avatars.dicebear.com/v2/male/jondoe.svg",
                GeneratedAvatar(Person.Sex.MAN, "jondoe").toUrl()
        )
    }

    @Test
    fun `when avatar generated for female it returns proper URL`() {
        assertEquals(
                "https://avatars.dicebear.com/v2/female/alice.svg",
                GeneratedAvatar(Person.Sex.WOMAN, "alice").toUrl()
        )
    }
}