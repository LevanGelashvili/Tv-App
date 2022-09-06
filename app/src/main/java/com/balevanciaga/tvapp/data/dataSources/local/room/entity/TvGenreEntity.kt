package com.balevanciaga.tvapp.data.dataSources.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.balevanciaga.tvapp.domain.model.TvGenre

const val TV_GENRES = "tv_genres"

@Entity(tableName = TV_GENRES)
data class TvGenreEntity(
    @PrimaryKey(autoGenerate = true) val dbId: Int? = null,
    val id: Int,
    val name: String
) {

    fun toDomain(): TvGenre = TvGenre(
        id = id,
        name = name
    )

    fun TvGenre.toEntity() = TvGenreEntity(
        id = id,
        name = name
    )
}