package com.example.diario_personal.Modelo

class Nota {
    var titulo: String = ""
    var contenido: String = ""
    var fecha: String = ""
    var id: Int = 0

    constructor(titulo: String, contenido: String, fecha: String, id: Int) {
        this.titulo = titulo
        this.contenido = contenido
        this.fecha = fecha
        this.id = id
    }

    constructor()

    constructor(titulo: String, contenido: String, fecha: String) {
        this.titulo = titulo
        this.contenido = contenido
        this.fecha = fecha
    }

    constructor(titulo: String, contenido: String) {
        this.titulo = titulo
        this.contenido = contenido
    }

    constructor(titulo: String, contenido: String, id: Int) {
        this.titulo = titulo
        this.contenido = contenido
        this.id = id
    }


}


