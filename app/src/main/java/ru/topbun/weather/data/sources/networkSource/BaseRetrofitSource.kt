package ru.topbun.weather.data.sources.networkSource

import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import retrofit2.HttpException
import ru.topbun.weather.data.BackendException
import ru.topbun.weather.data.ParseBackendResponseException
import java.io.IOException
import java.net.ConnectException

open class BaseRetrofitSource {

    suspend fun <T>wrapRetrofitExceptions(block: suspend() -> T): T{
        return try {
            block()
        } catch (e: JsonIOException){
            throw ParseBackendResponseException()
        } catch (e: JsonParseException){
            throw ParseBackendResponseException()
        } catch (e: HttpException){
            throw createBackendException(e)
        } catch (e: IOException){
            throw ConnectException()
        }
    }

    private fun createBackendException(e: HttpException): Exception{
        return try {
            val errorBody = e.response()!!.errorBody()!!.string()
            val errorResponseBody = Gson().fromJson(errorBody, ErrorResponseBody::class.java)
            BackendException(
                name = errorResponseBody.message,
                code = errorResponseBody.cod
            )
        } catch (e: Exception){
            throw ParseBackendResponseException()
        }
    }

    data class ErrorResponseBody(
        val cod: Int,
        val message: String
    )

}