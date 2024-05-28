package com.example.diario_personal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diario_personal.databinding.FragmentThirdBinding


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
        notaAdapter = Adaptador(mutableListOf())
        binding.recyclerViewNotas.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = notaAdapter
        }

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
