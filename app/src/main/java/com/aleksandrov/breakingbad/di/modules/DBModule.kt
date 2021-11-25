package com.aleksandrov.breakingbad.di.modules

import com.aleksandrov.breakingbad.data.db.BBStore
import com.aleksandrov.breakingbad.data.db.RoomBBStoreImpl
import com.aleksandrov.breakingbad.data.db.room.CharacterDao
import com.aleksandrov.breakingbad.data.db.room.DeathCountDao
import com.aleksandrov.breakingbad.data.db.room.EpisodeDao
import com.aleksandrov.breakingbad.data.db.room.QuoteDao
import com.aleksandrov.breakingbad.domain.converters.BBConverter
import com.aleksandrov.breakingbad.domain.converters.BBConverterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {

    @Provides
    @Singleton
    fun provideBBStore(
        characterDao: CharacterDao,
        deathCountDao: DeathCountDao,
        episodeDao: EpisodeDao,
        quoteDao: QuoteDao,
    ): BBStore {
        return RoomBBStoreImpl(characterDao, deathCountDao, episodeDao, quoteDao)
    }

    @Provides
    @Singleton
    fun provideBBConverter(): BBConverter {
        return BBConverterImpl()
    }

}