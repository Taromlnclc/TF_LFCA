package com.example.lc_td_final.model

import com.example.lc_td_final.model.local.entities.PlantaDetaEntity
import com.example.lc_td_final.model.local.entities.PlantasEntity
import com.example.lc_td_final.model.remoto.net.PlantaDeta
import com.example.lc_td_final.model.remoto.net.Plantas

fun fromInternetPlantasEntity( plantasList: List<PlantasEntity>) :List<Plantas> {
    return plantasList.map {
        Plantas(
            id = it.id,
            nombre = it.nombre,
            tipo = it.tipo,
            imagen = it.imagen,
            descripcion = it.descripcion
        )
    }
}

fun fromInternetPlantasDetaEntity( planta: PlantaDeta) :PlantaDetaEntity {
    return PlantaDetaEntity(
            id = planta.id,
            nombre = planta.nombre,
            tipo = planta.tipo,
            imagen = planta.imagen,
            descripcion = planta.descripcion
        )
}
