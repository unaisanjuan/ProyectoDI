package com.example.diario_personal.BBDD

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.diario_personal.Modelo.Diario
import com.example.diario_personal.Modelo.Usuario

class Repositorio(private val miBBDDPARSE: BBDDParse) {


    fun mostrarNotas(): MutableLiveData<List<Diario>> {
        return miBBDDPARSE.mostrarNotas()
    }

    fun borrarNota(miNota: Diario) {
        miBBDDPARSE.eliminarNota(miNota)
    }

    fun modificarNota(miNota: Diario) {
        miBBDDPARSE.modificarNota(miNota)
    }

    fun insertarNota(miNota: Diario) {
        miBBDDPARSE.insetarNota(miNota)
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