package mwaris.dev.githubtrends.trendingrepositories

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
    fun failingBackendError() {
        val trendingListRepository = UnavailableTrendingRepositoryListRepository()
        val viewModel = TrendingRepositoriesListingViewModel(trendingListRepository)

        viewModel.getTrendingGithubRepositoriesList()

        Assertions.assertEquals(
            TrendingRepositoriesListingState.BackendError,
            viewModel.trendingReposListingState.value
        )
    }

    class UnavailableTrendingRepositoryListRepository : ITrendingListRepository {
        override fun getTrendingGithubRepositoriesList(): TrendingRepositoriesListingState {
            return TrendingRepositoriesListingState.BackendError
        }

    }

}