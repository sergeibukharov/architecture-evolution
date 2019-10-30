package com.thoughtworks.people.businessPeople

interface AvatarProvider {
    fun createForPerson(person: Person): String
}
