package com.balevanciaga.tvapp.data.dataSources.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.balevanciaga.tvapp.data.dataSources.local.room.dao.TvGenresDao
import com.balevanciaga.tvapp.data.dataSources.local.room.entity.TvGenreEntity

@Database(
    entities = [TvGenreEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TvShowDatabase: RoomDatabase() {

    abstract fun tvGenresDao(): TvGenresDao
}