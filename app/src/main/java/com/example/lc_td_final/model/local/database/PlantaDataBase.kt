package com.example.lc_td_final.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lc_td_final.model.local.PlantaDao
import com.example.lc_td_final.model.local.entities.PlantaDetaEntity
import com.example.lc_td_final.model.local.entities.PlantasEntity


@Database(entities = [PlantasEntity:: class,PlantaDetaEntity::class], version = 1, exportSchema = false)
abstract class PlantaDataBase: RoomDatabase() {

    //referenciar al dao
    abstract fun getPlantaDao(): PlantaDao

    companion object{

        @Volatile
        private var
                INSTANCE : PlantaDataBase? = null
        fun getDataBase(context: Context) : PlantaDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlantaDataBase::class.java, "PlantAPP")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}