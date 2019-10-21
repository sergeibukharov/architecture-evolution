package com.thoughtworks.people.repository

import com.thoughtworks.people.model.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.Repository
import java.util.*

interface PersonRepository: CrudRepository<Person, UUID>