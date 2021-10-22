package com.aleksandrov.breakingbad.di.modules

import android.content.Context
import com.aleksandrov.breakingbad.data.db.BBStore
import com.aleksandrov.breakingbad.data.db.DEATH_COUNT
import com.aleksandrov.breakingbad.data.db.PrefsBBStoreImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides

@Module
class DBModule {

    @Provides
    fun provideBBStore(context: Context, moshi: Moshi): BBStore {
        return PrefsBBStoreImpl(context.getSharedPreferences(DEATH_COUNT, Context.MODE_PRIVATE),
            moshi)
    }

}