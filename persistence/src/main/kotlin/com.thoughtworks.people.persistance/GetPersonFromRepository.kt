package com.thoughtworks.people.persistance

import com.thoughtworks.people.businessPeople.GetPerson
import com.thoughtworks.people.businessPeople.Person
import com.thoughtworks.people.persistance.model.PersonEntity
import com.thoughtworks.people.persistance.repository.PersonRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class GetPersonFromRepository(
        private val repository: PersonRepository
) : GetPerson {
    override fun get(id: UUID): Person? =
            repository
                    .findById(id)
                    .toNullable()
                    ?.let { PersonEntity.toBusiness(it) }

}