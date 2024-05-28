package com.example.diario_personal.BBDD

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.diario_personal.Modelo.Nota
import com.example.diario_personal.Modelo.Usuario
import com.parse.ParseObject
import com.parse.ParseQuery

class BBDDParse {

    fun insetarNota(miNota: Nota) {
        val registroNota = ParseObject("tabla_notas")
        registroNota.put("titulo", miNota.titulo)
        registroNota.put("contenido", miNota.contenido)
        registroNota.put("fecha", miNota.fecha)
        registroNota.put("idNota", miNota.id)
        registroNota.saveInBackground {
            if (it != null) {
                it.localizedMessage?.let { message ->
                    throw Exception(message)
                }
            }
        }
    }

    fun eliminarNota(miNota: Nota) {
        val query =
            ParseQuery.getQuery<ParseObject>("tabla_notas")
        query.whereEqualTo("idNota", miNota.id)
        query.getFirstInBackground { parseObject, parseException ->
            if (parseException == null) {
                parseObject.deleteInBackground {
                    if (it != null) {
                        throw Exception(it.localizedMessage)
                    }
                }
            } else {
                throw Exception(parseException.localizedMessage)
            }
        }
    }

    fun modificarNota(miNota: Nota) {
        val query =
            ParseQuery.getQuery<ParseObject>("tabla_notas")
        query.whereEqualTo("idNota", miNota.id)
        query.getFirstInBackground { parseObject, parseException ->
            if (parseException == null) {
                parseObject.put("titulo", miNota.titulo)
                parseObject.put("contenido", miNota.contenido)
                parseObject.put("fecha", miNota.fecha)
                parseObject.saveInBackground {
                    if (it != null) {
                        throw Exception(it.localizedMessage)
                    }
                }
            } else {
                throw Exception(parseException.localizedMessage)
            }
        }
    }

    fun mostrarNotas(): MutableLiveData<List<Nota>> {
        val misNotas: MutableLiveData<List<Nota>> = MutableLiveData()
        val query = ParseQuery.getQuery<ParseObject>("tabla_notas")
        query.orderByAscending("idNota")
        query.findInBackground { objects, e ->
            if (e == null) {
                val notas = objects.map { i ->
                    Nota(
                        i.getString("titulo") ?: "",
                        i.getString("contenido") ?: "",
                        i.getString("fecha") ?: "",
                        i.getInt("idNota")
                    )
                }
                misNotas.postValue(notas)
            }
        }
        return misNotas
    }

    fun buscarNotaPorId(id: Int) {
        val query = ParseQuery.getQuery<ParseObject>("tabla_notas")
        query.whereEqualTo("idNota", id)
        query.getFirstInBackground { parseObject, parseException ->
            if (parseException == null) {
                val nota = Nota(
                    parseObject.getString("titulo") ?: "",
                    parseObject.getString("contenido") ?: "",
                    parseObject.getString("fecha") ?: "",
                    parseObject.getInt("idNota")
                )
            } else {
                throw Exception(parseException.localizedMessage)
            }
        }
    }

    fun insertarUsuario(miUsuario: Usuario) {
        val registroUsuario = ParseObject("tabla_usuarios")
        registroUsuario.put("nombre", miUsuario.usuario)
        registroUsuario.put("email", miUsuario.email)
        registroUsuario.put("password", miUsuario.password)
        registroUsuario.put("id", miUsuario.id)
        registroUsuario.saveInBackground {
            if (it != null) {
                it.localizedMessage?.let { message ->
                    throw Exception(message)
                }
            }
        }
    }


    fun borrarUsuario(miUsuario: Usuario) {
        val query =
            ParseQuery.getQuery<ParseObject>("tabla_usuarios")
        query.whereEqualTo("id", miUsuario.id)
        query.getFirstInBackground { parseObject, parseException ->
            if (parseException == null) {
                parseObject.deleteInBackground {
                    if (it != null) {
                        throw Exception(it.localizedMessage)
                    }
                }
            } else {
                throw Exception(parseException.localizedMessage)
            }
        }
    }

    fun modificarUsuario(miUsuario: Usuario) {
        val query =
            ParseQuery.getQuery<ParseObject>("tabla_usuarios")
        query.whereEqualTo("id", miUsuario.id)
        query.getFirstInBackground { parseObject, parseException ->
            if (parseException == null) {
                parseObject.put("nombre", miUsuario.usuario)
                parseObject.put("email", miUsuario.email)
                parseObject.put("password", miUsuario.password)
                parseObject.saveInBackground {
                    if (it != null) {
                        throw Exception(it.localizedMessage)
                    }
                }
            } else {
                throw Exception(parseException.localizedMessage)
            }
        }
    }

    fun mostrarUsuarios(): MutableLiveData<List<Usuario>> {
        val misUsuarios: MutableLiveData<List<Usuario>> = MutableLiveData()
        val query = ParseQuery.getQuery<ParseObject>("tabla_usuarios")
        query.orderByAscending("idUsuario")
        query.findInBackground { objects, e ->
            if (e == null) {
                val usuarios = objects.map { i ->
                    Usuario(
                        i.getString("nombre") ?: "",
                        i.getString("email") ?: "",
                        i.getString("password") ?: "",
                        i.getInt("id")
                    )
                }
                misUsuarios.postValue(usuarios)
            } else {
                // Manejar el error
                Log.e("mostrarUsuarios", "Error al obtener usuarios", e)
            }
        }
        return misUsuarios
    }


    fun buscarUsuarioPorId(id: Int): MutableLiveData<Usuario> {
        val usuarioEncontrado: MutableLiveData<Usuario> = MutableLiveData()
        val query = ParseQuery.getQuery<ParseObject>("tabla_usuarios")
        query.whereEqualTo("id", id)
        query.getFirstInBackground { parseObject, parseException ->
            if (parseException == null) {
                val usuario = Usuario(
                    parseObject.getString("nombre") ?: "",
                    parseObject.getString("email") ?: "",
                    parseObject.getString("password") ?: "",
                    parseObject.getInt("id")
                )
                usuarioEncontrado.postValue(usuario)
            } else {
                // Manejar el error
                Log.e("buscarUsuarioPorId", "Error al buscar usuario por ID", parseException)
                usuarioEncontrado.postValue(null) // Opcional: también puedes manejar el error devolviendo un usuario nulo
            }
        }
        return usuarioEncontrado
    }

    fun comprobarUsuario(username: String, password: String): LiveData<Boolean> {
        val isValidLiveData = MutableLiveData<Boolean>()
        val query = ParseQuery.getQuery<ParseObject>("tabla_usuarios")
        query.whereEqualTo("nombre", username)
        query.whereEqualTo("password", password)
        query.getFirstInBackground { parseObject, parseException ->
            if (parseException == null) {
                val isValid = parseObject != null
                isValidLiveData.postValue(isValid)
            } else {
                // Manejar el error
                Log.e("comprobarUsuario", "Error al comprobar el usuario", parseException)
                isValidLiveData.postValue(false)
            }
        }
        return isValidLiveData
    }

}