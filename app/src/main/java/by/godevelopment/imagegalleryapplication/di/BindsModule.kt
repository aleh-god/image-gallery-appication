package by.godevelopment.imagegalleryapplication.di

import by.godevelopment.imagegalleryapplication.data.GalleryRepositoryImpl
import by.godevelopment.imagegalleryapplication.domain.GalleryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Binds
    abstract fun bindRepositoryToImpl(galleryRepositoryImpl: GalleryRepositoryImpl) : GalleryRepository
}