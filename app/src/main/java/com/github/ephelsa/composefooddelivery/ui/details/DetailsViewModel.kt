package com.github.ephelsa.composefooddelivery.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ephelsa.data.ProductRepository
import com.github.ephelsa.domain.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val loadingDetails: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val onLoadingDetails: StateFlow<Boolean>
        get() = loadingDetails

    private val details: MutableStateFlow<Product?> = MutableStateFlow(null)
    val onDetails: StateFlow<Product?>
        get() = details

    fun getDetails(realId: String = "Sushi_1") {
        viewModelScope.launch {
            loadingDetails.emit(true)
            details.emit(productRepository.getProductDetails(realId))
            loadingDetails.emit(false)
        }
    }
}