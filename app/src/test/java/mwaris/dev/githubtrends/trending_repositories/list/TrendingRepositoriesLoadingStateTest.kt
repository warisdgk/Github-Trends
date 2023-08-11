package mwaris.dev.githubtrends.trending_repositories.list

import mwaris.dev.githubtrends.InstantExecutorExtension
import mwaris.dev.githubtrends.data.repositories.TrendingListRepository
import mwaris.dev.githubtrends.ui.trending_repositories.list.TrendingRepositoriesListingState
import mwaris.dev.githubtrends.ui.trending_repositories.list.TrendingRepositoriesListingViewModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class TrendingRepositoriesLoadingStateTest {

    @Test
    fun trendingRepositoriesStatesValidation() {

        val renderedStates = mutableListOf<TrendingRepositoriesListingState>()

        val trendingListRepository = TrendingListRepository()
        val viewModel = TrendingRepositoriesListingViewModel(trendingListRepository)

        trendingListRepository.signalEmptyData(true)
        viewModel.getTrendingGithubRepositoriesList()

        viewModel.trendingReposListingState.observeForever{
            renderedStates.add(it)
        }

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