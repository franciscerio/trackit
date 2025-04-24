package com.fcerio.features.tracks.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.fcerio.core.common.compose.AppTheme
import com.fcerio.core.common.compose.TrackitTheme
import com.fcerio.core.common.compose.components.CardComposable
import com.fcerio.core.common.compose.components.TextComposable
import com.fcerio.core.common.compose.dimensions.Margins

// Your StringPreviewParameterProvider
class SongCardPreviewParameterProvider : PreviewParameterProvider<SongCardUIModel> {

    override val values: Sequence<SongCardUIModel> = sequenceOf(
        SongCardUIModel(
            id = 0,
            imageURL = "https://www.kasandbox.org/programming-images/avatars/cs-hopper-cool.png",
            price = 1.50,
            songTitle = "The Weekend",
            songArtist = "Star Boy",
            shortDescription = "Short description test",
            longDescription = "Long description test",
            isFavorite = false
        )
    )
}

@Composable
@Preview(showBackground = true)
fun SongCard(
    @PreviewParameter(SongCardPreviewParameterProvider::class) model: () -> SongCardUIModel,
    onClicked: ((SongCardUIModel) -> Unit)? = null,
    onFavoriteClicked: ((SongCardUIModel) -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Margins.MICRO)
            .clickable {
                onClicked?.invoke(model())
            }
    ) {
        CardComposable(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .matchParentSize()
                        .aspectRatio(1f),
                    contentScale = ContentScale.FillHeight,
                    contentDescription = null,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(model().imageURL)
                        .crossfade(true)
                        .build(),
                    loading = {
                        CircularProgressIndicator(
                            modifier = Modifier.size(Margins.XX_LARGE)
                        )
                    },
                    error = {
                        if (LocalInspectionMode.current) {
                            Image(
                                painter = painterResource(R.drawable.sample_image),
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = null,
                                tint = Color.Red
                            )
                        }
                    }
                )

                HeartView(
                    modifier = Modifier
                        .padding(Margins.LARGE)
                        .align(Alignment.TopEnd),
                    isFavorite = model().isFavorite,
                    onClicked = {
                        onFavoriteClicked?.invoke(model())
                    }
                )
            }
        }

        TextComposable(
            modifier = Modifier.padding(
                start = Margins.MICRO,
                end = Margins.MICRO,
                top = Margins.LARGE
            ),
            style = AppTheme.typography.body1Bold,
            title = stringResource(R.string.price_format, model().price),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        TextComposable(
            modifier = Modifier.padding(
                start = Margins.MICRO,
                end = Margins.MICRO,
                top = Margins.MICRO
            ),
            style = AppTheme.typography.subHeadline,
            title = model().songTitle,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        TextComposable(
            modifier = Modifier.padding(
                start = Margins.MICRO,
                end = Margins.MICRO,
                top = Margins.MICRO
            ),
            style = AppTheme.typography.subHeadline,
            title = model().songArtist,
            textColor = AppTheme.colors.textTertiary
        )
    }
}

@Composable
fun HeartView(
    modifier: Modifier,
    isFavorite: Boolean = false,
    onClicked: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .background(color = Color.White, shape = CircleShape)
            .border(1.dp, color = AppTheme.colors.neutralsStroke, shape = CircleShape)
            .clip(CircleShape)
            .clickable {
                onClicked?.invoke()
            }
    ) {
        Image(
            modifier = Modifier.padding(Margins.SMALL),
            painter = painterResource(id = if (isFavorite) R.drawable.ic_wishlist_selected else R.drawable.ic_wishlist),
            contentDescription = "Wish list image"
        )
    }
}
