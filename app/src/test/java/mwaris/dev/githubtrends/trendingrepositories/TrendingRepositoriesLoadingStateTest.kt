package mwaris.dev.githubtrends.trendingrepositories

import mwaris.dev.githubtrends.InstantExecutorExtension
import mwaris.dev.githubtrends.data.repositories.TrendingListRepository
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingState
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingViewModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class TrendingRepositoriesLoadingStateTest {

    @Test
    fun trendingRepositoriesStatesValidation() {

        val trendingListRepository = TrendingListRepository()
        val viewModel = TrendingRepositoriesListingViewModel(trendingListRepository)

        trendingListRepository.signalEmptyData(true)

        val renderedStates = mutableListOf<TrendingRepositoriesListingState>()

        viewModel.trendingReposListingState.observeForever {
            renderedStates.add(it)
        }

        viewModel.getTrendingGithubRepositoriesList()

        assertEquals(
            listOf(
                TrendingRepositoriesListingState.Loading,
                TrendingRepositoriesListingState.GithubRepositories(
                    emptyList(),
                )
            ), renderedStates
        )
    }

}