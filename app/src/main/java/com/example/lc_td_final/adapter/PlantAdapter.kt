package com.example.lc_td_final.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lc_td_final.model.local.entities.PlantasEntity
import com.example.lc_td_final.databinding.ItemsPlantaBinding

class PlantAdapter : RecyclerView.Adapter<PlantAdapter.PlantaVH>() {

    // referencia para el adapter
    private var listPlantas= listOf<PlantasEntity>()
    private  val selectPlanta = MutableLiveData<PlantasEntity>()

    fun update (list:List<PlantasEntity>){
        listPlantas= list
        notifyDataSetChanged()
    }

    //seleccionar 1na Planta
    fun selectPlanta(): LiveData<PlantasEntity> = selectPlanta

    inner class PlantaVH(private  val binding : ItemsPlantaBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(planta: PlantasEntity) {

            Glide.with(binding.pimagen).load(planta.imagen).centerCrop().into(binding.pimagen)
            binding.pnombre.text = planta.nombre
            binding.pdescripcion.text = planta.descripcion
            binding.ptipo.text = planta.tipo
            itemView.setOnClickListener(this)

        }
        override fun onClick(v:View) {
            // referencia a la selección
            selectPlanta.value = listPlantas[adapterPosition]
            Log.d("ONCLICK",adapterPosition.toString())
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantaVH {
        return PlantaVH(ItemsPlantaBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listPlantas.size

    override fun onBindViewHolder(holder: PlantaVH, position: Int) {
        val planta = listPlantas[position]
        holder.bind(planta)
    }

}