package com.balevanciaga.tvapp.custom.managers

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateManager {

    private const val DEFAULT_PATTERN = "yyyy-MM-dd"

    fun strToDate(dateStr: String, pattern: String = DEFAULT_PATTERN): LocalDate? {
        return try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern))
        } catch (e: Exception) {
            null
        }
    }
}