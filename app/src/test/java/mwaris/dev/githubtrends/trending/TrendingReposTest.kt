package mwaris.dev.githubtrends.trending


import mwaris.dev.githubtrends.InstantExecutorExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class TrendingReposTest {

    @Test
    fun noTrendingReposAvailable() {
        val viewModel = TrendingReposViewModel()

        viewModel.signalEmptyData(true)
        viewModel.getTrendingRepos()

        assertEquals(
            TrendingRepoState.GithubRepositories(emptyList()),
            viewModel.trendingReposState.value
        )
    }

    @Test
    fun trendingReposAvailable() {

        val listOfTrendingRepositories = listOf(
            Repository(
                "23096959",
                "go",
                "golang/go",
                "https://avatars.githubusercontent.com/u/4314092?v=4",
                "The Go programming language"
            )
        )

        val viewModel = TrendingReposViewModel()

        viewModel.signalEmptyData(false)
        viewModel.getTrendingRepos()

        assertEquals(
            TrendingRepoState.GithubRepositories(listOfTrendingRepositories),
            viewModel.trendingReposState.value
        )
    }

}