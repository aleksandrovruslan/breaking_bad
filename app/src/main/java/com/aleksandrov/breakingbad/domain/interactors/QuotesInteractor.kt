package com.aleksandrov.breakingbad.domain.interactors

import com.aleksandrov.breakingbad.domain.models.Quote
import com.aleksandrov.breakingbad.domain.repositories.BBRepository
import javax.inject.Inject

class QuotesInteractor @Inject constructor(private val repository: BBRepository) {

    /**
     * Получить список цитат из хранилища
     * @return список цитат
     */
    fun loadQuotes(): List<Quote>? = repository.getQuotes()

    /**
     * Получить список цитат с сервера
     * @return список цитат
     */
    fun loadRemoteQuotes(): List<Quote>? = repository.getRemoteQuotes()

}