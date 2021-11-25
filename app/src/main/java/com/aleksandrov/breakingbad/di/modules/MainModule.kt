package com.aleksandrov.breakingbad.di.modules

import com.aleksandrov.breakingbad.data.network.BreakingBadApi
import com.aleksandrov.breakingbad.data.network.BreakingBadApiImpl
import com.aleksandrov.breakingbad.data.repositories.BBRepositoryImpl
import com.aleksandrov.breakingbad.domain.repositories.BBRepository
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {

    @Binds
    abstract fun bindsBBRepository(repository: BBRepositoryImpl): BBRepository

    @Binds
    abstract fun bindsBreakingBadApi(api: BreakingBadApiImpl): BreakingBadApi

}