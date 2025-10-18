

package com.geektcg.tienda.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.userDataStore by preferencesDataStore("user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val USER_PASS = stringPreferencesKey("user_pass")
    }

    suspend fun saveUser(name: String, email: String, pass: String) {
        context.userDataStore.edit { prefs ->
            prefs[USER_NAME] = name
            prefs[USER_EMAIL] = email
            prefs[USER_PASS] = pass
        }
    }

    val userName: Flow<String?> = context.userDataStore.data.map { it[USER_NAME] }
    val userEmail: Flow<String?> = context.userDataStore.data.map { it[USER_EMAIL] }
    val userPass: Flow<String?> = context.userDataStore.data.map { it[USER_PASS] }
}
