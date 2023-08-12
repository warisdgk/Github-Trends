package mwaris.dev.githubtrends.data.repositories

import mwaris.dev.githubtrends.data.entities.Repository
import mwaris.dev.githubtrends.data.exceptions.BackendException
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingState

class TrendingListRepository : ITrendingListRepository {

    private var signalEmptyData: Boolean = false

    override suspend fun getTrendingGithubRepositoriesList(): TrendingRepositoriesListingState {
        val listOfTrendingRepos = listOf(
            Repository(
                "23096959",
                "go",
                "golang/go",
                "https://avatars.githubusercontent.com/u/4314092?v=4",
                "The Go programming language"
            )
        )

        val repositoryState = try {
            if (signalEmptyData) {
                TrendingRepositoriesListingState.GithubRepositories(emptyList())
            } else {
                TrendingRepositoriesListingState.GithubRepositories(listOfTrendingRepos)
            }
        } catch (exception: BackendException) {
            TrendingRepositoriesListingState.BackendError
        }
        return repositoryState
    }

    fun signalEmptyData(updatedValue: Boolean) {
        signalEmptyData = updatedValue
    }
}