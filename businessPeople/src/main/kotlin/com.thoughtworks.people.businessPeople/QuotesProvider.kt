package com.thoughtworks.people.businessPeople

interface QuotesProvider {
    fun randomQuote():Quote
}

typealias Quote = String