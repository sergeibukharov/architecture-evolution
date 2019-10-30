package com.thoughtworks.people.businessPeople.avatars

import com.thoughtworks.people.businessPeople.AvatarProvider
import com.thoughtworks.people.businessPeople.Person

class StaticImageAvatarProvider: AvatarProvider {

    override fun createForPerson(person: Person) =
            "https://avatars.dicebear.com/v2/bottts/not%20found.svg"

}