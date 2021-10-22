package com.aleksandrov.breakingbad.data.network

import com.aleksandrov.breakingbad.data.network.ApiUrls.CHARACTERS
import com.aleksandrov.breakingbad.data.network.ApiUrls.CHARACTERS_BY_ID
import com.aleksandrov.breakingbad.data.network.ApiUrls.DEATH_COUNT
import com.aleksandrov.breakingbad.data.network.ApiUrls.RANDOM_CHARACTER
import com.aleksandrov.breakingbad.models.Character
import com.aleksandrov.breakingbad.models.DeathCount
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import javax.inject.Inject

class BreakingBadApiImpl @Inject constructor(
    client: OkHttpClient,
    moshi: Moshi,
) : AbstractBreakingBadApi(client, moshi), BreakingBadApi {

    override fun deathCount(): DeathCount? = loadData<Array<DeathCount>>(DEATH_COUNT)?.first()

    override fun characters(): Array<Character>? = loadData<Array<Character>>(CHARACTERS)

    override fun characterById(id: Int): Character? =
        loadData<Array<Character>>("${CHARACTERS_BY_ID}$id")?.first()

    override fun randomCharacter(): Character? = loadData<Character>(RANDOM_CHARACTER)

    override fun findCharacterByName(name: String): Array<Character>? = loadData(CHARACTERS, name)

}