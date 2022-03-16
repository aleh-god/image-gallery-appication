package by.godevelopment.imagegalleryapplication.di

import by.godevelopment.imagegalleryapplication.commons.BASE_URL
import by.godevelopment.imagegalleryapplication.data.ImageApi
import by.godevelopment.imagegalleryapplication.data.RemoteImageDataSource
import by.godevelopment.imagegalleryapplication.data.StringArrayListMoshiAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() : String = BASE_URL

    @Provides
    fun provideMoshi() : Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(StringArrayListMoshiAdapter())
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
        )
        .build()

    @Provides
    fun provideRetrofit(
        moshi: Moshi,
        BASE_URL : String,
        okHttpClient: OkHttpClient
    ) : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideImageApi(retrofit: Retrofit): ImageApi = retrofit.create(ImageApi::class.java)

    @Provides
    fun provideRemoteImageDataSource(
        imageApi: ImageApi
    ): RemoteImageDataSource = RemoteImageDataSource(imageApi)
}