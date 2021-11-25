package com.aleksandrov.breakingbad.di.modules

import android.content.Context
import androidx.room.Room
import com.aleksandrov.breakingbad.data.db.room.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideBBDatabase(context: Context): BBDatabase {
        return Room.databaseBuilder(context, BBDatabase::class.java, "bb.db").build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(database: BBDatabase): CharacterDao = database.characterDao()

    @Provides
    @Singleton
    fun provideDeathCountDao(database: BBDatabase): DeathCountDao = database.deathCountDao()

    @Provides
    @Singleton
    fun provideEpisodeDao(database: BBDatabase): EpisodeDao = database.episodeDao()

    @Provides
    @Singleton
    fun provideQuoteDao(database: BBDatabase): QuoteDao = database.quoteDao()

}