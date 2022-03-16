package by.godevelopment.imagegalleryapplication.domain

import android.util.Log
import by.godevelopment.imagegalleryapplication.commons.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchImagesListUseCase @Inject constructor(
    private val repository: GalleryRepository
) {
    operator fun invoke(): Flow<List<String>> = flow {
        repository.fetchImagesList()
    }
}