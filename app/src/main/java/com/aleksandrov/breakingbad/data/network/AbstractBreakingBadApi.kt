package com.aleksandrov.breakingbad.data.network

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

const val TAG = "MainActivity"

abstract class AbstractBreakingBadApi(
    var client: OkHttpClient,
    val moshi: Moshi,
) {

    inline fun <reified T> loadData(url: String, name: String? = null): T? {
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
                    Log.d(TAG, it)
                    val adapter: JsonAdapter<T> =
                        moshi.adapter(T::class.java)
                    adapter.fromJson(it).also {
                        Log.d(TAG, "fromJson - $it")
                    }
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