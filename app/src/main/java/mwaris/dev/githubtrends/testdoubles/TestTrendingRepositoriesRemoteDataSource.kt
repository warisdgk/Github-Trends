package mwaris.dev.githubtrends.testdoubles

import mwaris.dev.githubtrends.data.exceptions.BackendException
import mwaris.dev.githubtrends.data.remote.ITrendingListDataSource
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesState

class TestTrendingRepositoriesRemoteDataSource : ITrendingListDataSource {

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