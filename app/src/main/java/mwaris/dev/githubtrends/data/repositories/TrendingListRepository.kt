package mwaris.dev.githubtrends.data.repositories

import mwaris.dev.githubtrends.data.remote.ITrendingListDataSource
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesState
import javax.inject.Inject

class TrendingListRepository @Inject constructor(
    private val remoteTrendingListDataSource: ITrendingListDataSource
) : ITrendingListRepository {
    override suspend fun getTrendingGithubRepositoriesList(): TrendingRepositoriesState {
        return remoteTrendingListDataSource.getTrendingGithubRepositoriesList()
    }

}