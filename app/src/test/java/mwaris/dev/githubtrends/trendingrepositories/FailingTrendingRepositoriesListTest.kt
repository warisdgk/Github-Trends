package mwaris.dev.githubtrends.trendingrepositories

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import mwaris.dev.githubtrends.InstantExecutorExtension
import mwaris.dev.githubtrends.R
import mwaris.dev.githubtrends.base.TestNetworkMonitor
import mwaris.dev.githubtrends.data.repositories.ITrendingListRepository
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingViewModel
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesScreenState
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(InstantExecutorExtension::class)
class FailingTrendingRepositoriesListTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun failingBackendError() = runTest {
        val testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        try {
            val trendingListRepository = UnavailableTrendingRepositoryListRepository()
            val testNetworkMonitor = TestNetworkMonitor()
            val savedStateHandle = SavedStateHandle()
            val viewModel =
                TrendingRepositoriesListingViewModel(
                    trendingListRepository,
                    savedStateHandle,
                    testDispatcher,
                    testNetworkMonitor
                )

            viewModel.getTrendingGithubRepositoriesList()

            Assertions.assertEquals(
                TrendingRepositoriesScreenState(error = R.string.error_trending_repos_fetching),
                viewModel.dataState.value
            )

        } finally {
            Dispatchers.resetMain()
        }
    }

    class UnavailableTrendingRepositoryListRepository : ITrendingListRepository {
        override suspend fun getTrendingGithubRepositoriesList(): TrendingRepositoriesScreenState {
            return TrendingRepositoriesScreenState(error = R.string.error_trending_repos_fetching)
        }

    }

}