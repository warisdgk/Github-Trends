package mwaris.dev.githubtrends.trendingrepositories


import mwaris.dev.githubtrends.InstantExecutorExtension
import mwaris.dev.githubtrends.data.entities.Repository
import mwaris.dev.githubtrends.data.repositories.TrendingListRepository
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingState
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingViewModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class TrendingRepositoriesListTest {

    @Test
    fun noTrendingRepositoriesAvailable() {
        val trendingListRepository = TrendingListRepository()
        val viewModel = TrendingRepositoriesListingViewModel(trendingListRepository)

        trendingListRepository.signalEmptyData(true)
        viewModel.getTrendingGithubRepositoriesList()

        assertEquals(
            TrendingRepositoriesListingState.GithubRepositories(emptyList()),
            viewModel.trendingReposListingState.value
        )
    }

    @Test
    fun someTrendingRepositoriesAvailable() {

        val listOfTrendingRepositories = listOf(
            Repository(
                "23096959",
                "go",
                "golang/go",
                "https://avatars.githubusercontent.com/u/4314092?v=4",
                "The Go programming language"
            )
        )

        val trendingListRepository = TrendingListRepository()
        val viewModel = TrendingRepositoriesListingViewModel(trendingListRepository)

        trendingListRepository.signalEmptyData(false)
        viewModel.getTrendingGithubRepositoriesList()

        assertEquals(
            TrendingRepositoriesListingState.GithubRepositories(listOfTrendingRepositories),
            viewModel.trendingReposListingState.value
        )
    }

}