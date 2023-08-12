package mwaris.dev.githubtrends.trendingrepositories

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import mwaris.dev.githubtrends.data.entities.Repository
import mwaris.dev.githubtrends.ui.composables.APIUnreachable
import mwaris.dev.githubtrends.ui.composables.LoadingShimmerEffect
import mwaris.dev.githubtrends.ui.theme.GithubTrendsTheme
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoryItem
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

        val trendingRepositoriesList: List<Repository> = listOf(
            Repository(
                "23096959",
                "go",
                "golang/go",
                "https://avatars.githubusercontent.com/u/4314092?v=4",
                "The Go programming language"
            )
        )

        composeTestRule.setContent {
            GithubTrendsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn(
                        modifier = Modifier.testTag("trending-repositories-list")
                    ) {
                        items(trendingRepositoriesList,
                            key = {
                                it.id
                            }
                        ) { repositoryItem ->
                            TrendingRepositoryItem(repositoryItem)
                        }
                    }
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