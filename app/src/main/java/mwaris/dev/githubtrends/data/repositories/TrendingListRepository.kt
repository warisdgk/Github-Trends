package mwaris.dev.githubtrends.data.repositories

import mwaris.dev.githubtrends.data.entities.Repository
import mwaris.dev.githubtrends.ui.trending_repositories.list.TrendingRepositoriesListingState

class TrendingListRepository {

    private var signalEmptyData: Boolean = false

    fun getTrendingGithubRepositoriesList(): TrendingRepositoriesListingState.GithubRepositories {
        val listOfTrendingRepos = listOf(
            Repository(
                "23096959",
                "go",
                "golang/go",
                "https://avatars.githubusercontent.com/u/4314092?v=4",
                "The Go programming language"
            )
        )

        val repositoryState = if (signalEmptyData) {
            TrendingRepositoriesListingState.GithubRepositories(emptyList())
        } else {
            TrendingRepositoriesListingState.GithubRepositories(listOfTrendingRepos)
        }
        return repositoryState
    }

    fun signalEmptyData(updatedValue: Boolean) {
        signalEmptyData = updatedValue
    }
}