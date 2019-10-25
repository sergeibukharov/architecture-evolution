package com.thoughtworks.people.controller

import com.thoughtworks.people.service.PersonInput
import com.thoughtworks.people.service.PersonsService
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
import java.util.*

@Controller
class PeopleController(
        val personService: PersonsService
) {

    @RequestMapping(value = ["/me"], method = [RequestMethod.GET])
    @ResponseBody
    fun me(): String {
        val me = personService.me()
        return renderDetailedView(person = me)
    }

    @RequestMapping(value = ["/id/{id}"])
    fun get(@PathVariable id: String): ResponseEntity<String> {
        val idUUD = try {
            UUID.fromString(id)
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().build()
        }

        val person = personService.get(idUUD)
                ?: return ResponseEntity.badRequest().build()

        return ResponseEntity.ok(renderDetailedView(person))
    }

    @RequestMapping(value = ["/generate"], method = [RequestMethod.GET])
    @ResponseBody
    fun showCreationForm(): String {
        return personDetailsForm()
    }

    @RequestMapping(
            value = ["/generate"],
            method = [RequestMethod.POST],
            consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    @ResponseBody
    fun create(personInput: PersonInput): ResponseEntity<String>{
        val generatedPerson = personService.createNewPerson(personInput)

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create("/id/${generatedPerson.id}"))
                .build()
    }
}