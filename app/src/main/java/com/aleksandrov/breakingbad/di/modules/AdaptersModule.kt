package com.aleksandrov.breakingbad.di.modules

import com.aleksandrov.breakingbad.presentation.characters.CharactersAdapter
import com.aleksandrov.breakingbad.presentation.episodes.EpisodesAdapter
import com.aleksandrov.breakingbad.presentation.quotes.QuotesAdapter
import dagger.Module
import dagger.Provides

@Module
class AdaptersModule {

    @Provides
    fun provideCharactersAdapter(): CharactersAdapter = CharactersAdapter()

    @Provides
    fun provideEpisodesAdapter(): EpisodesAdapter = EpisodesAdapter()

    @Provides
    fun provideQuotesAdapter(): QuotesAdapter = QuotesAdapter()

}