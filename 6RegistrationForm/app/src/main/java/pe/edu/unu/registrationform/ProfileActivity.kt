package pe.edu.unu.registrationform

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.unu.registrationform.model.ResultadoPerfil
import pe.edu.unu.registrationform.model.Usuario

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvDatos = findViewById<TextView>(R.id.tvDatosRecibidos)
        val btnVerificar = findViewById<Button>(R.id.btnVerificar)

        // 1. Obtener el objeto Parcelable enviado
        val usuario = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("EXTRA_USUARIO", Usuario::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("EXTRA_USUARIO")
        }

        // 2. Mostrar los datos del objeto
        usuario?.let {
            tvDatos.text = "Nombre: ${it.nombre}\nEdad: ${it.edad}\nCorreo: ${it.correo}"
        }

        // 3. Devolver un objeto Parcelable como respuesta
        btnVerificar.setOnClickListener {
            val resultado = ResultadoPerfil(
                verificado = true,
                mensaje = "Perfil confirmado con éxito"
            )

            val intentResultado = Intent().apply {
                putExtra("EXTRA_RESULTADO", resultado)
            }

            setResult(RESULT_OK, intentResultado)
            finish() // Cierra la actividad actual
        }

    }
}