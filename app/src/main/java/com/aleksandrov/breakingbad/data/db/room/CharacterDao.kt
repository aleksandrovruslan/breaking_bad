package com.aleksandrov.breakingbad.data.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aleksandrov.breakingbad.data.db.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM characters")
    fun getCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM characters WHERE char_id = :id")
    fun getCharacterById(id: Int): CharacterEntity?

    @Query("SELECT * FROM characters WHERE name = :name")
    fun findCharacterByName(name: String): CharacterEntity?

}