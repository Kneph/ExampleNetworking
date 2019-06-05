package com.example.examplenetworking.network

import com.example.examplenetworking.models.Repository
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("/users/{user}/repos")
    fun retrieveRepositories(@Path("user") user: String): Deferred<List<Repository>>
}