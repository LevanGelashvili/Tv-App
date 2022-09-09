package com.balevanciaga.tvapp.presentation.details

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.balevanciaga.tvapp.R
import com.balevanciaga.tvapp.custom.ext.limitLength
import com.balevanciaga.tvapp.domain.model.TvShowBrief
import com.balevanciaga.tvapp.domain.model.TvShowDetails
import com.balevanciaga.tvapp.main.ui.theme.Theme
import com.balevanciaga.tvapp.presentation.destinations.TvShowDetailsScreenDestination
import com.balevanciaga.tvapp.presentation.details.animation.TvShowDetailsScreenAnimation
import com.balevanciaga.tvapp.presentation.details.composables.GenreItem
import com.balevanciaga.tvapp.presentation.details.composables.RatingCircle
import com.google.accompanist.flowlayout.FlowRow
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDate

@Destination(style = TvShowDetailsScreenAnimation::class)
@Composable
fun TvShowDetailsScreen(
    id: Int,
    navigator: DestinationsNavigator,
    viewModel: TvShowDetailsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.postAction(TvShowDetailsAction.FetchShowDetails(id = id))
    }
    with(viewModel) {
        viewState.details?.let {
            TvShowDetailsContent(
                details = it,
                similarShows = viewState.similarShows,
                onSimilarShowClicked = { similarShowId ->
                    navigator.navigate(
                        direction = TvShowDetailsScreenDestination(id = similarShowId)
                    )
                }
            )
        }
    }
}

@Composable
private fun TvShowDetailsContent(
    details: TvShowDetails,
    similarShows: List<TvShowBrief>,
    onSimilarShowClicked: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
    ) {
        with(details) {
            BackdropImage(posterUrl = backdropUrl)
            NameAndRating(name = name, rating = rating)
            TextDetails(
                firstAirDate = firstAirDate,
                lastAirDate = lastAirDate,
                numSeasons = numSeasons,
                numEpisodes = numEpisodes,
                overview = overview,
                createdBy = createdBy
            )
            GenreList(genres = genres)
            if (similarShows.isNotEmpty()) {
                ScrollableSimilarShows(
                    similarShows = similarShows,
                    onSimilarShowClicked = onSimilarShowClicked
                )
            }
        }
    }
}

@Composable
private fun NameAndRating(
    name: String,
    rating: Float
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name.limitLength(maxChars = 30),
            color = Theme.colors.onBackground,
            style = Theme.typography.black20
        )
        RatingCircle(rating = rating)
    }
}

@Composable
private fun TextDetails(
    firstAirDate: LocalDate?,
    lastAirDate: LocalDate?,
    numSeasons: Int,
    numEpisodes: Int,
    overview: String,
    createdBy: List<String>
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (firstAirDate != null) {
                Text(
                    text = formatAirDate(
                        firstAirDate = firstAirDate,
                        lastAirDate = lastAirDate
                    ),
                    color = Theme.colors.primary,
                    style = Theme.typography.medium14
                )
            }
            Text(
                text = formatSeasonsAndEpisodes(
                    numSeasons = numSeasons,
                    numEpisodes = numEpisodes
                ),
                color = Theme.colors.onBackground,
                style = Theme.typography.medium14
            )
        }
        Text(
            modifier = Modifier.padding(bottom = 12.dp),
            text = overview,
            color = Theme.colors.onBackground,
            style = Theme.typography.medium14
        )
        if (createdBy.isNotEmpty()) {
            Text(
                text = formatCreatedBy(createdBy = createdBy),
                color = Theme.colors.primary,
                style = Theme.typography.normal12
            )
        }
    }
}

@Composable
private fun GenreList(
    genres: List<String>
) {
    FlowRow(
        mainAxisSpacing = 10.dp,
        crossAxisSpacing = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        genres.forEach {
            GenreItem(genre = it)
        }
    }
}

@Composable
private fun ColumnScope.BackdropImage(
    posterUrl: String?
) {
    Image(
        modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally)
            .fillMaxWidth()
            .height(250.dp)
            .clip(shape = Theme.shapes.roundedBackdropImage),
        painter = rememberAsyncImagePainter(model = posterUrl),
        contentScale = ContentScale.FillWidth,
        contentDescription = null
    )
}

@Composable
private fun ScrollableSimilarShows(
    similarShows: List<TvShowBrief>,
    onSimilarShowClicked: (Int) -> Unit,
    posterHeight: Dp = 240.dp
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {
        Text(
            text = stringResource(R.string.similar_shows),
            color = Theme.colors.onBackground,
            style = Theme.typography.bold16
        )
        Row(
            modifier = Modifier
                .height(posterHeight)
                .padding(top = 4.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            similarShows.forEach {
                Image(
                    modifier = Modifier
                        .width(posterHeight / 2)
                        .padding(4.dp)
                        .clickable {
                            onSimilarShowClicked(it.id)
                        },
                    painter = rememberAsyncImagePainter(model = it.posterUrl),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
            }
        }
    }
}

private fun formatAirDate(
    firstAirDate: LocalDate?,
    lastAirDate: LocalDate?
): String {
    val firstYear = firstAirDate?.year
    val lastYear = lastAirDate?.year
    return if (firstYear == lastYear) {
        "$firstYear"
    } else {
        "$firstYear-$lastYear"
    }
}

@Composable
private fun formatSeasonsAndEpisodes(
    numSeasons: Int,
    numEpisodes: Int
) = buildString {
    append(numSeasons)
    append(" ")
    append(stringResource(id = R.string.seasons))
    append(" / ")
    append(numEpisodes)
    append(" ")
    append(stringResource(id = R.string.episodes))
}

@Composable
private fun formatCreatedBy(
    createdBy: List<String>
) = buildString {
    append(stringResource(R.string.created_by))
    append(": ")
    append(createdBy.joinToString(separator = ", ") { it })
}