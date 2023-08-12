package com.example.lc_td_final.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lc_td_final.R
import com.example.lc_td_final.adapter.PlantAdapter
import com.example.lc_td_final.databinding.FragmentUnoFragmentoBinding
import com.example.lc_td_final.viewmodel.PlantasViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UnoFragmento.newInstance] factory method to
 * create an instance of this fragment.
 */
class UnoFragmento : Fragment() {

    private lateinit var  mBinding : FragmentUnoFragmentoBinding
    private val mViewModel : PlantasViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentUnoFragmentoBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // DEBEMOS INTANCIAR ADAPTER

        val adapter = PlantAdapter()
        mBinding.recyclerView.adapter = adapter
        mBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        mViewModel.getPlantasList().observe(viewLifecycleOwner, Observer {

            it?.let {
                Log.d("Listado", it.toString())
                adapter.update(it)
            }

        })

        // MÉTODO PARA SELECCIONAR

        adapter.selectPlanta().observe(viewLifecycleOwner, Observer {
            it?.let {
                // válidar si capta la seleccion
                Log.d("Seleccion", it.id.toString())

            }
            val bundle = Bundle().apply {
                putString("courseId", it.id.toString())
            }
            findNavController().navigate(R.id.action_1Fragment_to_2Fragment, bundle)

        })
    }
}