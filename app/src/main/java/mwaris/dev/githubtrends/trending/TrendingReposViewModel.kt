package mwaris.dev.githubtrends.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TrendingReposViewModel {

    private val mutableTrendingReposState = MutableLiveData<TrendingRepoState>()
    val trendingReposState: LiveData<TrendingRepoState> = mutableTrendingReposState

    private var signalEmptyData: Boolean = false

    fun getTrendingGithubRepos() {
        val repositoryState = getGithubRepositories()
        mutableTrendingReposState.value = repositoryState
    }

    private fun getGithubRepositories(): TrendingRepoState.GithubRepositories {
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
            TrendingRepoState.GithubRepositories(emptyList())
        } else {
            TrendingRepoState.GithubRepositories(listOfTrendingRepos)
        }
        return repositoryState
    }

    fun signalEmptyData(updatedValue: Boolean) {
        signalEmptyData = updatedValue
    }
}