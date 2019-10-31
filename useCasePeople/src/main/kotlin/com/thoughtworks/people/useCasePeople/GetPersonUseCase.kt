package com.thoughtworks.people.useCasePeople

import com.thoughtworks.people.businessPeople.GetPerson
import com.thoughtworks.people.businessPeople.Person
import java.util.*
import javax.inject.Named

@Named
class GetPersonUseCase(
        private val getPerson: GetPerson
) {

    operator fun invoke(id: UUID): Person? = getPerson.get(id)
}