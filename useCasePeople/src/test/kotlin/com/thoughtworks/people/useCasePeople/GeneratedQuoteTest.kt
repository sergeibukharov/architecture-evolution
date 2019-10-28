package com.thoughtworks.people.utils

import com.thoughtworks.people.useCasePeople.GeneratedQuote
import com.thoughtworks.people.useCasePeople.defaultQuote
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GeneratedQuoteTest {

    @Test
    fun `random quote should be received`() {
        val obtainedQuoute = GeneratedQuote().get()
        assertTrue(obtainedQuoute.isNotEmpty())
        assertNotEquals(defaultQuote, obtainedQuoute)
    }
}