package com.example.diario_personal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import com.example.diario_personal.Modelo.Nota
import com.example.diario_personal.Modelo.NotaVM
import java.util.Random

class Adaptador(private val notas: MutableList<Nota>, private val notaViewModel: NotaVM) : RecyclerView.Adapter<Adaptador.NotaViewHolder>() {

    private var maxId: Int = 0

    private val idSet = mutableSetOf<Int>()

    init {
        // Inicializar el conjunto con todos los IDs posibles
        idSet.addAll(1..1000000)
    }

    // Función para generar un ID único
    private fun generarIdUnico(): Int {
        // Si el conjunto de IDs está vacío, no se pueden generar más IDs únicos
        if (idSet.isEmpty()) {
            throw IllegalStateException("No hay más IDs únicos disponibles.")
        }

        // Seleccionar un ID aleatorio del conjunto y luego eliminarlo
        val randomIndex = Random().nextInt(idSet.size)
        val iterator = idSet.iterator()
        repeat(randomIndex) { iterator.next() }
        val id = iterator.next()
        iterator.remove()

        return id
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        holder.bind(nota)
    }

    override fun getItemCount() = notas.size

    // crea un randomizer que se encarga de asignar un id a cada nota


    fun agregarNota() {
        val nuevoId = generarIdUnico()
        val nuevaNota = Nota("", "", nuevoId)
        notas.add(nuevaNota)
        notifyItemInserted(notas.size - 1)
        notaViewModel.insertarNota(nuevaNota)  // Insertar la nueva nota en la base de datos
    }

    fun setNotas(nuevasNotas: MutableList<Nota>) {
        this.notas.clear() // Limpiar la lista antes de añadir nuevas notas
        this.notas.addAll(nuevasNotas)
        notifyDataSetChanged()
    }

    inner class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val etTitulo: EditText = itemView.findViewById(R.id.etTitulo)
        private val etContenido: EditText = itemView.findViewById(R.id.etContenido)
        private val btnEditar: Button = itemView.findViewById(R.id.btnEditar)
        private val btnEliminar: Button = itemView.findViewById(R.id.btnEliminar)

        fun bind(nota: Nota) {
            etTitulo.setText(nota.titulo)
            etContenido.setText(nota.contenido)

            var editando = false

            btnEditar.text = "Guardar"

            btnEditar.setOnClickListener {
                editando = !editando

                if (!editando) {
                    nota.titulo = etTitulo.text.toString()
                    nota.contenido = etContenido.text.toString()
                    notaViewModel.modificarNota(nota)
                }

                etTitulo.isEnabled = editando
                etContenido.isEnabled = editando

                btnEditar.text = if (editando) "Guardar" else "Editar"
            }

            btnEliminar.setOnClickListener {
                val posicion = adapterPosition
                if (posicion != RecyclerView.NO_POSITION) {
                    val notaAEliminar = notas[posicion]
                    notas.removeAt(posicion)
                    notifyItemRemoved(posicion)
                    notaViewModel.eliminarNota(notaAEliminar)
                }
            }
        }
    }
}
