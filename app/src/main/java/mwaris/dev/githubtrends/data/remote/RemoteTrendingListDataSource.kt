package mwaris.dev.githubtrends.data.remote

import mwaris.dev.githubtrends.testing.DummyRepositoriesData
import mwaris.dev.githubtrends.data.exceptions.BackendException
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesState

class RemoteTrendingListDataSource : ITrendingListDataSource {

    private val listOfTrendingRepos = DummyRepositoriesData.listOfTrendingRepositories
    private var signalEmptyData: Boolean = false

    override suspend fun getTrendingGithubRepositoriesList(): TrendingRepositoriesState {

        val repositoryState = try {
            if (signalEmptyData) {
                TrendingRepositoriesState.TrendingRepositories(emptyList())
            } else {
                TrendingRepositoriesState.TrendingRepositories(listOfTrendingRepos)
            }
        } catch (exception: BackendException) {
            TrendingRepositoriesState.BackendError
        }
        return repositoryState
    }

    fun signalEmptyData(updatedValue: Boolean) {
        signalEmptyData = updatedValue
    }
}