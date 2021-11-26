package com.aleksandrov.breakingbad.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey var char_id: Int,
    var name: String,
    var birthday: String,
    var occupation: String,
    var img: String,
    var status: String,
    var nickname: String,
    var appearance: String,
    var portrayed: String,
    var category: String,
    var better_call_saul_appearance: String?,
)
