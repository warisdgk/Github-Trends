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

        viewModel.getTrendingRepos()

        assertEquals(
            TrendingRepoState.GithubRepositories(emptyList()),
            viewModel.trendingReposState.value
        )
    }
}