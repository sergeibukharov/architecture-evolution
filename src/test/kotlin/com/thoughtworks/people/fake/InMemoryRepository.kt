package com.thoughtworks.people.fake

import com.thoughtworks.people.persistence.model.PersonEntity
import com.thoughtworks.people.persistence.repository.PersonRepository
import java.util.*

class InMemoryRepository: PersonRepository  {

    private val storage: MutableMap<UUID, PersonEntity> = mutableMapOf()

    override fun <S : PersonEntity> save(entity: S): S {
        storage[entity.id] = entity
        return entity
    }

    override fun findAll(): MutableIterable<PersonEntity> =
        storage.values

    override fun deleteById(id: UUID) {
        storage.remove(id)
    }

    override fun deleteAll(entities: MutableIterable<PersonEntity>) {
        entities.forEach{ person ->  storage.remove(person.id)}
    }

    override fun deleteAll() {
        storage.clear()
    }

    override fun <S : PersonEntity> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        entities.forEach { storage[it.id] = it }
        return entities
    }

    override fun count(): Long =
            storage.size.toLong()

    override fun findAllById(ids: MutableIterable<UUID>): MutableIterable<PersonEntity> =
            storage
                    .filterKeys { it in ids }
                    .values
                    .toMutableList()

    override fun existsById(id: UUID): Boolean =
            storage.containsKey(id)

    override fun findById(id: UUID): Optional<PersonEntity> =
        Optional.ofNullable(storage[id])

    override fun delete(entity: PersonEntity) {
        storage.remove(entity.id)
    }

}
