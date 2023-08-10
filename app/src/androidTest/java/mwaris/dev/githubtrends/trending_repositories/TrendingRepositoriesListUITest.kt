package mwaris.dev.githubtrends.trending_repositories

import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import mwaris.dev.githubtrends.APIUnreachable
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
            .onNodeWithText("Something went wrong")
            .assertExists()
    }
}