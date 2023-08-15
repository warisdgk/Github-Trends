package mwaris.dev.githubtrends.trendingrepositories

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import mwaris.dev.githubtrends.InstantExecutorExtension
import mwaris.dev.githubtrends.base.TestNetworkMonitor
import mwaris.dev.githubtrends.data.remote.RemoteTrendingListDataSource
import mwaris.dev.githubtrends.data.repositories.TrendingListRepository
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingViewModel
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesScreenState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class TrendingRepositoriesLoadingStateTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun trendingRepositoriesStatesValidation() = runTest {
        val testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        try {
            val remoteTrendingListDataSource = RemoteTrendingListDataSource()
            val trendingListRepository = TrendingListRepository(remoteTrendingListDataSource)
            val testNetworkMonitor = TestNetworkMonitor()
            val savedStateHandle =  SavedStateHandle()
            val viewModel =
                TrendingRepositoriesListingViewModel(
                    trendingListRepository,
                    savedStateHandle,
                    testDispatcher,
                    testNetworkMonitor
                )

            remoteTrendingListDataSource.signalEmptyData(true)

            val renderedStates = mutableListOf<TrendingRepositoriesScreenState>()

            viewModel.dataState.observeForever {
                renderedStates.add(it)
            }

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