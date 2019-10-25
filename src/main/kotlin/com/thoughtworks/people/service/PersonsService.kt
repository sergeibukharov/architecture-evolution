package com.thoughtworks.people.service

import com.thoughtworks.people.business.Person
import com.thoughtworks.people.persistence.model.PersonEntity
import com.thoughtworks.people.persistence.repository.PersonRepository
import com.thoughtworks.people.utils.GeneratedAvatar
import com.thoughtworks.people.utils.GeneratedQuote
import com.thoughtworks.people.utils.toNullable
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class PersonsService(
        val repository: PersonRepository
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
        return repository
                .save(PersonEntity.fromBusiness(me))
                .let { PersonEntity.toBusiness(it) }
    }

    fun get(id: UUID): Person? =
            repository
            .findById(id)
                    .toNullable()
                    ?.let { PersonEntity.toBusiness(it) }

    fun createNewPerson(personInput: PersonInput): Person {
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

        return repository
                .save(PersonEntity.fromBusiness(generatedPerson))
                .let { PersonEntity.toBusiness(it) }
    }

}

data class PersonInput(
        val firstName: String,
        val secondName: String,
        val birthDate: String,
        val gender: String
)