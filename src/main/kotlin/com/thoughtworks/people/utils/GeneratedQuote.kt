package com.thoughtworks.people.utils

import org.springframework.web.client.RestTemplate

const val defaultQuote = "whoops, something went wrong"

class GeneratedQuote {

    private val getRandomUrl = "https://quote-garden.herokuapp.com/quotes/random"


    fun get() = RestTemplate()
            .getForEntity(getRandomUrl, QuoteResponse::class.java)
            .body?.quoteText ?: defaultQuote

    internal data class QuoteResponse(
            val _id: String,
            val quoteText: String,
            val quoteAuthor: String
    )
}