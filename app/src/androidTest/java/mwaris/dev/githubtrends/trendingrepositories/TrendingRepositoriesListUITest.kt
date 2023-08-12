package mwaris.dev.githubtrends.trendingrepositories

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import mwaris.dev.githubtrends.data.entities.Repository
import mwaris.dev.githubtrends.ui.composables.APIUnreachable
import mwaris.dev.githubtrends.ui.composables.LoadingShimmerEffect
import mwaris.dev.githubtrends.ui.theme.GithubTrendsTheme
import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoryScreen
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
                TrendingRepositoryScreen(trendingRepositoriesList = trendingRepositoriesList)
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