package mwaris.dev.githubtrends.data.remote

import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesState
import javax.inject.Inject

class TrendingRepositoriesRemoteDataSource @Inject constructor(
    private val githubRepositoriesAPI: ITrendingRepositoriesApi
) : ITrendingListDataSource {

    override suspend fun getTrendingGithubRepositoriesList(): TrendingRepositoriesState {
        val result = githubRepositoriesAPI.searchTrendingRepositories().parse()
        return TrendingRepositoriesState.TrendingRepositories(repositories = result)
    }
}