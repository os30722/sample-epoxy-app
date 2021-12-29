package com.hfad.epoxyapp.api

import com.hfad.epoxyapp.vo.Repo
import com.hfad.epoxyapp.vo.RepoSearchResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("search/repositories?sort=stars")
    fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): Call<RepoSearchResponse>


    companion object {

        private val BASE_URL = "https://api.github.com/"

        fun create(): GithubService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService::class.java)
        }
    }

}