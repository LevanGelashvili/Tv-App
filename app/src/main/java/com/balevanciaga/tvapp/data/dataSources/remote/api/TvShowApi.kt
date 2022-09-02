package com.balevanciaga.tvapp.data.dataSources.remote.api

import com.balevanciaga.tvapp.data.dataSources.remote.dto.response.PopularShowsDto
import retrofit2.Response
import retrofit2.http.GET

interface TvShowApi {

    @GET(ApiEndpoints.POPULAR)
    suspend fun getPopularShows(): Response<PopularShowsDto>
}