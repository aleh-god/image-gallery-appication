package by.godevelopment.imagegalleryapplication.presentation.fullview

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class FullViewViewModel @Inject constructor() : ViewModel() {

    val uiEvent  = MutableSharedFlow<String>(0)
}