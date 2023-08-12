package com.example.lc_td_final.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lc_td_final.model.local.entities.PlantaDetaEntity
import com.example.lc_td_final.model.local.entities.PlantasEntity
import com.example.lc_td_final.model.remoto.net.Plantas

@Dao
interface PlantaDao {

    //Inserta Plantas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plantas:List<Plantas>)

    //Inserta 1 planta
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlantaDeta(planta: PlantaDetaEntity)

    //Selecciona lista de plantas
    @Query("SELECT * FROM planta_tabla ORDER BY nombre DESC")
    fun getAllPlantas(): LiveData<List<PlantasEntity>>

    //Selecciona por Id
    @Query("SELECT * FROM planta_deta_tabla WHERE id=:id")
    suspend fun getPlantaById(id: Int): LiveData<PlantaDetaEntity>


    //Elimina todos los valores
    @Query("DELETE FROM planta_tabla")
    suspend fun deleteAll()
}