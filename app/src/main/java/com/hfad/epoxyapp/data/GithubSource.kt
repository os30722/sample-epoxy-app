package com.hfad.epoxyapp.data

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.hfad.epoxyapp.api.GithubService
import com.hfad.epoxyapp.vo.Repo
import com.hfad.epoxyapp.vo.RepoSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubSource(
    private val service: GithubService,
    private val query: String
): PageKeyedDataSource<Int, Repo>()  {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Repo>
    ) {

        service.searchRepos(query, 0, params.requestedLoadSize)
            .enqueue(object: Callback<RepoSearchResponse>{
                override fun onResponse(
                    call: Call<RepoSearchResponse>,
                    response: Response<RepoSearchResponse>
                ) {
                    val resp = response.body()

                    if(resp?.items != null)
                        callback.onResult(resp.items, null, 1)
                    else
                        callback.onResult(emptyList(), null, null)

                }

                override fun onFailure(call: Call<RepoSearchResponse>, t: Throwable) {
                    Log.d("debug64", "Error retrieving items: ${t.message}")
                }

            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) = Unit

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {
        service.searchRepos(query, params.key, params.requestedLoadSize)
            .enqueue(object: Callback<RepoSearchResponse>{
                override fun onResponse(
                    call: Call<RepoSearchResponse>,
                    response: Response<RepoSearchResponse>
                ) {
                    val resp = response.body()
                    if(resp?.items != null)
                        callback.onResult(resp.items, params.key + 1)
                    else
                        callback.onResult(emptyList(), null)

                }

                override fun onFailure(call: Call<RepoSearchResponse>, t: Throwable) {
                    Log.d("debug64", "Error retrieving items: ${t.message}")
                }

            })
    }



}