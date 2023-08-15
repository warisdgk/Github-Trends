package mwaris.dev.githubtrends.trendingrepositories

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import mwaris.dev.githubtrends.InstantExecutorExtension
import mwaris.dev.githubtrends.testdoubles.TestTrendingRepositoriesRemoteDataSource
import mwaris.dev.githubtrends.testdoubles.TestNetworkMonitor
import mwaris.dev.githubtrends.data.repositories.TrendingListRepository
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingViewModel
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesScreenState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class TrendingRepositoriesLoadingStateTest {

    private val trendingRepositoriesRemoteDataSource = TestTrendingRepositoriesRemoteDataSource()
    private val trendingListRepository = TrendingListRepository(trendingRepositoriesRemoteDataSource)
    private val testNetworkMonitor = TestNetworkMonitor()
    private val savedStateHandle =  SavedStateHandle()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    private val renderedStates = mutableListOf<TrendingRepositoriesScreenState>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun trendingRepositoriesStatesValidation() = runTest {
        Dispatchers.setMain(testDispatcher)
        try {
            val viewModel =
                TrendingRepositoriesListingViewModel(
                    trendingListRepository,
                    savedStateHandle,
                    testDispatcher,
                    testNetworkMonitor
                )

            viewModel.dataState.observeForever {
                renderedStates.add(it)
            }

            trendingRepositoriesRemoteDataSource.signalEmptyData(true)
            viewModel.getTrendingGithubRepositoriesList()

            assertEquals(
                listOf(
                    TrendingRepositoriesScreenState(),
                    TrendingRepositoriesScreenState(isLoading = true),
                    TrendingRepositoriesScreenState(listOfRepositories = emptyList()),
                ), renderedStates
            )
        } finally {
            Dispatchers.resetMain()
        }
    }

}