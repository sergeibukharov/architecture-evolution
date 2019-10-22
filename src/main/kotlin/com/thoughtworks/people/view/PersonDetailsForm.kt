package com.thoughtworks.people.view

import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.lang.StringBuilder

fun personDetailsForm(): String =
    StringBuilder()
            .appendHTML()
            .html {
                bootstrapHeader()
                body {
                    div(classes = "header") {
                        h1 {+ "Create a new person"}
                    }
                    div("body") {
                        form(action = "/generate", method = FormMethod.post) {
                            p { +"First name " }; textInput(name = "firstName")
                            p { +"Last name " }; textInput(name = "secondName")
                            p { +"Birthdate " }; dateInput(name = "birthDate")
                            p { +"Gender " }; textInput(name = "gender")
                            p { + "" }; submitInput()
                        }
                    }
                }
            }.toString()