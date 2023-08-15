package mwaris.dev.githubtrends.ui.trendingrepositories

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import mwaris.dev.githubtrends.R
import mwaris.dev.githubtrends.data.entities.Repository
import mwaris.dev.githubtrends.testdoubles.DummyRepositoriesData
import mwaris.dev.githubtrends.ui.composables.APIUnreachable
import mwaris.dev.githubtrends.ui.composables.LoadingShimmerEffect
import mwaris.dev.githubtrends.ui.theme.GithubTrendsTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrendingRepositoriesListingScreen(
    isOffline: Boolean,
    onRetry: () -> Unit,
    isLoading: Boolean,
    trendingRepositoriesScreenState: TrendingRepositoriesScreenState
) {
    val pullRefreshingState = rememberPullRefreshState(isLoading, { onRetry.invoke() })
    if (isOffline) {
        APIUnreachable(onRetry)
    } else {
        if (isLoading) {
            Column {
                repeat(8) {
                    LoadingShimmerEffect()
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .pullRefresh(pullRefreshingState)
                    .testTag("pull-to-refresh")
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.testTag("trending-repositories-list")
                ) {
                    items(trendingRepositoriesScreenState.listOfRepositories,
                        key = {
                            it.id
                        }) { repositoryItem ->
                        TrendingRepositoryItem(repositoryItem)
                    }
                }
                PullRefreshIndicator(
                    false,
                    pullRefreshingState,
                    Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }
}

@Composable
fun TrendingRepositoryItem(
    repositoryInfo: Repository
) {
    Row(
        Modifier
            .padding(all = 15.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = repositoryInfo.avatarUrl,
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )
        Column(
            Modifier
                .padding(start = 15.dp)
                .fillMaxWidth()
        ) {
            Text(repositoryInfo.name)
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                repositoryInfo.fullName
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(repositoryInfo.description)
            Spacer(modifier = Modifier.size(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                if (repositoryInfo.language.isNotEmpty()){
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(R.drawable.ic_language),
                        contentDescription = "Language Icon"
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(repositoryInfo.language)
                    Spacer(modifier = Modifier.size(15.dp))
                }
                if (repositoryInfo.stargazersCount.isNotEmpty()){
                    Image(
                        modifier = Modifier
                            .size(25.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(R.drawable.ic_star), contentDescription = "Star Icon"
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(repositoryInfo.stargazersCount.toString())
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrendingRepositoriesListPreview() {

    GithubTrendsTheme {
        TrendingRepositoriesListingScreen(
            isOffline = false,
            onRetry = {},
            isLoading = false,
            trendingRepositoriesScreenState = TrendingRepositoriesScreenState(
                listOfRepositories = DummyRepositoriesData.listOfTrendingRepositories
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TrendingRepositoriesLoadingPreview() {

    GithubTrendsTheme {
        TrendingRepositoriesListingScreen(
            isOffline = false,
            onRetry = {},
            isLoading = true,
            trendingRepositoriesScreenState = TrendingRepositoriesScreenState(
                listOfRepositories = emptyList()
            )
        )
    }
}