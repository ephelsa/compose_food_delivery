package com.github.ephelsa.composefooddelivery.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ephelsa.data.CategoryRepository
import com.github.ephelsa.domain.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
) : ViewModel() {

    private val loadingCategories: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val onLoadingCategories: StateFlow<Boolean>
        get() = loadingCategories

    private val categories: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    val onCategories: StateFlow<List<Category>>
        get() = categories

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            loadingCategories.emit(true)
            categories.emit(categoryRepository.getCategories())
            loadingCategories.emit(false)
        }
    }
}