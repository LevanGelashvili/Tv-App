package com.balevanciaga.tvapp.presentation.list

import com.balevanciaga.tvapp.custom.base.BaseAction

sealed class TvShowListAction : BaseAction<TvShowListViewState> {

    object LoadMore : TvShowListAction()

    class OnFilter(private val filter: String) : TvShowListAction() {

        override fun updateData(previousData: TvShowListViewState): TvShowListViewState {
            return previousData.copy(query = filter)
        }
    }
}
