package com.balevanciaga.tvapp.custom.data.apiHelper

import com.squareup.moshi.Moshi
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.io.InterruptedIOException

@Suppress("BlockingMethodInNonBlockingContext")
object ApiHelper {

    /**
     * Invokes [networkCall] which doesn't have a specific error response,
     * so [NoError] class is used as error type
     */
    suspend fun <Result> makeApiCall(networkCall: suspend () -> Response<Result>): Result {
        return makeApiCallWithError<Result, NoError> {
            networkCall()
        }
    }

    /**
     * Invokes [networkCall] which can return error with json body,
     * if error type is not [NoError], deserializes json body to [Error] type model and throws
     * [ResponseException]
     * if not, generates [GeneralException] by error code
     */
    private suspend inline fun <Result, reified Error : ErrorResponse> makeApiCallWithError(
        crossinline networkCall: suspend () -> Response<Result>
    ): Result {
        val result = apiCall(
            networkCall = { networkCall() },
            onError = { errorBody ->
                if (Error::class != NoError::class && errorBody.body != null) {
                    Moshi.Builder()
                        .build()
                        .adapter(Error::class.java)
                        .fromJson(errorBody.body)?.let {
                            throw ResponseException(error = it)
                        }
                }
                throw generateExceptionByErrorBody(errorBody)
            }
        )
        return result!!
    }

    /**
     * Tries to call [networkCall] and if it is successful returns result,
     * handles and throws [GeneralException]
     * Invokes [onError] if general exceptions was not caught and result is not successful
     */
    private suspend fun <Result> apiCall(
        networkCall: suspend () -> Response<Result>,
        onError: suspend (ErrorBody) -> Unit
    ): Result? {
        val result = try {
            networkCall()
        } catch (e: HttpException) {
            throw GeneralException.NoInternetException
        } catch (e: IOException) {
            throw GeneralException.NoInternetException
        } catch (e: InterruptedIOException) {
            throw GeneralException.TimeOutException
        }

        val body = result.body()
        if (result.isSuccessful && body != null) {
            return body
        }
        onError(ErrorBody(code = result.code(), body = result.errorBody()?.string()))
        return null
    }

    data class ErrorBody(val code: Int, val body: String?)
}