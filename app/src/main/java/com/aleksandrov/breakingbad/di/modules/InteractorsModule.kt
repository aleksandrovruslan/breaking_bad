package com.aleksandrov.breakingbad.di.modules

import com.aleksandrov.breakingbad.domain.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorsModule {

    @Provides
    @Singleton
    fun provideBBInteractor(repository: BBRepository): BBInteractor {
        return BBInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideCharactersInteractor(repository: BBRepository): CharactersInteractor {
        return CharactersInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideDetailsInteractor(repository: BBRepository): DetailsInteractor {
        return DetailsInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideEpisodesInteractor(repository: BBRepository): EpisodesInteractor {
        return EpisodesInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideEpisodeDetainsInteractor(repository: BBRepository): EpisodeDetainsInteractor {
        return EpisodeDetainsInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideQuotesInteractor(repository: BBRepository): QuotesInteractor {
        return QuotesInteractor(repository)
    }

}