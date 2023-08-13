package mwaris.dev.githubtrends.data.repositories

import mwaris.dev.githubtrends.R
import mwaris.dev.githubtrends.data.entities.Repository
import mwaris.dev.githubtrends.data.exceptions.BackendException
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesScreenState

class TrendingListRepository : ITrendingListRepository {

    private var signalEmptyData: Boolean = false

    override suspend fun getTrendingGithubRepositoriesList(): TrendingRepositoriesScreenState {
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
                TrendingRepositoriesScreenState(listOfRepositories = emptyList())
            } else {
                TrendingRepositoriesScreenState(listOfRepositories = listOfTrendingRepos)
            }
        } catch (exception: BackendException) {
            TrendingRepositoriesScreenState(error= R.string.error_trending_repos_fetching)
        }
        return repositoryState
    }

    fun signalEmptyData(updatedValue: Boolean) {
        signalEmptyData = updatedValue
    }
}