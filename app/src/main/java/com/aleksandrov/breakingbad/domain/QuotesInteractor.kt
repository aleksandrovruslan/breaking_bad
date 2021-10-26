package com.aleksandrov.breakingbad.domain

import com.aleksandrov.breakingbad.models.Quote
import javax.inject.Inject

class QuotesInteractor @Inject constructor(private val repository: BBRepository) {

    fun loadQuotes(): List<Quote>? = repository.getQuotes()?.toList()

}