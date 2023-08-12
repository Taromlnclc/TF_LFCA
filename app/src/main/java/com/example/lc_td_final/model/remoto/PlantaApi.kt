package com.example.lc_td_final.model.remoto

import com.example.lc_td_final.model.local.entities.PlantasEntity
import com.example.lc_td_final.model.remoto.net.PlantaDeta
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlantaApi {
    @GET("/plantas")
    suspend fun getAllPlantas(): Response<List<PlantasEntity>>

    @GET("/plantas/{id}")
    suspend fun getPlantaById(@Path("id") id: Int): Response<PlantaDeta>
}