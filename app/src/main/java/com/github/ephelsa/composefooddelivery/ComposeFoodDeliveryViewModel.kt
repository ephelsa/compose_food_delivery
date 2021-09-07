package com.github.ephelsa.composefooddelivery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ephelsa.data.ShoppingCartRepository
import com.github.ephelsa.data.UserProfileRepository
import com.github.ephelsa.domain.ProductQuantity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
class ComposeFoodDeliveryViewModel @Inject constructor(
    private val userProfileRepository: UserProfileRepository,
    private val shoppingCartRepository: ShoppingCartRepository
) : ViewModel() {

    private val userThumbnail: MutableStateFlow<String?> = MutableStateFlow(null)
    val onUserThumbnail: StateFlow<String?>
        get() = userThumbnail

    private val loadUserThumbnail: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val onLoadUserThumbnail: StateFlow<Boolean>
        get() = loadUserThumbnail

    private val productsQuantity: MutableStateFlow<Int> = MutableStateFlow(0)
    val onProductsQuantity: StateFlow<Int>
        get() = productsQuantity

    init {
        getUserThumbnail()
        productsQuantity()
    }

    private fun getUserThumbnail() {
        viewModelScope.launch {
            loadUserThumbnail.emit(true)
            userThumbnail.emit(userProfileRepository.getUserThumbnail())
            loadUserThumbnail.emit(false)
        }
    }

    private fun productsQuantity() {
        viewModelScope.launch {
            val transformation = shoppingCartRepository.shoppingCartProducts()
                .map { it.sumOf(ProductQuantity::quantity) }

            productsQuantity.emitAll(transformation)
        }
    }
}