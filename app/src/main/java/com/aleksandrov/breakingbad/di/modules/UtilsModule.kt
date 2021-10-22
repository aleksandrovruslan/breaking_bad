package com.aleksandrov.breakingbad.di.modules

import com.aleksandrov.breakingbad.utils.SchedulersProvider
import com.aleksandrov.breakingbad.utils.SchedulersProviderImpl
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {

    @Provides
    fun provideSchedulers(): SchedulersProvider {
        return SchedulersProviderImpl()
    }

}