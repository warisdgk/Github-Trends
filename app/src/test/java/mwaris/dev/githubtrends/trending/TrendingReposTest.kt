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

        viewModel.getTrendingRepos(true)

        assertEquals(
            TrendingRepoState.GithubRepositories(emptyList()),
            viewModel.trendingReposState.value
        )
    }

    @Test
    fun trendingReposAvailable(){
        val viewModel = TrendingReposViewModel()

        viewModel.getTrendingRepos(false)

        assertEquals(
            TrendingRepoState.GithubRepositories(
                listOf(Repositories("23096959",
                    "go",
                    "golang/go",
                    "https://avatars.githubusercontent.com/u/4314092?v=4",
                    "The Go programming language"))
            ),
            viewModel.trendingReposState.value
        )
    }

}