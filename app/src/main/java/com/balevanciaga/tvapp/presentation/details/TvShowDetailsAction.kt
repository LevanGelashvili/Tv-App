package com.balevanciaga.tvapp.presentation.details

import com.balevanciaga.tvapp.custom.base.BaseAction

sealed class TvShowDetailsAction : BaseAction<TvShowDetailsViewState> {

    data class FetchShowDetails(val id: Int) : TvShowDetailsAction()
}
