package com.aleksandrov.breakingbad.data.network

import com.aleksandrov.breakingbad.data.network.ApiUrls.CHARACTERS
import com.aleksandrov.breakingbad.data.network.ApiUrls.CHARACTERS_BY_ID
import com.aleksandrov.breakingbad.data.network.ApiUrls.DEATH_COUNT
import com.aleksandrov.breakingbad.data.network.ApiUrls.EPISODES
import com.aleksandrov.breakingbad.data.network.ApiUrls.EPISODE_BY_ID
import com.aleksandrov.breakingbad.data.network.ApiUrls.QUOTES
import com.aleksandrov.breakingbad.data.network.ApiUrls.RANDOM_CHARACTER
import com.aleksandrov.breakingbad.domain.models.Character
import com.aleksandrov.breakingbad.domain.models.DeathCount
import com.aleksandrov.breakingbad.domain.models.Episode
import com.aleksandrov.breakingbad.domain.models.Quote
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class BreakingBadApiImpl @Inject constructor(
    private val client: OkHttpClient,
    private val moshi: Moshi,
) : BreakingBadApi {

    override fun deathCount(): DeathCount? = loadData<Array<DeathCount>>(DEATH_COUNT)?.first()

    override fun characters(): Array<Character>? = loadData<Array<Character>>(CHARACTERS)

    override fun characterById(id: Int): Character? =
        loadData<Array<Character>>("${CHARACTERS_BY_ID}$id")?.first()

    override fun randomCharacter(): Character? = loadData<Character>(RANDOM_CHARACTER)

    override fun findCharacterByName(name: String): Array<Character>? = loadData(CHARACTERS, name)

    override fun getEpisodes(): Array<Episode>? = loadData<Array<Episode>>(EPISODES)

    override fun getEpisodeById(id: Int): Array<Episode>? =
        loadData<Array<Episode>>("$EPISODE_BY_ID$id")

    override fun getQuotes(): Array<Quote>? = loadData<Array<Quote>>(QUOTES)

    private inline fun <reified T> loadData(url: String, name: String? = null): T? {
        val request = Request.Builder()
            .url(url)
            .let {
                name?.also { name ->
                    it.addHeader("name", name)
                }
                it
            }
            .build()
        var response: Response? = null
        try {
            response = client.newCall(request).execute()
            return if (response.code == 200) {
                response.body?.string()?.let {
                    val adapter: JsonAdapter<T> =
                        moshi.adapter(T::class.java)
                    adapter.fromJson(it)
                }
            } else null
        }
//        catch (e: Exception) {
//            e.printStackTrace()
//            return null
//        }
        finally {
            response?.close()
        }
    }

}