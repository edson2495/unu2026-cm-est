package pe.edu.unu.petclinicapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import pe.edu.unu.petclinicapp.databinding.ActivityLoginBinding
import android.view.View

class LoginActivity : AppCompatActivity() {

    private val DEMO_USERNAME = "admin"
    private val DEMO_PASSWORD = "123456"

    private lateinit var binding: ActivityLoginBinding

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUser.text.toString()
            val password = binding.etPassword.text.toString()

            if(username == DEMO_USERNAME && password == DEMO_PASSWORD){//consumir el api
                sessionManager.createSession(username)
                navigateToMain()
            }else{
                showErrorMsg(binding.root)
            }

        }

    }

    private fun navigateToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showErrorMsg(view: View){
        Snackbar.make(
            view,
            "Usuario incorrecto.",
            Snackbar.LENGTH_LONG
        ).show()
    }

}