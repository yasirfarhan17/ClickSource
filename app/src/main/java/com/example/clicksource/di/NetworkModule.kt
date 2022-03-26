package com.example.clicksource.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.networkmodule.api.BookApi
import com.example.networkmodule.network.HeaderInterceptor
import com.example.networkmodule.network.NetworkClient
import com.example.networkmodule.network.NetworkManager
import com.example.networkmodule.repository.BookRepository
import com.example.networkmodule.repository.BookRepositoryImpl
import com.example.networkmodule.usecase.BookUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): BookApi{
        return  retrofit.create(BookApi::class.java)

    }

    @Provides
    @Singleton
    fun provideCoinRepository(api:BookApi):BookRepository{
        return BookRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideNetworkManager(
        @ApplicationContext context:Context
    ): NetworkManager {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return NetworkManager(connectivityManager)
    }
    @Singleton
    @Provides
    fun provideOkHttpClient(
        headerInterceptor: HeaderInterceptor,
        networkManager: NetworkManager
    ):OkHttpClient{
        return NetworkClient.provideOkHttp(
            headerInterceptor,
            networkManager
        )
    }
    @Provides
    @Singleton
    fun provideUseCase(
        repository : BookRepository
    ):BookUseCase{
        return BookUseCase(repository)

    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ):Retrofit = NetworkClient.provideRetrofit(okHttpClient)

}