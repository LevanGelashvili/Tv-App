package com.balevanciaga.tvapp.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.balevanciaga.tvapp.domain.model.TvShowDetails
import com.ramcosta.composedestinations.annotation.Destination

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
                details = it
            )
        }
    }
}

@Composable
private fun TvShowDetailsContent(
    details: TvShowDetails
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = details.name)
    }
}