package by.godevelopment.imagegalleryapplication.domain

interface GalleryRepository {
    suspend fun fetchImagesList(): List<String>
}