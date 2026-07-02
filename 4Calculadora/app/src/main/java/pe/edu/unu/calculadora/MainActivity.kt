package pe.edu.unu.calculadora

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
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

        //recuperando los componentes del xml
        val etNum1 = findViewById<EditText>(R.id.etNumber1)
        val etNum2 = findViewById<EditText>(R.id.etNumber2)
        val rgOptions = findViewById<RadioGroup>(R.id.rgOperations)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        //evento clic en boton calcular
        btnCalculate.setOnClickListener {
            val textNum1 = etNum1.text.toString()
            val textNum2 = etNum2.text.toString()

            //validando entradas
            if(textNum1.isEmpty() || textNum2.isEmpty()){
                Toast.makeText(this, "Por favor, ingresar ambos numeros", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val num1 = textNum1.toDouble()
            val num2 = textNum2.toDouble()
            var result = 0.0

            val selectedOperation = rgOptions.checkedRadioButtonId

            when (selectedOperation){
                R.id.rbAdd -> result = num1 + num2
                R.id.rbSubtract -> result = num1 - num2
                R.id.rbMultiply -> result = num1 * num2
                R.id.rbDivide -> {
                    if(num2 == 0.0){
                        Toast.makeText(this, "El divisor no puede ser cero.", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    result = num1 / num2
                }
            }

            tvResult.text = "Result : $result"

        }

    }



}