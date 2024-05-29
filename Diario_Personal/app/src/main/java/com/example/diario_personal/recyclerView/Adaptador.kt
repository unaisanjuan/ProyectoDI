package com.example.diario_personal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.diario_personal.Modelo.Nota
import com.example.diario_personal.Modelo.NotaVM

class Adaptador(private val notas: MutableList<Nota>, private val notaViewModel: NotaVM) : RecyclerView.Adapter<Adaptador.NotaViewHolder>() {

    private var maxId = if (notas.isNotEmpty()) notas.maxOf { it.id } else 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        holder.bind(nota)
    }

    override fun getItemCount() = notas.size

    fun agregarNota() {

        val nuevaid = ++maxId
        val nuevaNota = Nota("", "",nuevaid)
        notas.add(nuevaNota)
        notifyItemInserted(notas.size - 1)
        notaViewModel.insertarNota(nuevaNota)  // Insertar la nueva nota en la base de datos

    }

    fun setNotas(nuevasNotas: MutableList<Nota>) {
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

            // Estado inicial de la edición
            var editando = false

            // Mostrar el botón "Guardar" al inicio
            btnEditar.text = "Guardar"

            btnEditar.setOnClickListener {
                // Cambiar el estado de edición
                editando = !editando

                if (!editando) {
                    // Guardar los cambios en la base de datos cuando se desactiva la edición
                    nota.titulo = etTitulo.text.toString()
                    nota.contenido = etContenido.text.toString()
                    notaViewModel.modificarNota(nota)
                }

                // Habilitar o deshabilitar la edición de los campos según el estado
                etTitulo.isEnabled = editando
                etContenido.isEnabled = editando

                // Cambiar el texto del botón de editar según el estado
                btnEditar.text = if (editando) "Guardar" else "Editar"
            }

            btnEliminar.setOnClickListener {
                // Eliminar la nota de la lista y notificar el cambio
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
