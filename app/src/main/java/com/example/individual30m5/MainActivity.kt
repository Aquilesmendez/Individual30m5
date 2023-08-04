package com.example.individual30m5

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.example.individual30m5.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    //Creamos el objeto de la clase viewBindign.
    private lateinit var binding: ActivityMainBinding

    //Creamos los objetos de los widgets de nuestro diseño.
    private lateinit var etNumeroEntero: EditText
    private lateinit var etTexto: EditText
    private lateinit var etNumeroDecimal: EditText
    private lateinit var textViewResultado: TextView
    private lateinit var radioButton: RadioButton
    private lateinit var btonBorrar: MaterialButton
    private lateinit var btonGuardar: MaterialButton
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Configuracion  del objeto viewBinding y enlazamos el diseño al codigo.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Iniciamos las variables que vamos a utilizar.
        etNumeroEntero = binding.etNumeroEntero
        etTexto = binding.etTexto
        etNumeroDecimal = binding.etNumeroDecimal
        textViewResultado = binding.tvPreferences
        radioButton = binding.radiobutton
        btonBorrar = binding.btonBorrar
        btonGuardar = binding.btonGuardar
        sharedPreferences = SharedPreferences(this)

        //Creamos el evento escuchador para guardar los datos ingresados del usuario mostrar las sharepreferences
        //y limpiar los campos.
        btonGuardar.setOnClickListener {

            guardarDatos()
            limpiarCampos()
        }

        //Creamos el evento escuchador para borrar las sharepreferences guardada y limpiar los campos.
        btonBorrar.setOnClickListener {
            sharedPreferences.borrarTodasLasPreferencias()
            limpiarCampos()
            textViewResultado.setText("")
        }
    }

    //Funcion para verificar los datos ingresados por el usuario de fallar mostrar un mensaje de error si no guardarlos.
    private fun guardarDatos() {
        //Convertimos los valores ingresados por el usuario a string.
        val enteroStr = etNumeroEntero.text.toString()
        val texto = etTexto.text.toString()
        val booleano = radioButton.isChecked
        val decimalStr = etNumeroDecimal.text.toString()

        //Verificamos primero que los campos no esten vacios
        if (enteroStr.isNotEmpty() && decimalStr.isNotEmpty() && texto.isNotEmpty()) {
            //Convertimos los valores ingresados por el usuario a los datos requeridos.
            val entero = enteroStr.toInt()
            val decimal: Float

            //Hacemos un verificador en caso que no se pueda convertir a float muestre un error y no se caiga la aplicacion.
            try {
                decimal = decimalStr.toFloat()
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    this,
                    "El campo de número decimal debe ser un valor válido.",
                    Toast.LENGTH_LONG
                ).show()
                return
            }

            //Guardamos los datos ingresados por el usuario.
            sharedPreferences.guardarNumeroEntero(entero)
            sharedPreferences.guardarTexto(texto)
            sharedPreferences.guardarBooleano(booleano)
            sharedPreferences.guardarNumeroDecimal(decimal)

            mostrarDatosGuardados()
        } else {
            Toast.makeText(
                this,
                "Todos los campos deben estar correctamente ingresados",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    //Funcion para recuperar los datos de las sharePreferences.
    private fun mostrarDatosGuardados() {

        //Recuperamos los datos guardados por el usuario de y los guardamos en variables.
        val entero = sharedPreferences.obtenerNumeroEntero()
        val texto = sharedPreferences.obtenerTexto()
        val booleano = sharedPreferences.obtenerBooleano()
        val decimal = sharedPreferences.obtenerNumeroDecimal()

        //Le damos formato para imprimir los valores.
        val resultado = "Numero Guardado: $entero\n" +
                "Texto Guardado: $texto\n" +
                "Booleano: $booleano\n" +
                "Numero Decimal: $decimal"

        //Seteamos el textview para mostrar los datos.
        textViewResultado.text = resultado
    }

    //Funcion para setear los campos.
    private fun limpiarCampos(){
        etTexto.setText("")
        etNumeroEntero.setText("")
        etNumeroDecimal.setText("")
        radioButton.isChecked = false
    }
}
