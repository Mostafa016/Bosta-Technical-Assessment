package com.example.bostatechnicalassessment.di

import android.content.Context
import androidx.room.Room
import com.example.bostatechnicalassessment.data.local.CityDatabase
import com.example.bostatechnicalassessment.data.remote.CityApiService
import com.example.bostatechnicalassessment.data.repository.CityRepositoryImpl
import com.example.bostatechnicalassessment.domain.repository.CityRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
abstract class ChooseDeliveryAreaViewModelModule {
    @Binds
    @ViewModelScoped
    abstract fun bindCityRepository(
        impl: CityRepositoryImpl
    ): CityRepository

    companion object {
        @Provides
        @ViewModelScoped
        fun provideCityApiService(): CityApiService = Retrofit.Builder()
            .baseUrl(CityApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CityApiService::class.java)

        @Provides
        @ViewModelScoped
        fun provideCityDatabase(@ApplicationContext appContext: Context): CityDatabase {
            return Room.databaseBuilder(
                appContext,
                CityDatabase::class.java,
                CityDatabase.DATABASE_NAME,
            ).build()
        }
    }
}