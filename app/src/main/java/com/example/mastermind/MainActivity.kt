package com.example.mastermind

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            val systemBars: Insets =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        val gruppo1 = findViewById<RadioGroup>(R.id.gruppo1)
        val gruppo2 = findViewById<RadioGroup>(R.id.gruppo2)

        val btnInvia = findViewById<Button>(R.id.btnInvia)
        val txtRisultato = findViewById<TextView>(R.id.txtRisultato)

        // Risposte corrette
        val rispostaCorretta1 = "Bergamo"
        val rispostaCorretta2 = "Azzurro"

        btnInvia.setOnClickListener {

            var punteggio = 0

            // DOMANDA 1
            val idScelta1 = gruppo1.checkedRadioButtonId

            if (idScelta1 != -1) {

                val radio1 = findViewById<RadioButton>(idScelta1)

                val testoRisposta1 = radio1.text.toString()

                if (testoRisposta1 == rispostaCorretta1) {
                    punteggio++
                }
            }

            // DOMANDA 2
            val idScelta2 = gruppo2.checkedRadioButtonId

            if (idScelta2 != -1) {

                val radio2 = findViewById<RadioButton>(idScelta2)

                val testoRisposta2 = radio2.text.toString()

                if (testoRisposta2 == rispostaCorretta2) {
                    punteggio++
                }
            }

            txtRisultato.text = "Punteggio: $punteggio"
        }
    }
}