package com.example.cis4034_n.ui.di

import com.example.cis4034_n.data.network.KtorClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getKtorClient() : KtorClient {
        return KtorClient()
    }

}