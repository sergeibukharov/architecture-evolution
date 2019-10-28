package com.thoughtworks.people.persistance.model

import com.thoughtworks.people.businessPeople.Person
import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class PersonEntity(
        @Id val id: UUID = UUID.randomUUID(),
        val firstName: String,
        val secondName: String,
        val birthDate: LocalDate,
        val sex: Sex,
        val avatartUrl: String,
        val favoriteQuote: String
) {
    enum class Sex {
        MAN, WOMAN
    }

    companion object {
        fun fromBusiness(p: Person): PersonEntity =
                PersonEntity(
                        id = p.id,
                        firstName = p.firstName,
                        secondName = p.secondName,
                        birthDate = p.birthDate,
                        sex = if (p.sex == Person.Sex.MAN) Sex.MAN else Sex.WOMAN,
                        avatartUrl = p.avatartUrl,
                        favoriteQuote = p.favoriteQuote
                )

        fun toBusiness(p: PersonEntity): Person =
                Person(
                        id = p.id,
                        firstName = p.firstName,
                        secondName = p.secondName,
                        birthDate = p.birthDate,
                        sex = if (p.sex == Sex.MAN) Person.Sex.MAN else Person.Sex.WOMAN,
                        avatartUrl = p.avatartUrl,
                        favoriteQuote = p.favoriteQuote
                )
    }
}