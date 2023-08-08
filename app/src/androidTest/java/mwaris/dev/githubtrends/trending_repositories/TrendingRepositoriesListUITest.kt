package mwaris.dev.githubtrends.trending_repositories

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import mwaris.dev.githubtrends.Greeting
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
                Greeting("Android")
            }
        }

        composeTestRule
            .onNodeWithText("Something went wrong")
            .assertExists()
    }
}