package mwaris.dev.githubtrends.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mwaris.dev.githubtrends.R
import mwaris.dev.githubtrends.ui.theme.GithubTrendsTheme


@Composable
fun APIUnreachable(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
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
            text = stringResource(id = R.string.error_something_went_wrong),
            modifier = modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = stringResource(id = R.string.error_signal_blocking),
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(40.dp))
        OutlinedButton(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            shape = RectangleShape,
            onClick = { onRetry.invoke() }) {
            Text(
                text = stringResource(id = R.string.retry),
                letterSpacing = 3.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun APIUnreachablePreview() {
    GithubTrendsTheme {
        APIUnreachable({})
    }
}