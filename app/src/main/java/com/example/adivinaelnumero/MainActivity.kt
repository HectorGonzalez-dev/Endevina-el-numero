package com.example.adivinaelnumero

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var secretNumber = 0
    private var attempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Interface
        val button = findViewById<Button>(R.id.button)
        val userInput = findViewById<EditText>(R.id.editTextNumber)
        val historial = findViewById<TextView>(R.id.historial)
        val attemptCounter = findViewById<TextView>(R.id.attemptCounter)
        val scrollView = findViewById<ScrollView>(R.id.scrollView)

        // Others
        secretNumber = Random.nextInt(1, 101)

        button.setOnClickListener {
            var text: String
            val duration = Toast.LENGTH_SHORT

            val guess = userInput.text.toString().toIntOrNull()

            // Pressing the button being empty
            if (guess == null) {
                text = "Introduce un número válido"
                val toast = Toast.makeText(this, text, duration)
                toast.show()
            // Putting a number out of range
            } else if (guess !in 1..100) {
                text = "Introduce un número entre 1 y 100"
                val toast = Toast.makeText(this, text, duration)
                toast.show()
            // Guessing a wrong number
            } else if (guess != secretNumber) {
                // Plus 1 successful attempt
                attempts++
                attemptCounter.text = "Intentos: $attempts"

                // Checking if the guess is bigger or lower
                if (guess > secretNumber) {
                    text = "Número incorrecto, es mas pequeño. ($secretNumber)"
                    historial.append("\nIntento $attempts: $guess | INCORRECTO, es mas pequeño.")
                } else {
                    text = "Número incorrecto, es mas grande. ($secretNumber)"
                    historial.append("\nIntento $attempts: $guess | INCORRECTO, es mas grande.")
                }
                val toast = Toast.makeText(this, text, duration)
                toast.show()

            // Guessing the right number
            } else {
                // Plus 1 successful attempt
                attempts++
                attemptCounter.text = "Intentos: $attempts"
                historial.append("\nIntento $attempts: $guess | Número correcto, has ganado!")

                // Winning screen
                AlertDialog.Builder(this)
                    .setTitle("¡Felicidades!")
                    .setMessage("Has acertado el número en $attempts intentos.")
                    .setPositiveButton("OK") { _, _ ->

                        // Reset attempt counter and history
                        attempts = 0
                        historial.text = "Historial:"
                        attemptCounter.text = "Intentos: 0"

                        // Regenerate the secretNumber after winning
                        secretNumber = Random.nextInt(1, 101)

                    }
                    .show()

            }

            // Automatic scroll
            scrollView.post {
                scrollView.fullScroll(View.FOCUS_DOWN)
            }

            // Cleans user input
            userInput.text.clear()

        }

    }
}