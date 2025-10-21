package com.geektcg.tienda.data

import com.geektcg.tienda.R

// Modelo de datos para un producto
data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Int,
    val imagen: Int,     // Recurso drawable
    val categoria: String // Nueva propiedad
)

// Repositorio simple de productos (mock local)
object Repo {
    val productos = listOf(
        // Pokémon
        Producto(1, "Mega-Lucario ex", 20000, R.drawable.pokemon1, "pokemon"),
        Producto(2, "Palafin Ex Box", 26990, R.drawable.pokemon2, "pokemon"),
        Producto(3, "Eevee Ex #174 Pokemon Promo", 39990, R.drawable.pokemon3, "pokemon"),
        Producto(4, "Iono's Bellibolt ex promo SVP 194 inglés + Tadbulb cosmo", 5000, R.drawable.pokemon4, "pokemon"),
        // Yu-Gi-Oh!
        Producto(5, "Yu-Gi-Oh! - Booster Packs", 25990, R.drawable.yugioh1, "yugioh"),
        Producto(6, "Retro Pack 2", 210000, R.drawable.yugioh2, "yugioh"),
        Producto(7, "Cofre de Oro Sellado 2019", 19900, R.drawable.yugioh3, "yugioh"),
        Producto(8, "Baraja de Dios Egipcio: Slifer el Dragón del Cielo/ Baraja de Dios Egipcio:vObelisco el Atormentador", 10990, R.drawable.yugioh1, "yugioh"),


        // Mitos y Leyendas
        Producto(9, "PREVENTA 3 RACIAL PRIMERA ERA 2025 - CABALLERO", 74990, R.drawable.myl1, "myl"),
        Producto(10, "Display Leyendas Primera Era 2023", 59790, R.drawable.myl2, "myl"),
        Producto(11, "1 Don Quijote de la Mancha + 1 Alicia en el país de las maravillas + 1 Primigenia + 7 cartas promocionales", 31990, R.drawable.myl3, "myl"),
        Producto(12, "1 Display Espíritu Samurái + 1 Display Bestiarium + 1 Display Onyria + 7 cartas promocionales", 77990, R.drawable.myl4, "myl"),

        // Accesorios
        Producto(13, "Protectores Standard | Colección Vitrales - Paladín 66x91mm", 7990, R.drawable.acccesorios1, "accesorios"),
        Producto(14, "Portamazos Premium | TOPDECK Dual Dice 200 color Azul", 7990, R.drawable.acccesorios2, "accesorios"),
        Producto(15, "Carpeta Premium | TOPDECK 480 color Azul", 29990, R.drawable.acccesorios3, "accesorios"),
        Producto(16, "Playmat | Colección Nueva Era - Astucia de Montaraz", 19990, R.drawable.acccesorios4, "accesorios"),
    )
}

