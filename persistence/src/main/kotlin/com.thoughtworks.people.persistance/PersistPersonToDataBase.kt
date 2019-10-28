package com.thoughtworks.people.persistance

import com.thoughtworks.people.businessPeople.PersistPerson
import com.thoughtworks.people.businessPeople.Person
import com.thoughtworks.people.persistance.model.PersonEntity
import com.thoughtworks.people.persistance.repository.PersonRepository
import org.springframework.stereotype.Component

@Component
class PersistPersonToDataBase(
        private val repository: PersonRepository
): PersistPerson {
    override fun persist(person: Person) {
        repository
                .save(PersonEntity.fromBusiness(person))
                .let { PersonEntity.toBusiness(it) }
    }
}