package com.balevanciaga.tvapp.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.balevanciaga.tvapp.domain.model.TvShowBrief
import com.balevanciaga.tvapp.domain.model.TvShowDetails
import com.balevanciaga.tvapp.main.ui.theme.Theme
import com.ramcosta.composedestinations.annotation.Destination
import java.time.LocalDate

@Destination
@Composable
fun TvShowDetailsScreen(
    id: Int,
    viewModel: TvShowDetailsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.postAction(TvShowDetailsAction.FetchShowDetails(id = id))
    }
    with(viewModel) {
        viewState.details?.let {
            TvShowDetailsContent(
                details = it,
                similarShows = viewState.similarShows
            )
        }
    }
}

@Composable
private fun TvShowDetailsContent(
    details: TvShowDetails,
    similarShows: List<TvShowBrief>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        BackdropImage(posterUrl = details.backdropUrl)
        Details(details = details)
    }
}

@Composable
private fun Details(
    details: TvShowDetails
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = details.name,
            color = Theme.colors.onBackground,
            style = Theme.typography.black18
        )
        Text(
            text = airDateString(
                firstAirDate = details.firstAirDate,
                lastAirDate = details.lastAirDate
            ),
            color = Theme.colors.onBackground,
            style = Theme.typography.normal12
        )
        Text(
            text = "Created by: " + createdByString(createdBy = details.createdBy),
            color = Theme.colors.onBackground,
            style = Theme.typography.normal12
        )
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
            .clip(shape = Theme.shapes.roundedDefault),
        painter = rememberAsyncImagePainter(model = posterUrl),
        contentScale = ContentScale.FillWidth,
        contentDescription = null
    )
}

@Composable
private fun ScrollableSimilarShows(
    similarShows: List<TvShowBrief>
) {
    LazyRow {
        items(similarShows) {
            Image(
                modifier = Modifier
                    .width(120.dp)
                    .height(240.dp)
                    .padding(4.dp),
                painter = rememberAsyncImagePainter(model = it.posterUrl),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
        }
    }
}

private fun airDateString(
    firstAirDate: LocalDate?,
    lastAirDate: LocalDate?
): String {
    val firstYear = firstAirDate?.year
    val lastYear = lastAirDate?.year
    return if (firstYear == lastYear) {
        "$firstYear-"
    } else {
        "$firstYear-$lastYear"
    }
}

private fun createdByString(
    createdBy: List<String>
): String {
    return createdBy.joinToString(separator = ", ") { it }
}