package by.godevelopment.imagegalleryapplication.data

import javax.inject.Inject

class RemoteImageDataSource @Inject constructor(
    private val imageApi: ImageApi
) {

    suspend fun fetchImagesList() = imageApi.getImagesList()
}