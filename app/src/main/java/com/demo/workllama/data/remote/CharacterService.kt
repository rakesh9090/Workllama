package com.demo.workllama.data.remote


import com.demo.workllama.data.entities.CharacterList
import com.demo.workllama.data.entities.Characters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
    @GET("v1/contacts")
    suspend fun getAllCharacters(@Query("pageNumber") pageNumber: Int): Response<CharacterList>

    @POST("v1/star/{id}")
    suspend fun starCharacter(@Path("id") id: Int): Response<Characters>

    @POST("v1/unstar/{id}")
    suspend fun unStarCharacter(@Path("id") id: Int): Response<Characters>
}