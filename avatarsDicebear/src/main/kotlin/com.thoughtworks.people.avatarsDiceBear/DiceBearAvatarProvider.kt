package com.thoughtworks.people.avatarsDiceBear

import com.thoughtworks.people.businessPeople.AvatarProvider
import com.thoughtworks.people.businessPeople.Person
import javax.inject.Named

@Named
class DiceBearAvatarProvider: AvatarProvider {
    override fun createForPerson(person: Person): String {
        val uniqueValue = person.firstName + person.secondName
        return "https://avatars.dicebear.com/v2/${if (person.sex == Person.Sex.MAN) "male" else "female"}/$uniqueValue.svg"
    }

}