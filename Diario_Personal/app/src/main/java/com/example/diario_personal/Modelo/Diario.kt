package com.example.diario_personal.Modelo

class Diario {

    var id: Int = 0
    var fecha: String = ""
    var contenido: String = ""
    var titulo: String = ""

    constructor(id: Int, fecha: String, contenido: String, titulo: String) {
        this.id = id
        this.fecha = fecha
        this.contenido = contenido
        this.titulo = titulo
    }

    constructor()

    override fun toString(): String {
        return "Diario(id=$id, fecha='$fecha', contenido='$contenido', titulo='$titulo')"
    }

}