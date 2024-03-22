package com.example.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.Amphibian
import com.example.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AmphibiansUiState.Success -> AmphibiansScreen(
            amphibiansUiState.amphibians,
            contentPadding = contentPadding,
            modifier = modifier.fillMaxWidth().padding(16.dp)
        )
        is AmphibiansUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The home screen displaying error message with re-attemp button.
 */
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun AmphibianCard(amphibian: Amphibian, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Text(
            text = "${amphibian.name} (${amphibian.type})",
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(amphibian.imgSrc)
                .crossfade(true).build(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.amphibians_photo),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = amphibian.description,  modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun AmphibiansScreen(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(4.dp)
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(
            items = amphibians,
            key = { amphibian -> amphibian.name }
        ) { item ->
            AmphibianCard(amphibian = item)
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun AmphibianCardPreview() {
    val amphibian = Amphibian(
        "Great Basin Spadefoot",
        "Toad",
        "This toad spends most of its life underground due to the arid desert " +
                "conditions in which it lives. Spadefoot toads earn the name because of their " +
                "hind legs which are wedged to aid in digging. They are typically grey, green, " +
                "or brown with dark spots.",
        "https://developer.android.com/codelabs/" +
                "basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png")
    AmphibiansTheme {
        AmphibianCard(amphibian)
    }
}*/

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AmphibiansTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AmphibiansTheme {
        ErrorScreen({})
    }
}