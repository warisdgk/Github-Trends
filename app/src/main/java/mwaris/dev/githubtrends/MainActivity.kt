package mwaris.dev.githubtrends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import mwaris.dev.githubtrends.ui.theme.GithubTrendsTheme
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingScreen
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingViewModel
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesScreenState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: TrendingRepositoriesListingViewModel = viewModel()
            viewModel.getTrendingGithubRepositoriesList()

            GithubTrendsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val trendingListScreenState = viewModel.dataState.observeAsState()

                    TrendingRepositoriesListingScreen(
                        isLoading = trendingListScreenState.value?.isLoading ?: false,
                        trendingRepositoriesScreenState = trendingListScreenState.value
                            ?: TrendingRepositoriesScreenState()
                    )
                }
            }
        }
    }
}