package com.thoughtworks.people.useCasePeople

import com.thoughtworks.people.businessPeople.GetPerson
import com.thoughtworks.people.businessPeople.PersistPerson
import com.thoughtworks.people.businessPeople.Person
import java.time.LocalDate
import java.util.*
import javax.inject.Named

@Named
class PersonsService(
        private val getPerson: GetPerson,
        private val persistPerson: PersistPerson
) {

    fun me(): Person {
        val me = Person(
                id = UUID.fromString("29f4d7e3-fd7c-4664-ad07-763326215ec4"),
                firstName = "Sergey",
                secondName = "Bukharov",
                birthDate = LocalDate.of(1987,12,1),
                sex = Person.Sex.MAN,
                avatartUrl = "https://avatars.dicebear.com/v2/male/my-somffething.svg",
                favoriteQuote = "make the easy things easy, and the hard things possible"
        )
        persistPerson.persist(me)
        return me
    }

    fun get(id: UUID): Person? = getPerson.get(id)

    fun createNewPerson(personInput: PersonCreationSummary): Person {
        val inputSex = when(personInput.gender.toLowerCase()) {
            "male" -> Person.Sex.MAN
            "female" -> Person.Sex.WOMAN
            else -> Person.Sex.MAN
        }

        val generatedPerson = Person(
                firstName = personInput.firstName,
                secondName = personInput.secondName,
                birthDate = LocalDate.parse(personInput.birthDate),
                sex = inputSex,
                avatartUrl = GeneratedAvatar(
                        sex = inputSex,
                        uniqueValue = personInput.firstName + personInput.secondName).toUrl(),
                favoriteQuote = GeneratedQuote().get()
        )

        persistPerson.persist(generatedPerson)
        return generatedPerson
    }

}

data class PersonCreationSummary(
        val firstName: String,
        val secondName: String,
        val birthDate: String,
        val gender: String
)