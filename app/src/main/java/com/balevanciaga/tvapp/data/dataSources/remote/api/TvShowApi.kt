package com.balevanciaga.tvapp.data.dataSources.remote.api

import com.balevanciaga.tvapp.data.dataSources.remote.dto.response.PopularShowsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowApi {

    @GET(ApiEndpoints.POPULAR)
    suspend fun getPopularShows(
        @Query("page") page: Int
    ): Response<PopularShowsDto>
}