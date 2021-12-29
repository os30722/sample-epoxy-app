package com.hfad.epoxyapp.di

import com.hfad.epoxyapp.api.GithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Retrofit {


    @Provides
    @Singleton
    fun provideGithubService(): GithubService = GithubService.create()

}