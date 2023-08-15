package mwaris.dev.githubtrends.data.remote

import mwaris.dev.githubtrends.data.remote.responses.SearchTrendingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ITrendingRepositoriesApi {

    @GET("/search/repositories")
    suspend fun searchTrendingRepositories(
        @Query("q") searchQuery: String = "language=+sort:stars"
    ): SearchTrendingResponse

}