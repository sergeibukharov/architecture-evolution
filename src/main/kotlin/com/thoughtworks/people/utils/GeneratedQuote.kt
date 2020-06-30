package com.thoughtworks.people.utils

import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

const val defaultQuote = "whoops, something went wrong"

class GeneratedQuote {

    private val getRandomUrl = "https://quote-garden.herokuapp.com/quotes/random"

    private val httpRequestFactory = SimpleClientHttpRequestFactory()
            .also { it.setConnectTimeout(5000) }
            .also { it.setReadTimeout(5000) }

    fun get() =
            try {
                RestTemplate(httpRequestFactory)
                        .getForEntity(getRandomUrl, QuoteResponse::class.java)
                        .body?.quoteText ?: defaultQuote
            } catch (e: Exception) {
                defaultQuote
            }

    internal data class QuoteResponse(
            val _id: String,
            val quoteText: String,
            val quoteAuthor: String
    )
}