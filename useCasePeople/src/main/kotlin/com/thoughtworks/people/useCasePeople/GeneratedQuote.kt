package com.thoughtworks.people.useCasePeople

const val defaultQuote = "whoops, something went wrong"

class GeneratedQuote {

    private val getRandomUrl = "https://quote-garden.herokuapp.com/quotes/random"

    fun get() = ""

//    fun get() = RestTemplate()
//            .getForEntity(getRandomUrl, QuoteResponse::class.java)
//            .body?.quoteText ?: defaultQuote

    internal data class QuoteResponse(
            val _id: String,
            val quoteText: String,
            val quoteAuthor: String
    )
}