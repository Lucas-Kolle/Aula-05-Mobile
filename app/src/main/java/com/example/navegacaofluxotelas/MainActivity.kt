package com.example.navegacaofluxotelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navegacaofluxotelas.screens.LoginScreen
import com.example.navegacaofluxotelas.screens.MenuScreen
import com.example.navegacaofluxotelas.screens.PedidosScreen
import com.example.navegacaofluxotelas.screens.PerfilScreen
import com.example.navegacaofluxotelas.ui.theme.NavegacaoFluxoTelasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegacaoFluxoTelasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "Login",
                        exitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(1000)
                            )
                        },
                        enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(1000)
                            )
                        }
                    ){
                        composable (
                            route = "Login",
                            exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                                    animationSpec = tween(1000)
                                ) + fadeOut(
                                    animationSpec = tween(1000)
                                )
                            }
                        ){
                            LoginScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                        }

                        composable (
                            route = "Perfil/{nome}/{idade}",
                            arguments = listOf(
                                navArgument(name = "nome"){
                                    type = NavType.StringType
                                },
                                navArgument(name = "idade"){
                                    type = NavType.IntType
                                }
                            )
                        ){

                            val nome = it.arguments?.getString("nome") // Acessa os argumentos recebidos e pega o que possui a chave "nome"
                            val idade = it.arguments?.getInt("idade")

                            PerfilScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                                nome = nome!!,
                                idade = idade!!
                            )
                        }

                        composable (route = "Menu"){
                            MenuScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                        }

                        composable (
                            route = "Pedidos?numeroPedido={numeroPedido}",
                            arguments = listOf(navArgument(name = "numeroPedido"){
                                defaultValue = "Sem Pedidos" // Se nenhum valor for informado a resposta será : "sem pedidos"
                            })
                        ){
                            val numeroPedido = it.arguments?.getString("numeroPedido")

                            PedidosScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                                numeroPedido = numeroPedido!!
                            )
                        }
                    }


                }
            }
        }
    }
}