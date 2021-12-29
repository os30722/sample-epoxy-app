package com.hfad.epoxyapp.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.airbnb.epoxy.EpoxyRecyclerView
import com.hfad.epoxyapp.R
import com.hfad.epoxyapp.data.RepoListController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val INITIAL_QUERY = "java"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRepoList()
        initSearch()
    }

    private fun initRepoList(){
        val recyclerView = findViewById<EpoxyRecyclerView>(R.id.repo_list)

        //Adding separator
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayout.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        val controller = RepoListController()
        recyclerView.setController(controller)
        viewModel.searchRepo("java")

        viewModel.repoData?.observe(this){ pagedList ->
            controller.submitList(pagedList)
        }

    }

    private fun initSearch(){
        val searchBox = findViewById<EditText>(R.id.search_repo)
        searchBox.setText(INITIAL_QUERY)

        searchBox.setOnKeyListener{_, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                viewModel.searchRepo(searchBox.text.toString())

                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(searchBox.windowToken, 0)
                true
            } else {
                false
            }

        }
    }


}