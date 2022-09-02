package com.balevanciaga.tvapp.presentation.list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun TvShowListScreen(
    viewModel: TvShowListViewModel = hiltViewModel()
) {
    with(viewModel) {
        TvShowListContent(

        )
    }
}

@Composable
private fun TvShowListContent() {

}