package com.example.diario_personal.Modelo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.diario_personal.BBDD.Repositorio
import kotlinx.coroutines.launch

class NotaVM(private val miRepositorio: Repositorio): ViewModel() {

    fun insertarNota(nota: Nota) = viewModelScope.launch {
        miRepositorio.insertarNota(nota)
    }

    fun mostrarNotas(): LiveData<List<Nota>> {
        return miRepositorio.mostrarNotas()
    }

    fun buscarNotaPorId(nota: Nota) = viewModelScope.launch {
        miRepositorio.borrarNota(nota)
    }

    fun modificarNota(nota: Nota) = viewModelScope.launch {
        miRepositorio.modificarNota(nota)
    }

    fun buscarNotaPorIdMax(): MutableLiveData<Int> {
        return miRepositorio.buscarNotaPorIdMax()
    }

    fun eliminarNota(nota: Nota) = viewModelScope.launch {
        miRepositorio.borrarNota(nota)
    }
}

class NotaViewModelFactory(private val miRepositorio: Repositorio): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotaVM::class.java)){
            @Suppress("UNCHECKED_CAST")
            return NotaVM(miRepositorio) as T
        }
        throw IllegalArgumentException("ViewModel class desconocida")
    }
}
