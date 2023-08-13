package mwaris.dev.githubtrends.trendingrepositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import mwaris.dev.githubtrends.InstantExecutorExtension
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
            val trendingListRepository = TrendingListRepository()
            val viewModel =
                TrendingRepositoriesListingViewModel(
                    trendingListRepository,
                    testDispatcher
                )

            trendingListRepository.signalEmptyData(true)

            val renderedStates = mutableListOf<TrendingRepositoriesScreenState>()

            viewModel.trendingReposListingState.observeForever {
                renderedStates.add(it)
            }

            viewModel.getTrendingGithubRepositoriesList()

            assertEquals(
                listOf(
                    TrendingRepositoriesScreenState(isLoading = true),
                    TrendingRepositoriesScreenState(listOfRepositories = emptyList()),
                ), renderedStates
            )
        } finally {
            Dispatchers.resetMain()
        }
    }

}