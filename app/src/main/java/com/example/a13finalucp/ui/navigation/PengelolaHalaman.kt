package com.example.a13finalucp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a13finalucp.ui.view.DestinasiHome
import com.example.a13finalucp.ui.view.MainScreen
import com.example.a13finalucp.ui.view.event.DestinasiDetailEvent
import com.example.a13finalucp.ui.view.event.DestinasiHomeEvent
import com.example.a13finalucp.ui.view.event.DestinasiInsertEvent
import com.example.a13finalucp.ui.view.event.DestinasiUpdateEvent
import com.example.a13finalucp.ui.view.event.DetailEventScreen
import com.example.a13finalucp.ui.view.event.HomeEventScreen
import com.example.a13finalucp.ui.view.event.InsertEvtScreen
import com.example.a13finalucp.ui.view.event.UpdateEventScreen
import com.example.a13finalucp.ui.view.peserta.DestinasiDetailPeserta
import com.example.a13finalucp.ui.view.peserta.DestinasiInsertPeserta
import com.example.a13finalucp.ui.view.peserta.DestinasiHomePeserta
import com.example.a13finalucp.ui.view.peserta.DestinasiUpdatePeserta
import com.example.a13finalucp.ui.view.peserta.DetailPesertaScreen
import com.example.a13finalucp.ui.view.peserta.InsertPstScreen
import com.example.a13finalucp.ui.view.peserta.HomePesertaScreen
import com.example.a13finalucp.ui.view.peserta.UpdatePesertaScreen
import com.example.a13finalucp.ui.view.tiket.DestinasiDetailTiket
import com.example.a13finalucp.ui.view.tiket.DestinasiHomeTiket
import com.example.a13finalucp.ui.view.tiket.DestinasiInsertTiket
import com.example.a13finalucp.ui.view.tiket.DestinasiUpdateTiket
import com.example.a13finalucp.ui.view.tiket.DetailTiketScreen
import com.example.a13finalucp.ui.view.tiket.HomeTiketScreen
import com.example.a13finalucp.ui.view.tiket.InsertTktScreen
import com.example.a13finalucp.ui.view.tiket.UpdateTiketScreen
import com.example.a13finalucp.ui.view.transaksi.DestinasiDetailTransaksi
import com.example.a13finalucp.ui.view.transaksi.DestinasiHomeTransaksi
import com.example.a13finalucp.ui.view.transaksi.DestinasiInsertTransaksi
import com.example.a13finalucp.ui.view.transaksi.DestinasiUpdateTransaksi
import com.example.a13finalucp.ui.view.transaksi.DetailTransaksiScreen
import com.example.a13finalucp.ui.view.transaksi.HomeTransaksiScreen
import com.example.a13finalucp.ui.view.transaksi.InsertTssScreen
import com.example.a13finalucp.ui.view.transaksi.UpdateTransaksiScreen

@Composable
fun PengelolaHalaman (
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier,
    ) {
        composable(
            route = DestinasiHome.route
        ) {
            MainScreen(
                onPesertaClick = {navController.navigate(DestinasiHomePeserta.route)},
                onEventClick = {navController.navigate(DestinasiHomeEvent.route)},
                onTiketClick = {navController.navigate(DestinasiHomeTiket.route)},
                onTransaksiClick = {navController.navigate(DestinasiHomeTransaksi.route)},
            )
        }

//        PESERTA
        composable(
            route = DestinasiHomePeserta.route) {
            HomePesertaScreen(
                navigateToItemInsert = { navController.navigate(DestinasiInsertPeserta.route) },
                onDetailClick = { IDPeserta ->
                   println("HomePesertaScreen: Navigate to ${DestinasiDetailPeserta.route}/$IDPeserta")
                    navController.navigate("${DestinasiDetailPeserta.route}/$IDPeserta")
                },
                onBackClick = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                })
        }

        composable(DestinasiInsertPeserta.route) {
            InsertPstScreen(navigateBack = {
                navController.navigate(DestinasiHomePeserta.route) {
                    popUpTo(DestinasiHomePeserta.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            DestinasiDetailPeserta.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailPeserta.IDPeserta) {
                type = NavType.IntType
            })) {
            val id_peserta = it.arguments?.getInt(DestinasiDetailPeserta.IDPeserta)
            id_peserta?.let { id_peserta ->
                DetailPesertaScreen(
                    onBackClick = {
                    navController.popBackStack()
                },
                    onEditClick = { IDPeserta ->
                        println("DetailPesertaScreen: Navigate to ${DestinasiUpdatePeserta.route}/$IDPeserta")
                        navController.navigate("${DestinasiUpdatePeserta.route}/$IDPeserta")
                },
                    id_peserta = id_peserta
                )
            }
        }

        composable(
            route = DestinasiUpdatePeserta.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdatePeserta.IDPeserta) { type = NavType.IntType }
            )
        ) { backStackInsert ->
            val id_peserta = backStackInsert.arguments?.getInt(DestinasiUpdatePeserta.IDPeserta)
            id_peserta?.let {
                UpdatePesertaScreen(
                    onBackClick = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiHomePeserta.route) {
                            popUpTo(DestinasiHomePeserta.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier,
                )
            }
        }

//        EVENT
        composable(
            route = DestinasiHomeEvent.route) {
            HomeEventScreen(
                navigateToItemInsert = { navController.navigate(DestinasiInsertEvent.route) },
                onDetailClick = { IDEvent ->
                    println("HomeEventScreen: Navigate to ${DestinasiDetailEvent.route}/$IDEvent")
                    navController.navigate("${DestinasiDetailEvent.route}/$IDEvent")
                },
                onBackClick = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                })
        }

        composable(DestinasiInsertEvent.route) {
            InsertEvtScreen(navigateBack = {
                navController.navigate(DestinasiHomeEvent.route) {
                    popUpTo(DestinasiHomeEvent.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            DestinasiDetailEvent.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailEvent.IDEvent) {
                type = NavType.IntType
            })) {
            val id_event = it.arguments?.getInt(DestinasiDetailEvent.IDEvent)
            id_event?.let { id_event ->
                DetailEventScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onEditClick = { IDEvent ->
                        println("DetailEventScreen: Navigate to ${DestinasiUpdateEvent.route}/$IDEvent")
                        navController.navigate("${DestinasiUpdateEvent.route}/$IDEvent")
//                    navController.navigate("${DestinasiInsertEvent.route}/$it")
                    },
                    id_event = id_event
                )
            }
        }

        composable(
            route = DestinasiUpdateEvent.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateEvent.IDEvent) { type = NavType.IntType }
            )
        ) { backStackInsert ->
            val id_event = backStackInsert.arguments?.getInt(DestinasiUpdateEvent.IDEvent)
            id_event?.let {
                UpdateEventScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiHomeEvent.route) {
                            popUpTo(DestinasiHomeEvent.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier,
                )
            }
        }

//        TIKET
        composable(
            route = DestinasiHomeTiket.route) {
            HomeTiketScreen(
                navigateToItemInsert = { navController.navigate(DestinasiInsertTiket.route) },
                onDetailClick = { IDTiket ->
                    println("HomeTiketScreen: Navigate to ${DestinasiDetailTiket.route}/$IDTiket")
                    navController.navigate("${DestinasiDetailTiket.route}/$IDTiket")
                },
                onBackClick = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                })
        }

        composable(DestinasiInsertTiket.route) {
            InsertTktScreen(navigateBack = {
                navController.navigate(DestinasiHomeTiket.route) {
                    popUpTo(DestinasiHomeTiket.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            DestinasiDetailTiket.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailTiket.IDTiket) {
                type = NavType.IntType
            })) {
            val id_tiket = it.arguments?.getInt(DestinasiDetailTiket.IDTiket)
            id_tiket?.let { id_tiket ->
                DetailTiketScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onEditClick = { IDTiket ->
                        println("DetailTiketScreen: Navigate to ${DestinasiUpdateTiket.route}/$IDTiket")
                        navController.navigate("${DestinasiUpdateTiket.route}/$IDTiket")
                    },
                    id_tiket = id_tiket
                )
            }
        }

        composable(
            route = DestinasiUpdateTiket.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateTiket.IDTiket)
                { type = NavType.IntType }
            )
        ) { backStackInsert ->
            val id_tiket = backStackInsert.arguments?.getInt(DestinasiUpdateTiket.IDTiket)
            id_tiket?.let {
                UpdateTiketScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiHomeTiket.route) {
                            popUpTo(DestinasiHomeTiket.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier,
                )
            }
        }

//        TRANSAKSI
        composable(
            route = DestinasiHomeTransaksi.route) {
            HomeTransaksiScreen(
                navigateToItemInsert = { navController.navigate(DestinasiInsertTransaksi.route) },
                onDetailClick = { IDTransaksi ->
                    println("HomeTransaksiScreen: Navigate to ${DestinasiDetailTransaksi.route}/$IDTransaksi")
                    navController.navigate("${DestinasiDetailTransaksi.route}/$IDTransaksi")
                },
                onBackClick = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                })
        }

        composable(DestinasiInsertTransaksi.route) {
            InsertTssScreen(navigateBack = {
                navController.navigate(DestinasiHomeTransaksi.route) {
                    popUpTo(DestinasiHomeTransaksi.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            DestinasiDetailTransaksi.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailTransaksi.IDTransaksi) {
                type = NavType.IntType
            })) {
            val id_transaksi = it.arguments?.getInt(DestinasiDetailTransaksi.IDTransaksi)
            id_transaksi?.let { id_transaksi ->
                DetailTransaksiScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onEditClick = { IDTransaksi ->
                        println("DetailTransaksiScreen: Navigate to ${DestinasiUpdateTransaksi.route}/$IDTransaksi")
                        navController.navigate("${DestinasiUpdateTransaksi.route}/$IDTransaksi")
                    },
                    id_transaksi = id_transaksi
                )
            }
        }

        composable(
            route = DestinasiUpdateTransaksi.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateTransaksi.IDTransaksi)
                { type = NavType.IntType }
            )
        ) { backStackInsert ->
            val id_transaksi = backStackInsert.arguments?.getInt(DestinasiUpdateTransaksi.IDTransaksi)
            id_transaksi?.let {
                UpdateTransaksiScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiHomeTransaksi.route) {
                            popUpTo(DestinasiHomeTransaksi.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier,
                )
            }
        }
    }
}