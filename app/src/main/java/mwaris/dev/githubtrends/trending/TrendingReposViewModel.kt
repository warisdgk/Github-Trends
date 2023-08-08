package mwaris.dev.githubtrends.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TrendingReposViewModel {

    private val mutableTrendingReposState = MutableLiveData<TrendingRepoState>()
    val trendingReposState: LiveData<TrendingRepoState> = mutableTrendingReposState

    private var signalEmptyData: Boolean = false

    fun getTrendingRepos() {
        val listOfTrendingRepos = listOf(
            Repository(
                "23096959",
                "go",
                "golang/go",
                "https://avatars.githubusercontent.com/u/4314092?v=4",
                "The Go programming language"
            )
        )

        if (signalEmptyData) {
            mutableTrendingReposState.value = TrendingRepoState.GithubRepositories(emptyList())
        } else {
            mutableTrendingReposState.value = TrendingRepoState.GithubRepositories(
                listOfTrendingRepos
            )
        }
    }

    fun signalEmptyData(updatedValue: Boolean) {
        signalEmptyData = updatedValue
    }
}