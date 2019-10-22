package com.thoughtworks.people.controller

import com.thoughtworks.people.model.Person
import com.thoughtworks.people.repository.PersonRepository
import com.thoughtworks.people.utils.GeneratedAvatar
import com.thoughtworks.people.utils.GeneratedQuote
import com.thoughtworks.people.utils.toNullable
import com.thoughtworks.people.view.personDetailsForm
import com.thoughtworks.people.view.renderDetailedView
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import java.net.URI
import java.time.LocalDate
import java.util.*

@Controller
class PeopleController(
        val personRepository: PersonRepository
) {

    @RequestMapping(value = ["/me"], method = [RequestMethod.GET])
    @ResponseBody
    fun me(): String {
        val me = Person(
                id = UUID.fromString("29f4d7e3-fd7c-4664-ad07-763326215ec4"),
                firstName = "Sergey",
                secondName = "Bukharov",
                birthDate = LocalDate.of(1987,12,1),
                sex = Person.Sex.MAN,
                avatartUrl = "https://avatars.dicebear.com/v2/male/my-somffething.svg",
                favoriteQuote = "make the easy things easy, and the hard things possible"
        )
        personRepository.save(me)
        return renderDetailedView(person = me)
    }

    @RequestMapping(value = ["/id/{id}"])
    fun get(@PathVariable id: String): ResponseEntity<String> {
        val idUUD = try {
            UUID.fromString(id)
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().build()
        }

        val person = personRepository
                .findById(idUUD).toNullable()
                ?: return ResponseEntity.badRequest().build()

        return ResponseEntity.ok(renderDetailedView(person))
    }

    @RequestMapping(value = ["/generate"], method = [RequestMethod.GET])
    @ResponseBody
    fun showCreationForm(): String {
        return personDetailsForm()
    }

    @RequestMapping(value = ["/generate"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE] )
    @ResponseBody
    fun create(personInput: PersonInput): ResponseEntity<String>{
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

        personRepository.save(generatedPerson)

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create("/id/${generatedPerson.id}"))
                .build()
    }

    data class PersonInput(
            val firstName: String,
            val secondName: String,
            val birthDate: String,
            val gender: String
    )
}