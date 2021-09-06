package com.github.ephelsa.composefooddelivery.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ephelsa.data.CategoryRepository
import com.github.ephelsa.data.ProductRepository
import com.github.ephelsa.domain.Category
import com.github.ephelsa.domain.Recommended
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository
) : ViewModel() {

    private val loadingCategories: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val onLoadingCategories: StateFlow<Boolean>
        get() = loadingCategories

    private val categories: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    val onCategories: StateFlow<List<Category>>
        get() = categories

    private val loadingRecommended: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val onLoadingRecommended: StateFlow<Boolean>
        get() = loadingRecommended

    private val recommended: MutableStateFlow<List<Recommended>> = MutableStateFlow(emptyList())
    val onRecommended: StateFlow<List<Recommended>>
        get() = recommended

    init {
        getCategories()
        getRecommended(null)
    }

    private fun getCategories() {
        viewModelScope.launch {
            loadingCategories.emit(true)
            categories.emit(categoryRepository.getCategories())
            loadingCategories.emit(false)
        }
    }

    fun getRecommended(categoryType: Category.CategoryType?) {
        viewModelScope.launch {
            loadingRecommended.emit(true)
            recommended.emit(productRepository.getRecommended(categoryType))
            loadingRecommended.emit(false)
        }
    }
}