package com.example.diario_personal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diario_personal.databinding.FragmentThirdBinding
import com.example.diario_personal.Modelo.Nota

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private lateinit var notaAdapter: Adaptador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val notaViewModel = (activity as MainActivity).notaVM

        notaAdapter = Adaptador(mutableListOf(), notaViewModel)
        binding.recyclerViewNotas.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = notaAdapter
        }

        // Observa las notas en el ViewModel y actualiza el adaptador cuando cambien
        notaViewModel.mostrarNotas().observe(viewLifecycleOwner, Observer { notas ->
            notas?.let {
                notaAdapter.setNotas(it.toMutableList())
            }
        })

        binding.btnAgregarNota.setOnClickListener {
            notaAdapter.agregarNota()
        }

        binding.btnLogOut.setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToSecondFragment() {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
}
