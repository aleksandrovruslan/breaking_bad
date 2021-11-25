package com.aleksandrov.breakingbad.data.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aleksandrov.breakingbad.data.db.entity.CharacterEntity
import com.aleksandrov.breakingbad.data.db.entity.DeathCountEntity
import com.aleksandrov.breakingbad.data.db.entity.EpisodeEntity
import com.aleksandrov.breakingbad.data.db.entity.QuoteEntity

@Database(
    entities = [
        DeathCountEntity::class, CharacterEntity::class,
        EpisodeEntity::class, QuoteEntity::class
    ],
    version = 1
)
abstract class BBDatabase : RoomDatabase() {

    abstract fun deathCountDao(): DeathCountDao

    abstract fun characterDao(): CharacterDao

    abstract fun episodeDao(): EpisodeDao

    abstract fun quoteDao(): QuoteDao

}