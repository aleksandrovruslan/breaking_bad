package com.aleksandrov.breakingbad.di.modules

import com.aleksandrov.breakingbad.presentation.characters.CharactersAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AdaptersModule {

    @Singleton
    @Provides
    fun provideCharactersAdapter(): CharactersAdapter {
        return CharactersAdapter()
    }

}