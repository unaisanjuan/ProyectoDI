package com.example.diario_personal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.diario_personal.Modelo.Usuario
import com.example.diario_personal.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("usuariovw","llega al segundo fragmento")

        binding.buttonRegister.setOnClickListener {
            val usuario = binding.editTextName.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            if (!email.matches(emailPattern.toRegex())) {
                Toast.makeText(requireContext(), "Formato de correo electrónico inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validar la longitud de la contraseña
            if (password.length < 8) {
                Toast.makeText(requireContext(), "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nuevoUsuario = Usuario(usuario, email, password)

            // Llamar al método en el UsuarioViewModel para registrar el usuario
            (activity as MainActivity).usuarioVM.insertarUsuario(nuevoUsuario)

            // ir a la pagina de login y que salte un mensaje de te has registrado correctamente
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            Toast.makeText(requireContext(), "Te has registrado correctamente", Toast.LENGTH_SHORT).show()
        }

        binding.textViewLoginLink.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}