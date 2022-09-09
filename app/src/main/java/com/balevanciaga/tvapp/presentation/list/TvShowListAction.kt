package com.balevanciaga.tvapp.presentation.list

import com.balevanciaga.tvapp.custom.base.BaseAction

sealed class TvShowListAction : BaseAction<TvShowListViewState> {

    object LoadMore : TvShowListAction()

    data class OnFilter(val query: String) : TvShowListAction() {

        override fun updateData(previousData: TvShowListViewState): TvShowListViewState {
            return previousData.copy(query = query)
        }
    }
}
