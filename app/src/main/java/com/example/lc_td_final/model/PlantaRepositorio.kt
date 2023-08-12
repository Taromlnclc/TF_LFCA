package com.example.lc_td_final.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.lc_td_final.model.local.PlantaDao
import com.example.lc_td_final.model.local.entities.PlantaDetaEntity
import com.example.lc_td_final.model.remoto.RetrofitClient
import com.example.lc_td_final.model.remoto.net.PlantaDeta
import retrofit2.Retrofit
class PlantaRepositorio( private val plantaDao: PlantaDao) {


    // retrofit Cliente

    private val networkService = RetrofitClient.retrofitInstance()

    // dao listado
    val plantasListLiveData = plantaDao.getAllPlantas()

    // un elemento

    val plantaDetailLiveData = MutableLiveData<PlantaDetaEntity>()


    suspend fun fetchPlantas() {
        val service = kotlin.runCatching { networkService.getAllPlantas() }
        service.onSuccess {
            when (it.code()) {
                in 200..299 -> it.body()?.let {
                    Log.d("Plantas", it.toString())
                    plantaDao.insertAll(fromInternetPlantasEntity(it))
                }
                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}")
            }

        }
    }

    suspend fun fetchPlantaDeta(id: Int): PlantaDetaEntity? {
        val service = kotlin.runCatching { networkService.getPlantaById(id) }
        return service.getOrNull()?.body()?.let { plantaDeta ->
            // guardp los datos que viene del mapper y luego se los paso a dao directo
            val plandaDetaEntity = fromInternetPlantasDetaEntity(plantaDeta)
            //inserto los detalles de los curso DEL REPOSITORIO
            plantaDao.insertPlantaDeta(plandaDetaEntity)
            plandaDetaEntity
        }
    }
}