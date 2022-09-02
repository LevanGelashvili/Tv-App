package com.balevanciaga.tvapp.custom.base

interface BaseAction<Data> {
    fun updateData(previousData: Data): Data = previousData
}