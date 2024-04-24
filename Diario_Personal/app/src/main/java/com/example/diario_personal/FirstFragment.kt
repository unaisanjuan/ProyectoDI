package com.example.diario_personal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.diario_personal.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navegar al segundo fragmento clicando en la textview registro
        binding.textViewRegister.setOnClickListener {
            navigateToSecondFragment()
        }

        // Al hacer clic en el botón de inicio de sesión se navega al tercer fragmento y comprueba si el usuario y la contraseña son correctos en la base de datos
        binding.buttonLogin.setOnClickListener {
            val user = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            // Validar la longitud de la contraseña
            if (password.length < 8) {
                Toast.makeText(requireContext(), "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Llamar al método en el UsuarioViewModel para comprobar si el usuario y la contraseña son correctos
            (activity as MainActivity).usuarioVM.comprobarUsuario(user,password)
            (activity as MainActivity).usuarioVM.comprobarUsuario.observe(activity as MainActivity) { isValid ->
                if (isValid) {
                    // Si el usuario es válido, ir al tercer fragmento y mostrar un mensaje de éxito
                    findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment)
                    Toast.makeText(requireContext(), "Te has logueado correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    // Si el usuario no es válido, mostrar un mensaje de error
                    Toast.makeText(requireContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
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