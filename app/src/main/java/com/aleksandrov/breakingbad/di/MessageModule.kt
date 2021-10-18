package com.aleksandrov.breakingbad.di

import dagger.Module
import dagger.Provides

@Module
class MessageModule {

    @Provides
    fun provideMessage(): String {
        return "Message"
    }

}