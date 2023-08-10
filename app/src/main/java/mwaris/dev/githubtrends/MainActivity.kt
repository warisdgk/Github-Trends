package mwaris.dev.githubtrends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    APIUnreachable("Something went wrong")
                }
            }
        }
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
fun GreetingPreview() {
    GithubTrendsTheme {
        APIUnreachable("Something went wrong")
    }
}