package com.example.diario_personal.Modelo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.diario_personal.BBDD.Repositorio
import kotlinx.coroutines.launch


class UsuarioVM (private val miRepositorio: Repositorio): ViewModel() {

    lateinit var comprobarUsuario: LiveData<Boolean>

    fun insertarUsuario(miUsuario: Usuario)=viewModelScope.launch {
        miRepositorio.insertarUsuario(miUsuario)
    }

    fun mostrarUsuario()=viewModelScope.launch {
        miRepositorio.mostrarUsuario()
    }

    fun buscarUsuarioPorId(id: Int)=viewModelScope.launch {
        miRepositorio.buscarUsuarioPorId(id)
    }

    fun modificarUsuario(miUsuario: Usuario)=viewModelScope.launch {
        miRepositorio.modificarUsuario(miUsuario)
    }

    fun eliminarUsuario(miUsuario: Usuario)=viewModelScope.launch {
        miRepositorio.eliminarUsuario(miUsuario)
    }

    fun comprobarUsuario(username: String, password: String)=viewModelScope.launch {
        comprobarUsuario = miRepositorio.comprobarUsuario(username, password)
    }

}

class UsuarioViewModelFactory(private val miRepositorio: Repositorio): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsuarioVM::class.java)){
            @Suppress("UNCHECKED_CAST")
            return UsuarioVM(miRepositorio) as T
        }
        throw IllegalArgumentException("ViewModel class desconocida")
    }
}