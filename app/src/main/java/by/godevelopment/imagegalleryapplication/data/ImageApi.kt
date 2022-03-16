package by.godevelopment.imagegalleryapplication.data

import by.godevelopment.imagegalleryapplication.domain.ImagesModel
import retrofit2.http.GET

interface ImageApi {

    @GET("task-m-001/list.php")
    suspend fun getImagesList(): ImagesModel
}