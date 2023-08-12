package com.example.lc_td_final.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lc_td_final.model.PlantaRepositorio
import com.example.lc_td_final.model.local.database.PlantaDataBase
import com.example.lc_td_final.model.local.entities.PlantaDetaEntity
import com.example.lc_td_final.model.local.entities.PlantasEntity
import kotlinx.coroutines.launch

class PlantasViewModel(application: Application) : AndroidViewModel(application) {

    // conexión repository
    private val repository : PlantaRepositorio

    // entidades
    private val plantaDetailLiveData = MutableLiveData<PlantaDetaEntity>()

    // para seleccionar
    private var idSelected : String="-1"

    init{
        // tiene la instancia de la bd el dao y lo entregamos el repository
        val bd= PlantaDataBase.getDataBase(application)
        val plantaDao= bd.getPlantaDao()

        repository = PlantaRepositorio(plantaDao)

        // llamo al método del repository

        viewModelScope.launch {
            repository.fetchPlantas()
        }
    }


   // Lista plantas
    fun getPlantasList(): LiveData<List<PlantasEntity>> = repository.plantasListLiveData

    // Planta por id desde lo que se selecciono
    fun getPlantaDeta(): LiveData<PlantaDetaEntity> = plantaDetailLiveData

    // desde el segundo fragmento le paso la seleccion
    fun getPlantaDetaByIdFromInternet(id: Int)= viewModelScope.launch {
        val plantaDeta = repository.fetchPlantaDeta(id)
        plantaDeta?.let {
            plantaDetailLiveData.postValue(it)
        }
    }
}