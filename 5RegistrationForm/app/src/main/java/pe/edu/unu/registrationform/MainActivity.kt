package pe.edu.unu.registrationform

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val chkHabilitarSegundoNombre = findViewById<CheckBox>(R.id.chk_habilitar_segundo_nombre)
        val etSegundoNombre = findViewById<EditText>(R.id.et_segundo_nombre)

        chkHabilitarSegundoNombre.setOnCheckedChangeListener { _, isChecked ->
            etSegundoNombre.isEnabled = isChecked
            if (!isChecked) {
                etSegundoNombre.text.clear()
            }
        }

        val spinnerEscuelas = findViewById<Spinner>(R.id.spinner_escuelas)
        val tvTituloCursos = findViewById<TextView>(R.id.tv_titutlo_cursos)
        val tableCursos = findViewById<TableLayout>(R.id.table_cursos)
        val btnRegistrar = findViewById<Button>(R.id.btn_registrar)

        val escuelas = listOf("--- Seleccionar una Escuela ---", "Ingeniería de Sistemas", "Medicina Humana", "Derecho")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, escuelas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEscuelas.adapter = adapter

        val baseDeDatosCursos = mapOf(
            "Ingeniería de Sistemas" to listOf(
                Pair("Estructura de Datos", 4),
                Pair("Computación Movil", 2),
                Pair("Metodos Numericos", 3),
            ),
            "Medicina Humana" to listOf(
                Pair("Anatomia", 3),
                Pair("Historia", 2),
                Pair("Fisiologia", 3),
            ),
            "Medicina Humana" to listOf(
                Pair("Derecho I", 3),
                Pair("Historia", 2),
                Pair("Derecho II", 3),
            )
        )

        spinnerEscuelas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener   {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                val escuelaSeleccionada = escuelas[position]

                while(tableCursos.childCount > 1){
                    tableCursos.removeViewAt(1)
                }

                if(position == 0){
                    tvTituloCursos.visibility = View.GONE
                    tableCursos.visibility = View.GONE
                }else{
                    tvTituloCursos.visibility = View.VISIBLE
                    tableCursos.visibility = View.VISIBLE

                    val listaCursos =  baseDeDatosCursos[escuelaSeleccionada] ?: emptyList()

                    for(curso in listaCursos){

                        val fila = TableRow(this@MainActivity)

                        val tvNombre = TextView(this@MainActivity).apply {
                            text = curso.first
                            setTextColor(android.graphics.Color.BLACK)
                            //layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                        }

                        val tvCreditos = TextView(this@MainActivity).apply {
                            text = curso.second.toString()
                            setTextColor(android.graphics.Color.BLACK)
                            gravity = android.view.Gravity.CENTER
                            setPadding(16,0,16,0)
                        }

                        val chkSeleccionarCurso = CheckBox(this@MainActivity).apply {
                            gravity = android.view.Gravity.CENTER
                        }

                        fila.addView(tvNombre)
                        fila.addView(tvCreditos)
                        fila.addView(chkSeleccionarCurso)

                        tableCursos.addView(fila)
                    }
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }

        btnRegistrar.setOnClickListener {
            Toast.makeText(this,"Procesando formulario de matricula...", Toast.LENGTH_SHORT).show()
        }

    }

}