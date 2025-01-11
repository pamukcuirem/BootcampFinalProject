package com.irempamukcu.bootcampfinalproject.local
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

// Singleton DataStore instance for storing preferences.
// The "info" name is used to identify the preferences storage file.
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("info")

// This class provides a wrapper around Android's DataStore API to manage application preferences.
// It includes methods to save and retrieve simple key-value data such as a counter and a name.
class AppPref(private val context: Context) {

    companion object {
        // Key for storing and retrieving the name of user.
        val NAME_KEY = stringPreferencesKey("NAME")

        // Key for storing and retrieving the counter for checking the application first time run or not.
        val COUNTER_KEY = intPreferencesKey("COUNTER")
    }

    // Saves the counter value into DataStore preferences.
    suspend fun setCounter(counter: Int) {
        context.dataStore.edit {
            it[COUNTER_KEY] = counter
        }
    }

    // Retrieves the counter value from DataStore preferences.
    suspend fun getCounter(): Int {
        val preferences = context.dataStore.data.first()
        return preferences[COUNTER_KEY] ?: 0
    }


    // Saves the name value into DataStore preferences.
    suspend fun setName(name: String) {
        context.dataStore.edit {
            it[NAME_KEY] = name
        }
    }

    // Retrieves the name value from DataStore preferences.
    suspend fun getName(): String {
        val preferences = context.dataStore.data.first()
        return preferences[NAME_KEY] ?: ""
    }
}
