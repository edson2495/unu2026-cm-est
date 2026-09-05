package pe.edu.unu.registrationform

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.result.contract.ActivityResultContracts
import pe.edu.unu.registrationform.model.ResultadoPerfil
import pe.edu.unu.registrationform.model.Usuario

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etEdad: EditText
    private lateinit var etCorreo: EditText
    private lateinit var tvEstado: TextView

    // Register Activity Result Launcher
    private val launcherPerfil = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // Extracción segura del objeto devuelto compatible con API 33+
            val resultado = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra("EXTRA_RESULTADO", ResultadoPerfil::class.java)
            } else {
                @Suppress("DEPRECATION")
                result.data?.getParcelableExtra("EXTRA_RESULTADO")
            }

            resultado?.let {
                tvEstado.text = "Estado: ${it.mensaje} (Verificado: ${it.verificado})"
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etNombre = findViewById(R.id.etNombre)
        etEdad = findViewById(R.id.etEdad)
        etCorreo = findViewById(R.id.etCorreo)
        tvEstado = findViewById(R.id.tvEstado)

        val btnEnviar = findViewById<Button>(R.id.btnEnviar)
        btnEnviar.setOnClickListener {
            // 1. Crear la instancia del objeto
            val nuevoUsuario = Usuario(
                nombre = etNombre.text.toString(),
                edad = etEdad.text.toString().toIntOrNull() ?: 0,
                correo = etCorreo.text.toString()
            )

            // 2. Colocar el objeto completo en el Intent
            val intent = Intent(this, ProfileActivity::class.java).apply {
                putExtra("EXTRA_USUARIO", nuevoUsuario)
            }

            // 3. Iniciar la actividad esperando resultado
            launcherPerfil.launch(intent)
        }

    }
}