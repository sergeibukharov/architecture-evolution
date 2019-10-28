package com.thoughtworks.people.persistance.repository

import com.thoughtworks.people.persistance.model.PersonEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface PersonRepository: CrudRepository<PersonEntity, UUID>