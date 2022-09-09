package com.balevanciaga.tvapp.data.dataSource.remote.api

import com.balevanciaga.tvapp.data.dataSource.remote.dto.response.TvShowDetailsDto
import com.balevanciaga.tvapp.data.dataSource.remote.dto.response.TvShowListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowApi {

    @GET(ApiEndpoints.POPULAR)
    suspend fun getPopularShows(
        @Query("page") page: Int
    ): Response<TvShowListDto>

    @GET("{tv_id}/" + ApiEndpoints.SIMILAR)
    suspend fun getSimilarShows(
        @Path("tv_id") id: Int
    ): Response<TvShowListDto>

    @GET("{tv_id}")
    suspend fun getShowDetails(
        @Path("tv_id") id: Int
    ): Response<TvShowDetailsDto>
}