package com.codeid.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codeid.pokemon.presentation.detail.DetailScreen
import com.codeid.pokemon.presentation.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController, startDestination = "home"
                ) {
                    composable("home") {
                        HomeScreen(
                            onPokemonClick = { name ->
                                navController.navigate("detail/$name")
                            })
                    }
                    composable(
                        route = "detail/{pokemonName}",
                        arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val name = backStackEntry.arguments?.getString("pokemonName") ?: ""
                        DetailScreen(pokemonName = name)
                    }
                }
            }
        }
    }
}
