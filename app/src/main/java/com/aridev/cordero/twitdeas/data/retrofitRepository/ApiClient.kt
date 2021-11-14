package com.aridev.cordero.twitdeas.data.retrofitRepository

import com.aridev.cordero.twitdeas.data.model.response.GetIdeasResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiClient {

    @GET()
    suspend fun getIdeas(
       @Url urlIdea : String
    ): Response<GetIdeasResponse>

}