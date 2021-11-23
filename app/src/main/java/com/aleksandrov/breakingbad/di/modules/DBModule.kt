package com.aleksandrov.breakingbad.di.modules

import android.content.Context
import com.aleksandrov.breakingbad.data.db.BBDbHelper
import com.aleksandrov.breakingbad.data.db.BBStore
import com.aleksandrov.breakingbad.data.db.SqliteBBStoreImpl
import com.aleksandrov.breakingbad.domain.converters.BBConverter
import com.aleksandrov.breakingbad.domain.converters.BBConverterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {

    @Provides
    @Singleton
    fun provideBBStore(helper: BBDbHelper): BBStore {
        return SqliteBBStoreImpl(helper)
    }

    @Provides
    @Singleton
    fun provideBBDbHelper(context: Context): BBDbHelper {
        return BBDbHelper(context)
    }

    @Provides
    @Singleton
    fun provideBBConverter(): BBConverter {
        return BBConverterImpl()
    }

}