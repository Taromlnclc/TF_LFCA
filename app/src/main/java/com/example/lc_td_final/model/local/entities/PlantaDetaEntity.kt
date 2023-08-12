package com.example.lc_td_final.model.local.entities


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="planta_deta_tabla")
data class PlantaDetaEntity (
    @PrimaryKey
    val id: Int,
    val nombre: String,
    val tipo: String,
    val imagen: String,
    val descripcion: String
)