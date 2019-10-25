package com.thoughtworks.people.persistence.repository

import com.thoughtworks.people.persistence.model.PersonEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface PersonRepository: CrudRepository<PersonEntity, UUID>