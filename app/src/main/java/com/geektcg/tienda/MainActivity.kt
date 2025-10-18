package com.geektcg.tienda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.geektcg.tienda.ui.*
import com.geektcg.tienda.ui.theme.LeivaVegaTheme   // ✅ Importa tu tema azul personalizado
import kotlinx.coroutines.launch
import androidx.compose.foundation.isSystemInDarkTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 💙 Aplicar tu tema Azul Príncipe (claro/oscuro)
            LeivaVegaTheme(
                darkTheme = isSystemInDarkTheme()  // Se adapta automáticamente
            ) {
                TiendaApp()
            }
        }
    }
}

// 📱 Definición de pantallas principales
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendaApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // 🔹 Secciones principales (Bottom Bar)
    val mainItems = listOf(
        Screen.Inicio,
        Screen.Productos,
        Screen.Carrito,
        Screen.Nosotros
    )

    // 🔹 Secciones secundarias (Drawer lateral)
    val drawerItems = listOf(
        Screen.Contacto,
        Screen.Blogs,
        Screen.Login,
        Screen.Registro,
        Screen.Usuarios
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Menú Geek TCG",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
                drawerItems.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(item.label) },
                        selected = false,
                        onClick = {
                            navController.navigate(item.route)
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Geek TCG") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,   // 💙 Barra superior azul
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menú",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    mainItems.forEach { screen ->
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
                            icon = {}
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
                composable(Screen.Inicio.route) {
                    InicioScreen(onVerProducto = { id ->
                        navController.navigate(Screen.Detalle.withId(id))
                    })
                }
                composable(Screen.Productos.route) {
                    ProductosScreen(onVerProducto = { id ->
                        navController.navigate(Screen.Detalle.withId(id))
                    })
                }
                composable(Screen.Carrito.route) { CarritoScreen() }
                composable(Screen.Nosotros.route) { NosotrosScreen() }
                composable(Screen.Contacto.route) { ContactoScreen() }
                composable(Screen.Blogs.route) { BlogsScreen() }
                composable(Screen.Login.route) {
                    LoginScreen(onRegistro = {
                        navController.navigate(Screen.Registro.route)
                    })
                }
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
}

private fun NavDestination?.isRoute(route: String): Boolean {
    return this?.route?.startsWith(route.substringBefore("/{")) == true
}
