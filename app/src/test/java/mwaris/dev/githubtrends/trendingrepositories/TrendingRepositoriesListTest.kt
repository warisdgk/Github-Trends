package mwaris.dev.githubtrends.trendingrepositories

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import mwaris.dev.githubtrends.InstantExecutorExtension
import mwaris.dev.githubtrends.testing.TestNetworkMonitor
import mwaris.dev.githubtrends.testing.DummyRepositoriesData
import mwaris.dev.githubtrends.data.remote.RemoteTrendingListDataSource
import mwaris.dev.githubtrends.data.repositories.TrendingListRepository
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingViewModel
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesScreenState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class TrendingRepositoriesListTest {

    private val remoteTrendingListDataSource = RemoteTrendingListDataSource()
    private val trendingListRepository = TrendingListRepository(remoteTrendingListDataSource)
    private val savedStateHandle = SavedStateHandle()
    private val testNetworkMonitor = TestNetworkMonitor()

    private val listOfTrendingRepositories = DummyRepositoriesData.listOfTrendingRepositories

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun noTrendingRepositoriesAvailable() = runTest {
        Dispatchers.setMain(testDispatcher)
        try {
            val viewModel =
                TrendingRepositoriesListingViewModel(
                    trendingListRepository,
                    savedStateHandle,
                    testDispatcher,
                    testNetworkMonitor
                )

            remoteTrendingListDataSource.signalEmptyData(true)
            viewModel.getTrendingGithubRepositoriesList()

            assertEquals(
                TrendingRepositoriesScreenState(),
                viewModel.dataState.value
            )
        } finally {
            Dispatchers.resetMain()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun someTrendingRepositoriesAvailable() = runTest {
        Dispatchers.setMain(testDispatcher)
        try {
            val viewModel = TrendingRepositoriesListingViewModel(
                trendingListRepository,
                savedStateHandle,
                testDispatcher,
                testNetworkMonitor
            )

            remoteTrendingListDataSource.signalEmptyData(false)
            viewModel.getTrendingGithubRepositoriesList()

            assertEquals(
                TrendingRepositoriesScreenState(listOfRepositories = listOfTrendingRepositories),
                viewModel.dataState.value
            )
        } finally {
            Dispatchers.resetMain()
        }
    }
}