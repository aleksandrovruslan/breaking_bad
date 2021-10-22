package com.aleksandrov.breakingbad.di.modules

import com.aleksandrov.breakingbad.domain.BBInteractor
import com.aleksandrov.breakingbad.domain.BBRepository
import com.aleksandrov.breakingbad.domain.CharactersInteractor
import com.aleksandrov.breakingbad.domain.DetailsInteractor
import dagger.Module
import dagger.Provides

@Module
class InteractorsModule {

    @Provides
    fun provideBBInteractor(repository: BBRepository): BBInteractor {
        return BBInteractor(repository)
    }

    @Provides
    fun provideCharactersInteractor(repository: BBRepository): CharactersInteractor {
        return CharactersInteractor(repository)
    }

    @Provides
    fun provideDetailsInteractor(repository: BBRepository): DetailsInteractor {
        return DetailsInteractor(repository)
    }

}