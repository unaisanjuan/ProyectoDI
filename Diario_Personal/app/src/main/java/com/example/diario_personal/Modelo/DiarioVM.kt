package com.example.diario_personal.Modelo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.diario_personal.BBDD.Repositorio
import kotlinx.coroutines.launch

class DiarioVM(private val miRepositorio: Repositorio ): ViewModel(){

    lateinit var notas: MutableLiveData<Diario>
    lateinit var listaNotas: MutableLiveData<List<Diario>>


    fun mostratNotas()= viewModelScope.launch {
        miRepositorio.mostrarNotas()
    }

    fun borrarNota(miNota: Diario)= viewModelScope.launch {
        miRepositorio.borrarNota(miNota)
    }

    fun modificarNota(miNota: Diario)= viewModelScope.launch {
        miRepositorio.modificarNota(miNota)
    }

    fun insertarNota(miNota: Diario)= viewModelScope.launch {
        miRepositorio.insertarNota(miNota)
    }

    fun buscarPorId(id: Int)= viewModelScope.launch {
        miRepositorio.buscarPorId(id)
    }

}

class DiarioViewModelFactory(private val miRepositorio: Repositorio): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiarioVM::class.java)){
            @Suppress("UNCHECKED_CAST")
            return DiarioVM(miRepositorio) as T
        }
        throw IllegalArgumentException("ViewModel class desconocida")
    }
}