package com.balevanciaga.tvapp.custom.data.apiHelper

/**
 * General Exceptions that can happen inside application
 */
sealed class GeneralException : Exception() {
    object NoInternetException : GeneralException()
    object TimeOutException : GeneralException()
    object UnauthorizedException : GeneralException()
    object NotFoundException : GeneralException()
    data class UnknownException(val code: Int) : GeneralException()
}

/**
 * Specific error wrapper exception
 */
open class ResponseException(val error: ErrorResponse) : Exception()

fun generateExceptionByErrorBody(errorBody: ApiHelper.ErrorBody): Exception {
    return when (errorBody.code) {
        401 -> GeneralException.UnauthorizedException
        404 -> GeneralException.NotFoundException
        else -> GeneralException.UnknownException(code = errorBody.code)
    }
}