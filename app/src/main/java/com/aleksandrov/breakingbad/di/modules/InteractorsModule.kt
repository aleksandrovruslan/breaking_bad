package com.aleksandrov.breakingbad.di.modules

import com.aleksandrov.breakingbad.domain.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorsModule {

    @Provides
    @Singleton
    fun provideBBInteractor(repository: BBRepository): DeathsInteractor {
        return DeathsInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideCharactersInteractor(repository: BBRepository): CharactersInteractor {
        return CharactersInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideDetailsInteractor(repository: BBRepository): CharacterDetailsInteractor {
        return CharacterDetailsInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideEpisodesInteractor(repository: BBRepository): EpisodesInteractor {
        return EpisodesInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideEpisodeDetainsInteractor(repository: BBRepository): EpisodeDetailsInteractor {
        return EpisodeDetailsInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideQuotesInteractor(repository: BBRepository): QuotesInteractor {
        return QuotesInteractor(repository)
    }

}