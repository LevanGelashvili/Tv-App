package com.balevanciaga.tvapp.data.dataSources.remote.api

import com.balevanciaga.tvapp.data.dataSources.remote.dto.response.PopularShowsDto
import com.balevanciaga.tvapp.data.dataSources.remote.dto.response.TvGenreDto
import com.balevanciaga.tvapp.data.dataSources.remote.dto.response.TvShowDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowApi {

    @GET(ApiEndpoints.POPULAR)
    suspend fun getPopularShows(
        @Query("page") page: Int
    ): Response<PopularShowsDto>

    @GET("{tv_id}")
    suspend fun getShowDetails(
        @Path("tv_id") id: Int
    ): Response<TvShowDetailsDto>

    @GET(ApiEndpoints.TV_GENRES)
    suspend fun getTvGenres(): Response<TvGenreDto>
}