package com.example.maximbar.ui.screen.placingAnOrder

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.maximbar.R
import com.example.maximbar.data.model.entity.Product

@Composable
fun ProductElement(
    product: Product,
    viewModel: PlacingAnOrderVM,
    action: Boolean
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Card(
            modifier = Modifier
                .size(150.dp)
                .padding(5.dp)
                .clickable { },
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 15.dp)
        ){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(id = R.drawable.peach), contentDescription = null)
                IconButton(
                    onClick = {
                              if(action){
                                  viewModel.addProductToCart(product)
                              } else {
                                  viewModel.removeProductFromCart(product)
                              }
                    },
                    modifier = Modifier.align(alignment = Alignment.BottomEnd),
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Gray),
                    content = {
                        if(action){
                            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                        } else {
                            Icon(imageVector = Icons.Filled.Remove, contentDescription = null)
                        }
                    }
                )
            }

        }
        Text(text = product.title)
        Text(text = "Цена: ${product.price} руб")
    }
}