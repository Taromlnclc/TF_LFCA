package com.example.lc_td_final.model.remoto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{

        private const val BASE_URL ="https://my-json-server.typicode.com/mauricioponce/TDApi/"

        lateinit var  retrofitInstance : Retrofit

        fun retrofitInstance(): PlantaApi{

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return  retrofit.create(PlantaApi::class.java)
        }


    }
/*
    companion object{
        private val BASE_URL ="https://my-json-server.typicode.com/mauricioponce/TDApi/"
        fun retrofitInstance(): PlantaApi{

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return  retrofit.create(PlantaApi::class.java)
        }
    }
*/




}