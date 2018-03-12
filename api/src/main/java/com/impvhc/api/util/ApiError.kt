package com.impvhc.api.util

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by victor on 2/13/18.
 */
class ApiError(
        var code: String = "",
        var status: Int = 0,
        @SerializedName("ErrorStatus") var errorStatus: String = "",
        @SerializedName("ErrorMessage") var message: ApiErrors = ApiErrors.GENERIC
) {

    companion object {
        val NO_INTERNET_STATUS = -1

        fun generateError(apiError: ApiErrors): ApiError = ApiError(message = apiError)

        fun generateError(throwable: Throwable): ApiError {
            var apiError: ApiError

            if (throwable is RetrofitException) {
                val error = throwable

                if (error.response == null || error.kind == RetrofitException.Kind.NETWORK) {
                    apiError = ApiError(status = NO_INTERNET_STATUS, message = error.message?.let { ApiErrors.valueOf(it) } ?: ApiErrors.GENERIC)
                } else {
                    try {
                        apiError = error.getErrorBodyAs<ApiError>(ApiError::class.java)
                        apiError.status = error.response.code()
                    } catch (e: IOException) {
                        apiError = ApiError()
                        apiError.message = throwable.message?.let { ApiErrors.valueOf(it) } ?: ApiErrors.GENERIC
                    }
                }
            } else if (throwable is HttpException) {
                val gson = Gson()

                val messageList = gson.fromJson(throwable.response().errorBody()?.string(), Array<ApiErrors>::class.java)

                val message = if (messageList[0] == null) ApiErrors.GENERIC else messageList[0]

                apiError = ApiError(message = message)
            } else {
                apiError = ApiError(message = ApiErrors.GENERIC)
            }

            return apiError
        }
    }
}