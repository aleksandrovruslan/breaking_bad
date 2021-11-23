package com.aleksandrov.breakingbad.data.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.aleksandrov.breakingbad.data.db.entity.*

class BBDbHelper(
    context: Context,
) : SQLiteOpenHelper(context, DBContract.DB_FILE_NAME, null, DBContract.DB_VERSION) {

    private val TAG = "BBDbHelper"

    private val DEATH_COUNT_TABLE = "death_count"
    private val KEY_COUNT_ID = "id"
    private val KEY_COUNT_ID_DEFAULT = 1
    private val KEY_COUNT_INT = "death_count"
    private val CREATE_DEAT_COUNT_TABLE =
        "CREATE TABLE $DEATH_COUNT_TABLE ($KEY_COUNT_ID INTEGER, $KEY_COUNT_INT INTEGER)"
    private val DROP_DEATH_COUNT_TABLE = "DROP TABLE $DEATH_COUNT_TABLE"
    private val QUERY_DEATH_COUNT =
        "SELECT $KEY_COUNT_INT FROM $DEATH_COUNT_TABLE WHERE $KEY_COUNT_ID = $KEY_COUNT_ID_DEFAULT"

    private val QUOTES_TABLE = "quotes"
    private val KEY_QUOTE_ID = "id"
    private val KEY_QUOTE = "quote"
    private val KEY_AUTHOR = "author"
    private val KEY_SERIES = "series"
    private val CREATE_QUOTES_TABLE =
        "CREATE TABLE $QUOTES_TABLE ($KEY_QUOTE_ID INTEGER, $KEY_QUOTE TEXT, $KEY_AUTHOR TEXT, $KEY_SERIES TEXT)"
    private val DROP_QUOTES_TABLE = "DROP TABLE $QUOTES_TABLE"
    private val QUERY_QUOTES = " SELECT * FROM $QUOTES_TABLE"

    private val CHARACTERS_TABLE = "characters"
    private val KEY_CHARACTER = "id"
    private val KEY_NAME = "name"
    private val KEY_BIRTHDAY = "birthday"
    private val KEY_OCCUPATION_REFERNCE_ID = "occupation_id"
    private val KEY_IMG = "image"
    private val KEY_STATUS = "status"
    private val KEY_NICKNAME = "nickname"
    private val KEY_APPEARANCE_REFERENCE_ID = "appearance"
    private val KEY_PORTRAYED = "portrayed"
    private val KEY_CATEGORY = "category"
    private val KEY_BETTER_CALL_SAUL_APPEARANCE = "better_call_saul_appearance"
    private val CREATE_CHARACTERS_TABLE =
        "CREATE TABLE $CHARACTERS_TABLE ($KEY_CHARACTER INTEGER, $KEY_NAME TEXT, $KEY_BIRTHDAY TEXT)"
    private val DROP_CHARACTERS_TABLE = "DROP TABLE $CHARACTERS_TABLE"
    private val QUERY_CHARACTERS = "SELECT * FROM $CHARACTERS_TABLE"

    private val OCCUPATIONS_TABLE = "occupations"
    private val KEY_OCCUPATION_ID = "id"
    private val KEY_OCCUPATION = "occupation"
    private val CREATE_OCCUPATION_TABLE =
        "CREATE TABLE $OCCUPATIONS_TABLE ($KEY_OCCUPATION_ID INTEGER PRIMARY KEY, $KEY_OCCUPATION TEXT)"
    private val DROP_OCCUPATION_TABLE = "DROP TABLE $OCCUPATIONS_TABLE"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_DEAT_COUNT_TABLE)
        db.execSQL(CREATE_QUOTES_TABLE)
        db.execSQL(CREATE_CHARACTERS_TABLE)
        db.execSQL(CREATE_OCCUPATION_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db.execSQL(DROP_DEATH_COUNT_TABLE)
            db.execSQL(DROP_QUOTES_TABLE)
            db.execSQL(DROP_CHARACTERS_TABLE)
            db.execSQL(DROP_OCCUPATION_TABLE)
            onCreate(db)
        }
    }

    fun addDeathCount(count: DeathCountEntity) {
        writableDatabase.beginTransaction()
        try {
            val values = ContentValues()
            values.put(KEY_COUNT_ID, KEY_COUNT_ID_DEFAULT)
            values.put(KEY_COUNT_INT, count.deathCount)
            val row = writableDatabase.update(DEATH_COUNT_TABLE,
                values,
                "$KEY_COUNT_ID = ?",
                arrayOf(count.toString()))
            if (row != 1) {
                writableDatabase.insertOrThrow(DEATH_COUNT_TABLE, null, values)
            }
            writableDatabase.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.d(TAG, "Error add death count")
        } finally {
            writableDatabase.endTransaction()
        }
    }

//    fun addDeathCount(count: DeathCountEntity) = with(ContentValues()) {
//        put(KEY_COUNT_ID, KEY_COUNT_ID_DEFAULT)
//        put(KEY_COUNT_INT, count.deathCount)
//        addToDb(DEATH_COUNT_TABLE, this, "Error add death count")
//    }

    @SuppressLint("Range")
    fun getDeathCount(): DeathCountEntity? {
        var count: DeathCountEntity? = null
        val cursor = readableDatabase.rawQuery(QUERY_DEATH_COUNT, null)
        try {
            if (cursor.moveToFirst()) {
                count = DeathCountEntity(cursor.getInt(cursor.getColumnIndex(KEY_COUNT_INT)))
            }
        } catch (e: Exception) {
            Log.d(TAG, "Error get death count")
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }
        return count
    }

    fun addQuotes(quotes: List<QuoteEntity>) {
        MutableList(quotes.size) { index ->
            ContentValues().also {
                it.put(KEY_QUOTE_ID, quotes[index].quote_id)
                it.put(KEY_QUOTE, quotes[index].quote)
                it.put(KEY_AUTHOR, quotes[index].author)
                it.put(KEY_SERIES, quotes[index].series)
            }
        }.also {
            writableDatabase.beginTransaction()
            try {
                for (values in it) {
                    writableDatabase.insertOrThrow(QUOTES_TABLE, null, values)
                }
                writableDatabase.setTransactionSuccessful()
            } catch (e: Exception) {
                Log.d(TAG, "Error add quotes")
            } finally {
                writableDatabase.endTransaction()
            }
        }
    }

    @SuppressLint("Range")
    fun getQuotes(): List<QuoteEntity>? {
        var quotes: MutableList<QuoteEntity>? = null
        val cursor = readableDatabase.rawQuery(QUERY_QUOTES, null)
        try {
            if (cursor.moveToFirst()) {
                quotes = mutableListOf()
                do {
                    quotes.add(
                        QuoteEntity(
                            cursor.getInt(cursor.getColumnIndex(KEY_QUOTE_ID)),
                            cursor.getString(cursor.getColumnIndex(KEY_QUOTE)),
                            cursor.getString(cursor.getColumnIndex(KEY_AUTHOR)),
                            cursor.getString(cursor.getColumnIndex(KEY_SERIES))
                        )
                    )
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.d(TAG, "Error get quotes")
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }
        Log.d(TAG, "getQuotes")
        return quotes
    }

    fun saveCharacters(characters: List<CharacterEntity>) {

    }

    fun getCharacters(): List<CharacterEntity>? {
        return null
    }

    fun getCharacterById(id: Int): CharacterEntity? {
        return null
    }

    fun getRandomCharacter(): CharacterEntity? {
        return null
    }

    fun findCharacterByName(name: String): List<CharacterEntity>? {
        return null
    }

    fun getEpisodes(): List<EpisodeEntity>? {
        return null
    }

    fun saveEpisodes(episodes: List<EpisodeEntity>) {

    }

    fun getEpisodeById(id: Int): EpisodeEntity? {
        return null
    }

}