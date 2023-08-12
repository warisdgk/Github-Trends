package mwaris.dev.githubtrends.trendingrepositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import mwaris.dev.githubtrends.InstantExecutorExtension
import mwaris.dev.githubtrends.data.repositories.ITrendingListRepository
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingState
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingViewModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class FailingTrendingRepositoriesListTest {

    @Test
    fun failingBackendError() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            val trendingListRepository = UnavailableTrendingRepositoryListRepository()
            val viewModel =
                TrendingRepositoriesListingViewModel(trendingListRepository, testDispatcher)

            viewModel.getTrendingGithubRepositoriesList()

            Assertions.assertEquals(
                TrendingRepositoriesListingState.BackendError,
                viewModel.trendingReposListingState.value
            )
        } finally {
            Dispatchers.resetMain()
        }
    }

    class UnavailableTrendingRepositoryListRepository : ITrendingListRepository {
        override suspend fun getTrendingGithubRepositoriesList(): TrendingRepositoriesListingState {
            return TrendingRepositoriesListingState.BackendError
        }

    }

}