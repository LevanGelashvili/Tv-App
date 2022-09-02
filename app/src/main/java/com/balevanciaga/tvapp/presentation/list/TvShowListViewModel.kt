package com.balevanciaga.tvapp.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.balevanciaga.tvapp.custom.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowListViewModel @Inject constructor(

) : BaseViewModel<TvShowListAction, TvShowListViewState>() {

    override var viewState: TvShowListViewState by mutableStateOf(TvShowListViewState())

    init {
        execute {

        }
    }

    override fun onActionReceived(action: TvShowListAction) {
        when (action) {

        }
    }
}