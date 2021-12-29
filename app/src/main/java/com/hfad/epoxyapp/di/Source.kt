package com.hfad.epoxyapp.di

import com.hfad.epoxyapp.api.GithubService
import com.hfad.epoxyapp.data.GithubSource
import com.hfad.epoxyapp.factory.GithubSourceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Source {


    @Provides
    @Singleton
    fun provideGithubSource(service: GithubService): GithubSourceFactory = GithubSourceFactory(service)

}