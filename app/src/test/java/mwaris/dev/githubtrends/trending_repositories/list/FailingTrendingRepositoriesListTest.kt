package mwaris.dev.githubtrends.trending_repositories.list

import mwaris.dev.githubtrends.InstantExecutorExtension
import mwaris.dev.githubtrends.data.repositories.ITrendingListRepository
import mwaris.dev.githubtrends.ui.trending_repositories.list.TrendingRepositoriesListingState
import mwaris.dev.githubtrends.ui.trending_repositories.list.TrendingRepositoriesListingViewModel
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