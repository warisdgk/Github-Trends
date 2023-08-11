package mwaris.dev.githubtrends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import mwaris.dev.githubtrends.ui.theme.GithubTrendsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubTrendsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn(
                        modifier = Modifier.testTag("trending-repositories-list")
                    ) {
                        items(count = 15) {
                            TrendingRepositoryItem()
                        }
                    }
                    APIUnreachable("Something went wrong")
                }
            }
        }
    }
}

@Composable
private fun TrendingRepositoryItem() {
    Row(
        Modifier
            .padding(all = 15.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = "https://avatars.githubusercontent.com/u/4314092?v=4",
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
            Text("go")
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                "golang/go"
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text("The Go programming language")
        }
    }
}

@Composable
fun ShimmerLoadingItem() {
    Row(
        modifier = Modifier
            .padding(all = 15.dp)
            .fillMaxWidth()
            .testTag("shimmer-loading-effect"), verticalAlignment = Alignment.Top
    ) {
    }
}

@Composable
fun APIUnreachable(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.network_error),
            contentDescription = "Api unreachable",
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .testTag("api-unreachable-image")
        )
        Text(
            text = name,
            modifier = modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "An alien is probably blocking your signal.",
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(40.dp))
        OutlinedButton(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            shape = RectangleShape,
            onClick = { /*TODO*/ }) {
            Text(
                text = "RETRY",
                letterSpacing = 3.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun APIUnreachablePreview() {
    GithubTrendsTheme {
        APIUnreachable("Something went wrong")
    }
}

@Preview(showBackground = true)
@Composable
fun TrendingRepositoriesListPreview() {
    GithubTrendsTheme {
        LazyColumn(
            modifier = Modifier.testTag("trending-repositories-list")
        ) {
            items(count = 15) {
                TrendingRepositoryItem()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShimmerLoadingItemPreview() {
    GithubTrendsTheme {
        ShimmerLoadingItem()
    }
}