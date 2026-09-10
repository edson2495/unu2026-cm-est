package pe.edu.unu.petclinicapp

import android.content.Context
import android.content.SharedPreferences

class SessionManager (context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

    fun createSession(username: String){
        val editor = prefs.edit()
        editor.putBoolean("IS_LOGGED_IN",true)
        editor.putString("username", username)
        editor.apply()
    }

    fun isLogged(): Boolean{
        return prefs.getBoolean("IS_LOGGED_IN", false)
    }

    fun closeSession(){
        prefs.edit().clear().apply()
    }

}