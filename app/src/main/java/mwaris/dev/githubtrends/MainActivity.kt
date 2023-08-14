package mwaris.dev.githubtrends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import mwaris.dev.githubtrends.ui.theme.GithubTrendsTheme
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingScreen
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingViewModel
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesScreenState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: TrendingRepositoriesListingViewModel = viewModel()
            viewModel.getTrendingGithubRepositoriesList()

            GithubTrendsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    stringResource(R.string.title_trending),
                                    modifier = Modifier.testTag("top-app-bar")
                                )
                            })
                    }
                ) { contentPadding ->
                    Surface(
                        modifier = Modifier
                            .padding(contentPadding)
                            .fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {

                        val trendingListScreenState = viewModel.dataState.observeAsState()
                        val isOffline = viewModel.isOffline.collectAsState()

                        TrendingRepositoriesListingScreen(
                            isOffline = isOffline.value,
                            onRetry = { viewModel.getTrendingGithubRepositoriesList() },
                            isLoading = trendingListScreenState.value?.isLoading ?: false,
                            trendingRepositoriesScreenState = trendingListScreenState.value
                                ?: TrendingRepositoriesScreenState()
                        )
                    }
                }
            }
        }
    }
}