package com.balevanciaga.tvapp.custom.ext

fun String.limitLength(
    maxChars: Int
): String {
    return if (length > maxChars) {
        substring(startIndex = 0, endIndex = maxChars) + "…"
    } else {
        this
    }
}