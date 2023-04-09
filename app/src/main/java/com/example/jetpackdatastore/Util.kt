package com.example.jetpackdatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//Preference Name
const val PREFERENCE_NAME = "MyDataStore"

//Instance of DataStore
// SINGLETON
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

// WRITE STRING VALUE
suspend fun Context.writeString(key: String, value: String) {
    dataStore.edit { pref -> pref[stringPreferencesKey(key)] = value }
}

suspend fun Context.writeBool(key: String, value: Boolean) {
    dataStore.edit {
        it[booleanPreferencesKey((key))] = value
    }
}

class DatastoreConstants {
    companion object {
        const val USER_NAME_KEY : String = "USER_NAME"
    }
}


// READING STRING VALUE
fun Context.readString(key: String): Flow<String> {
    return dataStore.data.map{
        it[stringPreferencesKey(key)] ?: "Save Data First !!"
    }
}

// WRITE INT VALUE
suspend fun Context.writeInt(key: String, value: Int) {
    dataStore.edit { pref -> pref[intPreferencesKey(key)] = value }
}

// READ INT VALUE
fun Context.readInt(key: String): Flow<Int> {
    return dataStore.data.map { pref ->
        pref[intPreferencesKey(key)] ?: 0
    }
}