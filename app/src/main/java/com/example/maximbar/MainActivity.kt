package com.example.maximbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.maximbar.data.model.entity.Table
import com.example.maximbar.ui.screen.main.TableReservation
import com.example.maximbar.ui.screen.placingAnOrder.PlacingAnOrder
import com.example.maximbar.ui.theme.MaximbarTheme
import com.google.gson.Gson
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.Socket
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private lateinit var socket: Socket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = z()
            MaximbarTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ){
                    composable("home"){
                        TableReservation( navController = navController)
                    }
                    composable(
                        route = "order/{table}",
                        arguments = listOf(navArgument("table") { type = NavType.StringType})
                    ) { backStackEntry ->
                        val userJson = backStackEntry.arguments?.getString("table")
                        val table = userJson?.let { Gson().fromJson(it, Table::class.java) }
                        PlacingAnOrder(
                            table = table!!,
                            navController = navController,
                            context =  this@MainActivity
                        )
                    }
                }

//                var text by remember { mutableStateOf("ничего") }
//                viewModel.t.observe(this){
//                    text = it!!
//                }
//                Column {
//
//                    Button(onClick = {
//                        viewModel.f()
//                    }
//                    ) {
//                        Text(text = "тыкай нахой")
//                    }
//                }

            }
        }
    }
}
suspend fun c(): String = withContext(Dispatchers.IO){
    val selectorManager = SelectorManager(Dispatchers.IO)
    val socket = aSocket(selectorManager).tcp().connect("192.168.196.123", 8888)
    val receiveChannel = socket.openReadChannel()
    val n = receiveChannel.readUTF8Line(Int.MAX_VALUE)!!
    println(n)
    return@withContext n

}
class z(): ViewModel(){
    val t = MutableLiveData<String>()

    fun f(){
        viewModelScope.launch {
            t.value = c()
        }
    }
}
