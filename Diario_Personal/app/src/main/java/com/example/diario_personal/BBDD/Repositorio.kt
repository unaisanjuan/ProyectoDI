package com.example.diario_personal.BBDD

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.diario_personal.Modelo.Nota
import com.example.diario_personal.Modelo.Usuario

class Repositorio(private val miBBDDPARSE: BBDDParse) {


    fun mostrarNotas(): MutableLiveData<List<Nota>> {
        return miBBDDPARSE.mostrarNotas()
    }

    fun borrarNota(miNota: Nota) {
        miBBDDPARSE.eliminarNota(miNota)
    }

    fun modificarNota(miNota: Nota) {
        miBBDDPARSE.modificarNota(miNota)
    }

    fun insertarNota(miNota: Nota) {
        miBBDDPARSE.insetarNota(miNota)
    }

    fun buscarNotaPorIdMax(): MutableLiveData<Int> {
        return miBBDDPARSE.buscarNotaPorIdMax()
    }

    fun buscarPorId(id: Int) {
        miBBDDPARSE.buscarNotaPorId(id)
    }

    fun insertarUsuario(miUsuario: Usuario) {
        miBBDDPARSE.insertarUsuario(miUsuario)
    }

    fun mostrarUsuario(): MutableLiveData<List<Usuario>> {
        return miBBDDPARSE.mostrarUsuarios()
    }

    fun buscarUsuarioPorId(id: Int) {
        miBBDDPARSE.buscarUsuarioPorId(id)
    }

    fun modificarUsuario(miUsuario: Usuario) {
        miBBDDPARSE.modificarUsuario(miUsuario)
    }

    fun eliminarUsuario(miUsuario: Usuario) {
        miBBDDPARSE.borrarUsuario(miUsuario)
    }

    fun comprobarUsuario(username: String, password: String): LiveData<Boolean> {
        return miBBDDPARSE.comprobarUsuario(username, password)
    }


}