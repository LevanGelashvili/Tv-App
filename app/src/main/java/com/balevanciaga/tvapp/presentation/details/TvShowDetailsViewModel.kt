package com.balevanciaga.tvapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.balevanciaga.tvapp.custom.base.BaseViewModel
import com.balevanciaga.tvapp.domain.repository.ITvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowDetailsViewModel @Inject constructor(
    private val tvShowRepository: ITvShowRepository
) : BaseViewModel<TvShowDetailsAction, TvShowDetailsViewState>() {

    override var viewState: TvShowDetailsViewState by mutableStateOf(TvShowDetailsViewState())

    override fun onActionReceived(action: TvShowDetailsAction) {
        when (action) {
            is TvShowDetailsAction.FetchShowDetails -> {
                execute {
                    viewState = viewState.copy(
                        details = tvShowRepository.getShowDetails(id = action.id),
                        similarShows = tvShowRepository.getSimilarShows(id = action.id)
                    )
                }
            }
        }
    }
}