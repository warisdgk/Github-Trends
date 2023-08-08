package mwaris.dev.githubtrends.ui.trending_repositories.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mwaris.dev.githubtrends.data.entities.Repository

class TrendingRepositoriesListingViewModel {

    private val mutableTrendingReposState = MutableLiveData<TrendingRepositoriesListingState>()
    val trendingReposState: LiveData<TrendingRepositoriesListingState> = mutableTrendingReposState

    private var signalEmptyData: Boolean = false

    fun getTrendingGithubRepos() {
        val repositoryState = getGithubRepositories()
        mutableTrendingReposState.value = repositoryState
    }

    private fun getGithubRepositories(): TrendingRepositoriesListingState.GithubRepositories {
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