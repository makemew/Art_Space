package com.example.artspace

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.data.artworksList
import com.example.compose.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var step by rememberSaveable { mutableIntStateOf(0) }

        Painting(
            imageRes = artworksList[step].imageRes,
            contentDescription = artworksList[step].contentDescriptionRes,
        )
        PaintingDescription(
            artist = artworksList[step].artistRes,
            title = artworksList[step].titleRes,
            date = artworksList[step].dateRes,
        )

        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (step == 0) step = artworksList.size-1 else step-=1
                }
            ) {
                Text("Previous")
            }
            Button(
                onClick = {
                    if (step == artworksList.size-1) step = 0 else step+=1
                }
            ) {
                Text("Next")
            }
        }
    }
}

@Composable
fun Painting(
    imageRes: Int,
    contentDescription: Int,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(imageRes)
    val intrinsicSize = painter.intrinsicSize
    Box(
        modifier = modifier
            .fillMaxWidth(getContentWidth())
            .height(if (isTablet() && isScreenPortrait()) 624.dp else 546.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = stringResource(contentDescription),
            modifier = modifier
                .shadow(8.dp)
                .background(color = Color.White)
                .padding(24.dp)
                .aspectRatio(intrinsicSize.width/intrinsicSize.height)
        )
    }
}

@Composable
fun PaintingDescription(
    title: Int,
    artist: Int,
    date: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(getContentWidth())
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )
        Row {
            Text(
                text = stringResource(artist),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier.width(4.dp))
            Text(
                text = "(${stringResource(date)})",
                style = MaterialTheme.typography.titleSmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun isScreenPortrait(): Boolean {
    val orientation = LocalConfiguration.current.orientation
    return orientation == Configuration.ORIENTATION_PORTRAIT
}

@Composable
fun isTablet(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.smallestScreenWidthDp >= 600}

@Composable
fun getContentWidth(): Float {
    return if (isTablet() && isScreenPortrait()) 0.7f
    else if (isScreenPortrait()) 1f else 0.5f
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceLayout()
}
