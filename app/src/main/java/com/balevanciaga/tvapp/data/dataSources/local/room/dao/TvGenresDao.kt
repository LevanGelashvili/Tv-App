package com.balevanciaga.tvapp.data.dataSources.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.balevanciaga.tvapp.data.dataSources.local.room.entity.TvGenreEntity
import com.balevanciaga.tvapp.domain.model.TvGenre

@Dao
interface TvGenresDao {

    @Query("select * from TV_GENRES")
    fun getTvGenres(): List<TvGenreEntity>

    @Insert
    fun insertTvGenres(genres: List<TvGenreEntity>)
}