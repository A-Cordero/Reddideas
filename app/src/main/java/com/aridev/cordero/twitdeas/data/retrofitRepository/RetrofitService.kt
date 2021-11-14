package com.aridev.cordero.twitdeas.data.retrofitRepository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketException


class RetrofitService : KoinComponent {
    private val api : ApiClient by inject()

    operator fun <T> invoke(method: Response<T>, callback: (success: T?, error: String?) -> Unit) =
        CoroutineScope(Dispatchers.IO).launch {
            api.apply {
                withContext(Dispatchers.Main) {
                    try{
                        if( method.isSuccessful) {
                            callback.invoke(method.body(), null)
                        } else  {
                            when(method.code()) {
                                401 -> callback.invoke(null,"Credenciales incorrectas.")
                                404 -> callback.invoke(null,"404 algo salio mal.")
                            }
                        }
                    }catch (throwable : Throwable) {
                        when (throwable) {
                            is IOException -> callback.invoke(null,"Sin conexion a internet")
                            is HttpException -> {
                                val code = throwable.code()
                                callback.invoke(null,code.toString())
                            }
                            is SocketException -> {
                                callback.invoke(null,"Mala conexion")
                            }
                            else -> {
                                callback.invoke(null,"Ha ocurrido un error, intentelo nuevamente.")
                            }
                        }
                    }
                }
            }
        }
}