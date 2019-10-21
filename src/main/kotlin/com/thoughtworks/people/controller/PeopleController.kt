package com.thoughtworks.people.controller

import com.thoughtworks.people.model.Person
import com.thoughtworks.people.repository.PersonRepository
import com.thoughtworks.people.utils.GeneratedAvatar
import com.thoughtworks.people.utils.GeneratedQuote
import com.thoughtworks.people.view.renderDetailedView
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import java.time.LocalDate

@Controller
class PeopleController(
        val personRepository: PersonRepository
) {

    @RequestMapping(value = ["/me"], method = [RequestMethod.GET])
    @ResponseBody
    fun me(): String {
        val p = Person(
                firstName = "Sergey",
                secondName = "Bukharov",
                birthDate = LocalDate.of(1987,12,1),
                sex = Person.Sex.MAN,
                avatartUrl = "https://avatars.dicebear.com/v2/male/my-somffething.svg",
                favoriteQuote = "make the easy things easy, and the hard things possible"
        )

        personRepository.save(p)

        return renderDetailedView(person = p)
    }

    @RequestMapping(value = ["/generate"], method = [RequestMethod.GET])
    @ResponseBody
    fun showCreationForm(): String {
        return "hello"
    }

    @RequestMapping(value = ["/generate"], method = [RequestMethod.POST])
    @ResponseBody
    fun create(@RequestBody personInput: PersonInput): String {
        val inputSex = when(personInput.sex.toLowerCase()) {
            "man" -> Person.Sex.MAN
            "woman" -> Person.Sex.WOMAN
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

        return renderDetailedView(generatedPerson)
    }

    data class PersonInput(
            val firstName: String,
            val secondName: String,
            val birthDate: String,
            val sex: String
    )
}