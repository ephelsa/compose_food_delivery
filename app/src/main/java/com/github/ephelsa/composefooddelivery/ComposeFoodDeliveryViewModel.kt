package com.github.ephelsa.composefooddelivery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ephelsa.data.UserProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ComposeFoodDeliveryViewModel @Inject constructor(
    private val userProfileRepository: UserProfileRepository
) : ViewModel() {

    private val userThumbnail: MutableStateFlow<String?> = MutableStateFlow(null)
    val onUserThumbnail: StateFlow<String?>
        get() = userThumbnail

    private val loadUserThumbnail: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val onLoadUserThumbnail: StateFlow<Boolean>
        get() = loadUserThumbnail

    init {
        getUserThumbnail()
    }

    private fun getUserThumbnail() {
        viewModelScope.launch {
            loadUserThumbnail.emit(true)
            userThumbnail.emit(userProfileRepository.getUserThumbnail())
            loadUserThumbnail.emit(false)
        }
    }
}