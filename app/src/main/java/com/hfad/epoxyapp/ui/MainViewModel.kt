package com.hfad.epoxyapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hfad.epoxyapp.api.GithubService
import com.hfad.epoxyapp.factory.GithubSourceFactory
import com.hfad.epoxyapp.vo.Repo
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val sourceFactory: GithubSourceFactory
) : ViewModel() {

    var repoData: LiveData<PagedList<Repo>>? = null

    init {
        repoData = LivePagedListBuilder<Int, Repo>(sourceFactory, getPagedListConfig()).build()
    }

    fun searchRepo(query: String) {
        viewModelScope.launch {
            sourceFactory.query = query
            sourceFactory.sourceLiveData.value?.invalidate()

        }
    }

    fun getPagedListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(0)
            .setPageSize(10)
            .build()
    }
}