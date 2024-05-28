package com.example.diario_personal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.diario_personal.Modelo.Nota

class Adaptador(private val notas: MutableList<Nota>) : RecyclerView.Adapter<Adaptador.NotaViewHolder>() {

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
        notas.add(Nota("", ""))
        notifyItemInserted(notas.size - 1)
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
                    notas.removeAt(posicion)
                    notifyItemRemoved(posicion)
                }
            }
        }
    }


}