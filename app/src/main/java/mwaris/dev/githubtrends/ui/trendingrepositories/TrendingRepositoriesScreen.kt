package mwaris.dev.githubtrends.ui.trendingrepositories

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import mwaris.dev.githubtrends.data.entities.Repository
import mwaris.dev.githubtrends.ui.theme.GithubTrendsTheme

@Composable
fun TrendingRepositoryScreen(trendingRepositoriesList: List<Repository>) {
    LazyColumn(
        modifier = Modifier.testTag("trending-repositories-list")
    ) {
        items(trendingRepositoriesList,
            key = {
                it.id
            }) { repositoryItem ->
            TrendingRepositoryItem(repositoryItem)
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrendingRepositoriesListPreview() {

    val trendingRepositoriesList: List<Repository> = emptyList()

    GithubTrendsTheme {
        LazyColumn(
            modifier = Modifier.testTag("trending-repositories-list")
        ) {
            items(trendingRepositoriesList) { repositoryItem ->
                TrendingRepositoryItem(repositoryItem)
            }
        }
    }
}