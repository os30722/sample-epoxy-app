package com.hfad.epoxyapp.factory

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.hfad.epoxyapp.api.GithubService
import com.hfad.epoxyapp.data.GithubSource
import com.hfad.epoxyapp.vo.Repo

class GithubSourceFactory(
    private val service: GithubService,
    var query: String = "java",
) : DataSource.Factory<Int, Repo>() {

    val sourceLiveData = MutableLiveData<GithubSource>()

    override fun create(): DataSource<Int, Repo> {
        val source = GithubSource(service, query)
        sourceLiveData.postValue(source)
        return source

    }


}