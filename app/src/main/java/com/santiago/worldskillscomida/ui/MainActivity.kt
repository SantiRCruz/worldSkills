package com.santiago.worldskillscomida.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.santiago.worldskillscomida.ui.pedidos.PedidosActivity
import com.santiago.worldskillscomida.R
import com.santiago.worldskillscomida.databinding.ActivityMainBinding
import com.santiago.worldskillscomida.ui.iniciosesion.IniciarSesionActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navView: BottomNavigationView = binding.navView

         navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_desayuno,
                R.id.navigation_almuerzo,
                R.id.navigation_cena,
                R.id.navigation_bebida,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.popBackStack()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.cerrar_sesion ->{
                val intent = Intent(applicationContext,IniciarSesionActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.pedidos ->{
                val intent = Intent(applicationContext, PedidosActivity::class.java)
                startActivity(intent)
            }
            
        }
        return super.onOptionsItemSelected(item)
    }
}