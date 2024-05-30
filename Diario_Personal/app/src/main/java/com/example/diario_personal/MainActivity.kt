package com.example.diario_personal

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.diario_personal.BBDD.BBDDParse
import com.example.diario_personal.BBDD.Repositorio
import com.example.diario_personal.Modelo.NotaVM
import com.example.diario_personal.Modelo.NotaViewModelFactory
import com.example.diario_personal.Modelo.UsuarioVM
import com.example.diario_personal.Modelo.UsuarioViewModelFactory
import com.example.diario_personal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var tiempoInicio: Long = 0
    //definir viewmodel usuario
    private val miRepositorio by lazy { Repositorio(BBDDParse()) }
    val usuarioVM: UsuarioVM by viewModels {
        UsuarioViewModelFactory(miRepositorio)
    }
    val notaVM: NotaVM by viewModels {
        NotaViewModelFactory(miRepositorio)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        supportActionBar?.hide()
        tiempoInicio = System.currentTimeMillis()
    }

    override fun onResume() {
        super.onResume()
        tiempoInicio = System.currentTimeMillis()
    }

    override fun onPause() {
        super.onPause()
        val tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio
        // Guardar tiempoTranscurrido en SharedPreferences u otra forma de almacenamiento
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}