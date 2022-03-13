package com.example.breweryapp.dependency

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.breweryapp.data.db.BreweryDatabase
import com.example.breweryapp.data.networking.BreweryApi
import com.example.breweryapp.data.repository.BreweryRepositoryImpl
import com.example.breweryapp.domain.repository.BreweryRepository
import com.example.breweryapp.utils.Utils
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [AppModule.RepositoryBuilderModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoviesDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        BreweryDatabase::class.java,
        "brewery_db"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideMovieDao(db: BreweryDatabase) = db.dao

    @Singleton
    @Provides
    fun provideOkHttpClient() = run {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideBaseUrl() = Utils.BASE_URL

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String) =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit) = retrofit.create(BreweryApi::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    internal abstract class RepositoryBuilderModule {
        @Binds
        abstract fun bindBreweryRepository(breweryRepositoryImpl: BreweryRepositoryImpl): BreweryRepository
    }
}