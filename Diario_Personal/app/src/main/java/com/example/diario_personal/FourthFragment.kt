package com.example.diario_personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.diario_personal.databinding.FragmentFourthBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class FourthFragment : Fragment() {

    //binding para el fragmento
    private var _binding: FragmentFourthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val notaViewModel = (activity as MainActivity).notaVM

        // Observar las notas en el ViewModel
        notaViewModel.mostrarNotas().observe(viewLifecycleOwner) { notas ->
            // Calcular la longitud de cada nota
            val entries = mutableListOf<BarEntry>()
            notas.forEachIndexed { index, nota ->
                val longitud = nota.contenido.length.toFloat()
                entries.add(BarEntry(index.toFloat(), longitud))
            }

            // Crear el conjunto de datos y establecer el color
            val dataSet = BarDataSet(entries, "Longitud de las notas")
            dataSet.colors = ColorTemplate.MATERIAL_COLORS.asList()

            // Crear el objeto BarData con el conjunto de datos y configurarlo en el gráfico
            val data = BarData(dataSet)

            val barChart = view.findViewById<BarChart>(R.id.barChart)
            barChart.data = data

            // Configuración adicional del gráfico
            barChart.setFitBars(true)
            barChart.description.isEnabled = false
            barChart.xAxis.labelRotationAngle = 45f
            barChart.xAxis.isGranularityEnabled = true
            barChart.invalidate()
        }

        binding.btnVolver.setOnClickListener() {
            findNavController().navigate(R.id.action_fourthFragment_to_thirdFragment)
        }

        binding.btnCerrarSesion.setOnClickListener(){
            findNavController().navigate(R.id.action_fourthFragment_to_FirstFragment)
        }
    }
}

