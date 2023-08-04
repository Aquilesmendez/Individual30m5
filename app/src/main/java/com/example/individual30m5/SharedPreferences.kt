package com.example.individual30m5

import android.content.Context
import android.content.SharedPreferences

//Creamos la clase sharePreference.
class SharedPreferences(context: Context) {

    //Variables constantes para las llaves.
    private val PREFERENCES_FILE_NAME = "MyPreferences"
    private val PREFERENCES_ENTERO = "entero"
    private val PREFERENCES_TEXTO = "texto"
    private val PREFERENCES_BOOLEANO = "booleano"
    private val PREFERENCES_DECIMAL = "decimal"

    //Iniciamos el objeto de la clase sharePreferences.
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)

    //Funcion para guardar el numero entero
    fun guardarNumeroEntero(valor: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(PREFERENCES_ENTERO, valor)
        editor.apply()
    }

    //Funcion para obtener el numero entero
    fun obtenerNumeroEntero(): Int {
        return sharedPreferences.getInt(PREFERENCES_ENTERO, 0)
    }

    //Funcion para guardar el texto.
    fun guardarTexto(valor: String) {
        val editor = sharedPreferences.edit()
        editor.putString(PREFERENCES_TEXTO, valor)
        editor.apply()
    }

    //Funcion para obtener el texto.
    fun obtenerTexto(): String {
        return sharedPreferences.getString(PREFERENCES_TEXTO, "") ?: ""
    }

    //Funcion para guardat el dato booleano.
    fun guardarBooleano(valor: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(PREFERENCES_BOOLEANO, valor)
        editor.apply()
    }

    //Funcion para obtener el dato booleano.
    fun obtenerBooleano(): Boolean {
        return sharedPreferences.getBoolean(PREFERENCES_BOOLEANO, false)
    }

    //Funcion para guardar el numero decimal.
    fun guardarNumeroDecimal(valor: Float) {
        val editor = sharedPreferences.edit()
        editor.putFloat(PREFERENCES_DECIMAL, valor)
        editor.apply()
    }

    //Funcion para obtener el numero decimal.
    fun obtenerNumeroDecimal(): Float {
        return sharedPreferences.getFloat(PREFERENCES_DECIMAL, 0.0f)
    }

    //Funcion para borrar todos los datos de las sharepreferences.
    fun borrarTodasLasPreferencias() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

}
