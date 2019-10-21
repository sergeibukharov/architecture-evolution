package com.thoughtworks.people.view

import com.thoughtworks.people.model.Person
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.lang.StringBuilder
import java.time.LocalDate
import java.time.Period


fun renderDetailedView(person: Person): String =
    StringBuilder()
            .appendHTML()
            .html {
                bootstrapHeader()
                body {
                    div(classes = "header") {
                        h1 {
                            img(src = person.avatartUrl) { height = "48"; width = "48"}
                            + if (Period.between(LocalDate.now(), person.birthDate).years > 40) "Mr./Mrs. " else ""
                            + "${person.firstName} ${person.secondName}"
                        }
                    }
                    div("body") {
                        p { + "Birth date: ${person.birthDate.dayOfMonth} ${person.birthDate.month} ${person.birthDate.year}" }
                        p { + "Favorite quote: ${person.favoriteQuote}"}
                    }
                }
            }.toString()


