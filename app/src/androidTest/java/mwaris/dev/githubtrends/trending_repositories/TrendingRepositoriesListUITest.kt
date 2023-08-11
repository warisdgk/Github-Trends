package mwaris.dev.githubtrends.trending_repositories

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import mwaris.dev.githubtrends.ui.composables.APIUnreachable
import mwaris.dev.githubtrends.ui.composables.LoadingShimmerEffect
import mwaris.dev.githubtrends.ui.theme.GithubTrendsTheme
import org.junit.Rule
import org.junit.Test

class TrendingRepositoriesListUITest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun showsSomethingWentWrongMessage() {
        composeTestRule.setContent {
            GithubTrendsTheme {
                APIUnreachable("Something went wrong")
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn(
                        modifier = Modifier.testTag("trending-repositories-list")
                    ) {
                        items(count = 15) {
                            Text(text = "Trending Repo")
                        }
                    }
                    APIUnreachable("Something went wrong")
                }
            }
        }

        composeTestRule
            .onNodeWithTag("trending-repositories-list")
            .assertExists()
    }

    @Test
    fun showsShimmerEffectInCaseOfLoadingData() {
        composeTestRule.setContent {
            Column {
                repeat(8) {
                    LoadingShimmerEffect()
                }
            }
        }

        composeTestRule
            .onAllNodesWithTag("shimmer-loading-effect")
            .assertCountEquals(8)
    }
}