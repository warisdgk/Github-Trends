package mwaris.dev.githubtrends.trendingrepositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun noTrendingRepositoriesAvailable() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            val trendingListRepository = TrendingListRepository()
            val viewModel =
                TrendingRepositoriesListingViewModel(trendingListRepository, testDispatcher)

            trendingListRepository.signalEmptyData(true)
            viewModel.getTrendingGithubRepositoriesList()

            assertEquals(
                TrendingRepositoriesListingState.GithubRepositories(emptyList()),
                viewModel.trendingReposListingState.value
            )
        } finally {
            Dispatchers.resetMain()
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun someTrendingRepositoriesAvailable() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
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
            val viewModel =
                TrendingRepositoriesListingViewModel(trendingListRepository, testDispatcher)

            trendingListRepository.signalEmptyData(false)
            viewModel.getTrendingGithubRepositoriesList()

            assertEquals(
                TrendingRepositoriesListingState.GithubRepositories(listOfTrendingRepositories),
                viewModel.trendingReposListingState.value
            )
        } finally {
            Dispatchers.resetMain()
        }
    }
}