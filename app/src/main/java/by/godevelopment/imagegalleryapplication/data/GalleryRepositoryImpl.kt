package by.godevelopment.imagegalleryapplication.data

import by.godevelopment.imagegalleryapplication.domain.GalleryRepository
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val remoteImageDataSource: RemoteImageDataSource
) : GalleryRepository {

    override suspend fun fetchImagesList() = remoteImageDataSource.fetchImagesList()
}