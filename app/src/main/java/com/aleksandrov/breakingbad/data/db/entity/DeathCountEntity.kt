package com.aleksandrov.breakingbad.data.db.entity

import android.provider.BaseColumns
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "death_count")
data class DeathCountEntity(@PrimaryKey var id: Int = 1, val deathCount: Int) : BaseColumns
