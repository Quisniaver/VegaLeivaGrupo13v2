package com.geektcg.tienda.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.geektcg.tienda.data.AppDatabase
import com.geektcg.tienda.data.CartItem
import com.geektcg.tienda.data.Repository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CarritoViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = Repository(app, AppDatabase.get(app))

    // 🔹 Lista reactiva de productos
    val items: StateFlow<List<CartItem>> =
        repo.cartItems.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // 🔹 Total automático
    val total: StateFlow<Int> =
        repo.cartItems.map { list -> list.sumOf { it.price * it.qty } }
            .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    // 🔹 Agregar producto (si ya existe, aumenta cantidad)
    fun add(productId: Int, name: String, price: Int, qty: Int = 1) {
        viewModelScope.launch {
            val current = repo.getCartItemById(productId)
            if (current != null) {
                repo.updateCartItem(current.copy(qty = current.qty + qty))
            } else {
                repo.addToCart(CartItem(productId = productId, name = name, price = price, qty = qty))
            }
        }
    }

    // 🔹 Borrar un producto del carrito
    fun removeItem(item: CartItem) {
        viewModelScope.launch {
            repo.removeFromCart(item)
        }
    }

    // 🔹 Vaciar el carrito completo
    fun clear() {
        viewModelScope.launch {
            repo.clearCart()
        }
    }
}
