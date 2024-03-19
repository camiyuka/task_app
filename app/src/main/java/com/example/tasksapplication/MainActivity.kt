package com.example.tasksapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.example.tasksapplication.databinding.ActivityMainBinding
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    // Vai inicializar depois - lateinit
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            val task = Task(title = "Intervalo", description = "Não tem")

            val popupTitulo = AlertDialog.Builder(this)
            popupTitulo.setTitle("To Do:")
            val textTitulo = EditText(this)
            popupTitulo.setView(textTitulo)

            val popupDescricao = AlertDialog.Builder(this)
            popupDescricao.setTitle("Descrição:")
            val textDescricao = EditText(this)
            popupDescricao.setView(textDescricao)


            popupTitulo.setPositiveButton("Ok") { dialog, _ ->
                val titulo = textTitulo.text.toString()
                task.title = titulo // Atualiza o título da tarefa com o texto digitado
                Snackbar.make(view, "Tarefa salva: $task", Snackbar.LENGTH_LONG).show()
            }
            popupTitulo.setNegativeButton("Cancelar"){ dialog, _ ->
                dialog.cancel()
            }

            popupTitulo.show()


        }
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
