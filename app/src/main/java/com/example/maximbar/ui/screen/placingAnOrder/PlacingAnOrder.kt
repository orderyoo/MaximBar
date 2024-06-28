package com.example.maximbar.ui.screen.placingAnOrder

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.maximbar.MainActivity
import com.example.maximbar.data.model.entity.Table
import com.example.maximbar.data.repository.Repository

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlacingAnOrder(
    viewModel: PlacingAnOrderVM = viewModel(
        factory = PlacingAnOrderVM.provideFactory(
            Repository(),
            owner = LocalSavedStateRegistryOwner.current
        )
    ),
    table: Table,
    navController: NavController,
    context: MainActivity
){
    var isVisibleBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    val cartProductUI by viewModel.cartProduct.observeAsState()
    val totalPriceUI by viewModel.result.observeAsState()

    val listCategory = listOf("Фрукты", "Выпечка", "Алкоголь", "Блюда")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Оформление заказа") },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack()},
                        content = { Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)}
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { isVisibleBottomSheet = true },
                content = { Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = null) }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ){
            Column(
                modifier = Modifier
                    .padding()
                    .align(Alignment.TopCenter)
            ){
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 15.dp)
                ){
                    Text(
                        text = "Номер стола: ${table.id}",
                        modifier = Modifier.padding(5.dp),
                        fontSize = 24.sp
                    )
                    Text(
                        text = "Назначаемая дата: 21-12-2077",
                        modifier = Modifier.padding(5.dp),
                        fontSize = 24.sp
                    )
                }
                RowCategory(listCategory = listCategory){

                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(16.dp),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(viewModel.listProduct){
                        ProductElement( product = it, viewModel, true)
                    }
                }
            }

        }
        if(isVisibleBottomSheet){
            ModalBottomSheet(
                onDismissRequest = { isVisibleBottomSheet = false },
                modifier = Modifier.padding(5.dp),
                sheetState = sheetState,
                shape = RoundedCornerShape(15.dp),
                dragHandle = { },
                content = {
                    Box(modifier = Modifier.fillMaxSize()){
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 120.dp)) {
                            Text(
                                text = "Корзина",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier.padding(16.dp),
                                contentPadding = PaddingValues(8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(cartProductUI ?: listOf()){
                                    ProductElement(product = it, viewModel = viewModel, false)
                                }
                            }
                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(vertical = 20.dp, horizontal = 10.dp)
                                .align(Alignment.BottomCenter),
                            shape = RoundedCornerShape(15.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 15.dp)
                        ){
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(0.65f),
                                    contentAlignment = Alignment.Center,
                                    content = {
                                        Text(
                                            text = "Итоговая цена: $totalPriceUI",
                                            fontSize = 18.sp
                                        )
                                    }
                                )
                                Box(
                                    modifier = Modifier.weight(0.35f),
                                    content = {
                                        Button(
                                            onClick = {  },
                                            shape = RoundedCornerShape(10.dp),
                                            content = { Text(text = "Заказать", fontSize = 18.sp)}
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}