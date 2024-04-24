package com.example.diario_personal.Modelo

class Usuario {
    var usuario: String = ""
    var email: String = ""
    var password: String = ""
    var id: Int = 0

    constructor(usuario: String, email: String, password: String, id: Int) {
        this.usuario = usuario
        this.email = email
        this.password = password
        this.id = id
    }

    constructor()

    constructor(usuario: String, email: String, password: String) {
        this.usuario = usuario
        this.email = email
        this.password = password
    }


    override fun toString(): String {
        return "Usuario(usuario='$usuario', email='$email', password='$password', id=$id)"
    }
}