package com.thoughtworks.people.quotesGarden

import com.thoughtworks.people.businessPeople.Quote
import com.thoughtworks.people.businessPeople.QuotesProvider
import org.springframework.web.client.RestTemplate
import javax.inject.Named

const val defaultQuote = "whoops, something went wrong"

@Named
class QuoteGardenProvider: QuotesProvider {

    private val getRandomUrl = "https://quote-garden.herokuapp.com/quotes/random"

    override fun randomQuote(): Quote =
            RestTemplate()
                    .getForEntity(getRandomUrl, QuoteResponse::class.java)
                    .body?.quoteText ?: defaultQuote

    internal data class QuoteResponse(
            val _id: String,
            val quoteText: String,
            val quoteAuthor: String
    )
}