package com.example.lc_td_final.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.lc_td_final.R
import com.example.lc_td_final.databinding.FragmentDosFragmentoBinding
import com.example.lc_td_final.viewmodel.PlantasViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DosFragmento.newInstance] factory method to
 * create an instance of this fragment.
 */
class DosFragmento : Fragment() {

    private lateinit var mBinding: FragmentDosFragmentoBinding
    private val mViewModel: PlantasViewModel by activityViewModels()
    private var plantaId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDosFragmentoBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            plantaId = bundle.getInt("Id")
            Log.d("seleccion", plantaId.toString())
        }

        plantaId?.let { id ->
            mViewModel.getPlantaDetaByIdFromInternet(id)

        }

        mViewModel.getPlantaDeta().observe(viewLifecycleOwner, Observer {
            Log.d("seleccion3", plantaId.toString())

            var id = it.id
            var name = it.nombre

// cargamos los datos desde la seleccion
            Glide.with(mBinding.pimagen).load(it.imagen).into(mBinding.pimagen)
            mBinding.pnombre.text = it.nombre
            mBinding.pdescripcion.text = it.descripcion
            mBinding.ptipo.text = "TIPO: ${it.tipo}"



            // ACCION DE ENVIAR CORREO Eléctronico


            mBinding.btMail.setOnClickListener {
                Log.d("Button", "Click")

                val mintent = Intent(Intent.ACTION_SEND)
                mintent.data = Uri.parse("mailto")
                mintent.type = "text/plain"

                mintent.putExtra(Intent.EXTRA_EMAIL, arrayOf("admisión@centrofuturo.cl"))
                mintent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Solicito información sobre este producto" + id
                )


                mintent.putExtra(

                    Intent.EXTRA_TEXT, "Hola\n" +

                            "Quisiera pedir información sobre este planta " + name + " ,\n" +
                            "me gustaría que me contactaran a este correo o al siguiente número\n" +
                            " _________\n" +
                            "Quedo atento."


                )
                try {
                    startActivity(mintent)
                } catch (e: Exception) {

                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }
            }
        })


    }
    override fun onDestroyView() {
        super.onDestroyView()
    }
}