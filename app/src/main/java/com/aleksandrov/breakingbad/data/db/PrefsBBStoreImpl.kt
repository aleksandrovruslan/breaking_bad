package com.aleksandrov.breakingbad.data.db

import android.content.SharedPreferences
import com.aleksandrov.breakingbad.models.DeathCount
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

const val DEATH_COUNT = "DEATH_COUNT"

class PrefsBBStoreImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val moshi: Moshi,
) : BBStore {

    override fun saveDeathCount(deathCount: DeathCount) {
        val adapter: JsonAdapter<DeathCount> = moshi.adapter(DeathCount::class.java)
        sharedPreferences.edit().putString(DEATH_COUNT, adapter.toJson(deathCount)).commit()
    }

    override fun getDeathCount(): DeathCount? {
        val deathCountString = sharedPreferences.getString(DEATH_COUNT, null)
        val adapter: JsonAdapter<DeathCount> = moshi.adapter(DeathCount::class.java)
        return deathCountString?.let(adapter::fromJson)
    }

}