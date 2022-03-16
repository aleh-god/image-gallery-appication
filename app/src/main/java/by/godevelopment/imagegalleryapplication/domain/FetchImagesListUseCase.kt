package by.godevelopment.imagegalleryapplication.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchImagesListUseCase @Inject constructor(
    private val repository: GalleryRepository
) {
    operator fun invoke(): Flow<List<String>> = flow {
        emit(repository.fetchImagesList())
    }
}