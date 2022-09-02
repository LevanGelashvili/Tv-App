package com.balevanciaga.tvapp.custom.data.apiHelper

/**
 * Abstract Network Error Response type
 */
sealed class ErrorResponse {
    open var errorCode: Int? = null
}

/**
 * Any error that has not been handled
 */
data class UnknownError(override var errorCode: Int?) : ErrorResponse()

/**
 * Used for calls that does not have specific error response type
 */
object NoError : ErrorResponse()