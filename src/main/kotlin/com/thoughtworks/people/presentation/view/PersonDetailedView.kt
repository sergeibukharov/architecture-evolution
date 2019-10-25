package com.thoughtworks.people.presentation.view

import com.thoughtworks.people.presentation.model.PersonRespectfullViewModel
import kotlinx.html.*
import kotlinx.html.stream.appendHTML


fun renderDetailedView(person: PersonRespectfullViewModel): String =
    StringBuilder()
            .appendHTML()
            .html {
                bootstrapHeader()
                body {
                    div(classes = "header") {
                        h1 {
                            img(src = person.avatarUrl()) { height = "48"; width = "48"}
                            + person.title()
                        }
                    }
                    div("body") {
                        p { + "Birth date: ${person.birthDate()}" }
                        p { + "Favorite quote: ${person.favoriteQuote()}"}
                    }
                }
            }.toString()


