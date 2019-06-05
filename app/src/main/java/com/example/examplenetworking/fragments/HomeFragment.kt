package com.example.examplenetworking.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examplenetworking.R
import com.example.examplenetworking.adapters.RepositoryAdapter
import com.example.examplenetworking.models.Repository
import com.example.examplenetworking.network.GitHubService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class HomeFragment : Fragment() {

    var repositories: List<Repository> = listOf()
    lateinit var service: GitHubService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        service = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(GitHubService::class.java)

        retrieveRepos()
    }

    private fun retrieveRepos() {
        GlobalScope.launch {
            repositories = service.retrieveRepositories("JakeWharton").await()
            updateRepo()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swiper.setOnRefreshListener {
            swiper.isRefreshing = false
            retrieveRepos()
        }
        recycler_view.layoutManager = LinearLayoutManager(context)
        updateRepo()
    }

    private fun updateRepo() {
        activity?.runOnUiThread {
            recycler_view.adapter = RepositoryAdapter(repositories, context)
        }

    }


}