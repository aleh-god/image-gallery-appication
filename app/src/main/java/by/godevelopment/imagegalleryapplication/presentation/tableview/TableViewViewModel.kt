package by.godevelopment.imagegalleryapplication.presentation.tableview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.godevelopment.imagegalleryapplication.R
import by.godevelopment.imagegalleryapplication.commons.TAG
import by.godevelopment.imagegalleryapplication.domain.FetchImagesListUseCase
import by.godevelopment.imagegalleryapplication.domain.StringHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableViewViewModel @Inject constructor(
    private val fetchImagesListUseCase: FetchImagesListUseCase,
    private val stringHelper: StringHelper
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    private val _uiEvent  = MutableSharedFlow<String>(0)
    val uiEvent: SharedFlow<String> = _uiEvent

    private var fetchJob: Job? = null

    init {
        fetchImagesList()
    }

    fun fetchImagesList() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            fetchImagesListUseCase()
                .onStart {
                    Log.i(TAG, "viewModelScope.launch: .onStart")
                    _uiState.value = UiState(
                        isFetchingData = true
                    )
                }
                .catch { exception ->
                    Log.i(TAG, "TableViewViewModel: .catch ${exception.message}")
                    _uiState.value = UiState(
                        isFetchingData = false
                    )
                    delay(1000)
                    _uiEvent.emit(stringHelper.getString(R.string.alert_error_loading))
                }
                .collect {
                    Log.i(TAG, "viewModelScope.launch: .collect = ${it.size}")
                    _uiState.value = UiState(
                        isFetchingData = false,
                        imagesList = it
                    )
                }
        }
    }

    data class UiState(
        val isFetchingData: Boolean = false,
        val imagesList: List<String> = listOf()
    )
}