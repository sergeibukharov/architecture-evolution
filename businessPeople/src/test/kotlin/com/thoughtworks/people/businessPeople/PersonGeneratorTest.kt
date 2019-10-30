package com.thoughtworks.people.businessPeople

import com.thoughtworks.people.businessPeople.avatars.StaticImageAvatarProvider
import com.thoughtworks.people.businessPeople.quotes.EmptyQuoteProviders
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class PersonGeneratorTest {

    @Test
    fun `if input data is correct person should be generated propely`() {
        val firstName = "Donald"
        val secondName = "Duck"
        val birthDate = LocalDate.of(1956,1,1)
        val sex = Person.Sex.MAN

        val generator = PersonGenerator(EmptyQuoteProviders(), StaticImageAvatarProvider())
        val generatedDuck = generator.generate(
                firstName = firstName,
                secondName = secondName,
                birthDate = birthDate,
                sex = sex
        )

        Assertions.assertEquals(firstName, generatedDuck.firstName)
        Assertions.assertEquals(secondName, generatedDuck.secondName)
        Assertions.assertEquals(birthDate, generatedDuck.birthDate)
        Assertions.assertEquals(sex, generatedDuck.sex)
    }
}