
package com.geektcg.tienda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.geektcg.tienda.ui.*
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TiendaApp()
            }
        }
    }
}

sealed class Screen(val route: String, val label: String) {
    object Inicio: Screen("inicio", "Inicio")
    object Productos: Screen("productos", "Productos")
    object Carrito: Screen("carrito", "Carrito")
    object Contacto: Screen("contacto", "Contacto")
    object Nosotros: Screen("nosotros", "Nosotros")
    object Blogs: Screen("blogs", "Blogs")
    object Login: Screen("login", "Login")
    object Registro: Screen("registro", "Registro")
    object Usuarios: Screen("usuarios", "Usuarios")
    object AdmHome: Screen("adm_home", "Admin")
    object Detalle: Screen("detalle/{id}", "Detalle") {
        fun withId(id: Int) = "detalle/$id"
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TiendaApp() {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Inicio, Screen.Productos, Screen.Carrito, Screen.Blogs, Screen.Nosotros
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Geek TCG") },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        },


        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination.isRoute(screen.route),
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text(screen.label) },
                        icon = { /* could add icons */ }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Inicio.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Inicio.route) { InicioScreen(onVerProducto = { id -> navController.navigate(Screen.Detalle.withId(id)) }) }
            composable(Screen.Productos.route) { ProductosScreen(onVerProducto = { id -> navController.navigate(Screen.Detalle.withId(id)) }) }
            composable(Screen.Carrito.route) { CarritoScreen() }
            composable(Screen.Blogs.route) { BlogsScreen() }
            composable(Screen.Nosotros.route) { NosotrosScreen() }
            composable(Screen.Contacto.route) { ContactoScreen() }
            composable(Screen.Login.route) { LoginScreen(onRegistro = { navController.navigate(Screen.Registro.route) }) }
            composable(Screen.Registro.route) { RegistroScreen() }
            composable(Screen.Usuarios.route) { UsuariosScreen() }
            composable(Screen.AdmHome.route) { AdmHomeScreen() }
            composable(Screen.Detalle.route) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
                DetalleScreen(id = id)
            }
        }
    }
}

private fun NavDestination?.isRoute(route: String): Boolean {
    return this?.route?.startsWith(route.substringBefore("/{")) == true
}
