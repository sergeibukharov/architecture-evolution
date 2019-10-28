package com.thoughtworks.people.presentation.view

import kotlinx.html.HTML
import kotlinx.html.head
import kotlinx.html.link

fun HTML.bootstrapHeader() {
    head {
        link(rel = "stylesheet", href = "https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css")
    }
}