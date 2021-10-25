package com.aleksandrov.breakingbad.di.modules

import com.aleksandrov.breakingbad.domain.BBInteractor
import com.aleksandrov.breakingbad.domain.BBRepository
import com.aleksandrov.breakingbad.domain.CharactersInteractor
import com.aleksandrov.breakingbad.domain.DetailsInteractor
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

}