package com.example.artspace

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
import androidx.compose.foundation.layout.safeDrawingPadding
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val artworks = artworksList
        var step by remember { mutableIntStateOf(1) }

        when (step) {
            1 -> {
                Painting(
                    painterResource = R.drawable.girl_with_a_pearl_earring  ,
                    contentDescription = R.string.content_description_girl_with_earring,
                )
                PaintingDescription(
                    artistName = R.string.artist_girl_with_earring,
                    paintingName = R.string.title_girl_with_earring,
                    date = R.string.date_girl_with_earring,
                )
            }
            2 -> {
                Painting(
                    painterResource = R.drawable.the_son_of_man,
                    contentDescription = R.string.content_description_the_son_of_man
                )
                PaintingDescription(
                    paintingName = R.string.title_the_son_of_man,
                    artistName = R.string.artist_the_son_of_man,
                    date = R.string.date_the_son_of_man
                )
            }
            3 -> {
                Painting(
                    painterResource = R.drawable.woman_with_a_parasol,
                    contentDescription = R.string.content_description_woman_with_a_parasol
                )
                PaintingDescription(
                    paintingName = R.string.title_woman_with_a_parasol,
                    artistName = R.string.artist_woman_with_a_parasol,
                    date = R.string.date_woman_with_a_parasol
                )
            }
        }

        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {

                }
            ) {
                Text("Previous")
            }
            Button(
                onClick = {
                    step = if (step == 1) 2 else 1                }
            ) {
                Text("Next")
            }
        }
    }
}

@Composable
fun Painting(
    painterResource: Int,
    contentDescription: Int,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(painterResource)
    val intrinsicSize = painter.intrinsicSize
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(512.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = stringResource(contentDescription),
            modifier = modifier
                .shadow(8.dp)
                .background(color = Color.White)
                .padding(24.dp)
                .aspectRatio(intrinsicSize.width/ intrinsicSize.height)
        )
    }
}


@Composable
fun PaintingDescription(
    paintingName: Int,
    artistName: Int,
    date: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(paintingName),
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )
        Row {
            Text(
                text = stringResource(artistName),
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

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceLayout()
}
