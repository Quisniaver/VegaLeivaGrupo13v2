package com.geektcg.tienda.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

class Repository(private val context: Context, private val db: AppDatabase) {
    private val CART_COUNT = intPreferencesKey("cart_count")

    val cartItems: Flow<List<CartItem>> = db.cartDao().getAll()
    val cartCount: Flow<Int> = context.dataStore.data.map { it[CART_COUNT] ?: 0 }

    suspend fun addToCart(item: CartItem) {
        db.cartDao().insert(item)
        context.dataStore.edit { prefs ->
            val current = prefs[CART_COUNT] ?: 0
            prefs[CART_COUNT] = current + item.qty
        }
    }

    suspend fun clearCart() {
        db.cartDao().clear()
        context.dataStore.edit { prefs ->
            prefs[CART_COUNT] = 0
        }
    }
}