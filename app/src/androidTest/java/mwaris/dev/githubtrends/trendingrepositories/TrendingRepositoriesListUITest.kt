package mwaris.dev.githubtrends.trendingrepositories

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import mwaris.dev.githubtrends.R
import mwaris.dev.githubtrends.testdoubles.DummyRepositoriesData
import mwaris.dev.githubtrends.ui.theme.GithubTrendsTheme
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingScreen
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesScreenState
import org.junit.Rule
import org.junit.Test

class TrendingRepositoriesListUITest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val trendingRepositoriesList = DummyRepositoriesData.listOfTrendingRepositories

    @Test
    fun showsNetworkUnavailable() {

        composeTestRule.setContent {
            GithubTrendsTheme {
                TrendingRepositoriesListingScreen(
                    onRetry = {},
                    isOffline = true,
                    isLoading = false,
                    trendingRepositoriesScreenState = TrendingRepositoriesScreenState()
                )
            }
        }

        composeTestRule
            .onNodeWithTag("api-unreachable-image")
            .assertExists()

        composeTestRule
            .onNodeWithText("Something went wrong")
            .assertExists()

        composeTestRule
            .onNodeWithText("An alien is probably blocking your signal.")
            .assertExists()
    }

    @Test
    fun showsTrendingRepositoriesList() {

        composeTestRule.setContent {
            GithubTrendsTheme {
                TrendingRepositoriesListingScreen(
                    onRetry = {},
                    isOffline = false,
                    isLoading = false,
                    trendingRepositoriesScreenState = TrendingRepositoriesScreenState(
                        listOfRepositories = trendingRepositoriesList
                    )
                )
            }
        }

        composeTestRule
            .onNodeWithTag("trending-repositories-list")
            .assertExists()

        composeTestRule
            .onAllNodesWithContentDescription("Language Icon")
            .assertCountEquals(trendingRepositoriesList.size)

        composeTestRule
            .onAllNodesWithContentDescription("Star Icon")
            .assertCountEquals(trendingRepositoriesList.size)

    }

    @Test
    fun showsShimmerEffectInCaseOfLoadingData() {

        composeTestRule.setContent {
            GithubTrendsTheme {
                TrendingRepositoriesListingScreen(
                    onRetry = {},
                    isOffline = false,
                    isLoading = true,
                    trendingRepositoriesScreenState = TrendingRepositoriesScreenState(
                        listOfRepositories = trendingRepositoriesList
                    )
                )
            }
        }

        composeTestRule
            .onAllNodesWithTag("shimmer-loading-effect")
            .assertCountEquals(8)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun showsTopBarWithTextTrending() {
        composeTestRule.setContent {
            GithubTrendsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            modifier = Modifier.testTag("top-app-bar"),
                            title = {
                                Text(
                                    stringResource(R.string.title_trending)
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
                        TrendingRepositoriesListingScreen(
                            onRetry = {},
                            isOffline = false,
                            isLoading = true,
                            trendingRepositoriesScreenState = TrendingRepositoriesScreenState(
                                listOfRepositories = emptyList()
                            )
                        )
                    }
                }
            }
        }

        composeTestRule
            .onNodeWithTag("top-app-bar")
            .assertExists()

        composeTestRule
            .onNodeWithText("Trending")
            .assertExists()
    }

    @Test
    fun showsPullToRefresh() {
        composeTestRule.setContent {
            GithubTrendsTheme {
                TrendingRepositoriesListingScreen(
                    onRetry = {},
                    isOffline = false,
                    isLoading = false,
                    trendingRepositoriesScreenState = TrendingRepositoriesScreenState(
                        listOfRepositories = trendingRepositoriesList
                    )
                )
            }
        }

        composeTestRule
            .onNodeWithTag("pull-to-refresh")
            .assertExists()
    }
}