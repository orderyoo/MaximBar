package com.example.maximbar.ui.screen.placingAnOrder

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.maximbar.data.model.entity.Product
import com.example.maximbar.data.repository.Repository

class PlacingAnOrderVM(val repository: Repository, stateHandle: SavedStateHandle): ViewModel(){

    val listProduct = listOf(
        Product(0, "Персик", 12.99, "hui", "Фрукт", "gh"),
        Product(1, "Яблоко", 12.99, "hui", "Фрукт", "gh"),
        Product(2, "Водка", 12.99, "hui", "Фрукт", "gh"),
        Product(3, "Егермейстер, 0.7", 2000.00, "hui", "Алкоголь", ""),
        Product(4, "Персик", 12.99, "hui", "Фрукт", "gh")
    )

    private val _cartProduct = MutableLiveData<List<Product>>(listOf())
    val cartProduct: LiveData<List<Product>> get() = _cartProduct

    val _totalPrice = MutableLiveData<Double>(0.0)
    val result: LiveData<Double> get() = _totalPrice

    fun addProductToCart(product: Product) {
        val list = _cartProduct.value?.toMutableList() ?: mutableListOf()
        list.add(product)
        _cartProduct.value = list
        _totalPrice.value= _totalPrice.value!! + product.price
    }

    fun removeProductFromCart(product: Product) {
        val list = _cartProduct.value?.toMutableList() ?: mutableListOf()
        list.remove(product)
        _cartProduct.value = list
        _totalPrice.value= _totalPrice.value!! - product.price
    }
    fun print(){
        println(cartProduct.value!!.size)
    }

    companion object{
        fun provideFactory(
            myRepository: Repository,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return PlacingAnOrderVM(myRepository, handle) as T
                }
            }
    }
}