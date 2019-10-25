package com.thoughtworks.people.fake

import com.thoughtworks.people.model.Person
import com.thoughtworks.people.repository.PersonRepository
import java.util.*

class InMemoryRepository: PersonRepository  {

    private val storage: MutableMap<UUID, Person> = mutableMapOf()

    override fun <S : Person> save(entity: S): S {
        storage[entity.id] = entity
        return entity
    }

    override fun findAll(): MutableIterable<Person> =
        storage.values

    override fun deleteById(id: UUID) {
        storage.remove(id)
    }

    override fun deleteAll(entities: MutableIterable<Person>) {
        entities.forEach{ person ->  storage.remove(person.id)}
    }

    override fun deleteAll() {
        storage.clear()
    }

    override fun <S : Person> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        entities.forEach { storage[it.id] = it }
        return entities
    }

    override fun count(): Long =
            storage.size.toLong()

    override fun findAllById(ids: MutableIterable<UUID>): MutableIterable<Person> =
            storage
                    .filterKeys { it in ids }
                    .values
                    .toMutableList()

    override fun existsById(id: UUID): Boolean =
            storage.containsKey(id)

    override fun findById(id: UUID): Optional<Person> =
        Optional.ofNullable(storage[id])

    override fun delete(entity: Person) {
        storage.remove(entity.id)
    }

}
