package com.example.mastermind

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. VARIABILI LOCALI DI STATO (Niente "private var" fuori da qui)
        var indiceDomandaAttuale = 0
        var punteggio = 0

        // 2. CARICAMENTO DATI DA ARRAYS.XML
        val domande = resources.getStringArray(R.array.lista_domande)
        val risposteCorrette = resources.getStringArray(R.array.risposte_corrette)

        // Elenco degli ID delle risorse XML per le opzioni delle domande
        val idOpzioniXML = arrayOf(
            R.array.opzioni_d1,
            R.array.opzioni_d2,
            R.array.opzioni_d3
        )

        // Riferimenti ai componenti grafici del layout XML
        val txtDomanda = findViewById<TextView>(R.id.txtDomanda)
        val gruppoRisposte = findViewById<RadioGroup>(R.id.gruppoRisposte)
        val txtRisultato = findViewById<TextView>(R.id.txtRisultato)
        val btnAvanti = findViewById<Button>(R.id.btnAvanti)
        val btnInvia = findViewById<Button>(R.id.btnInvia)

        // 3. MOSTRA LA PRIMA DOMANDA ALL'AVVIO
        txtDomanda.text = domande[indiceDomandaAttuale]
        var opzioniCorrenti = resources.getStringArray(idOpzioniXML[indiceDomandaAttuale])
        findViewById<RadioButton>(R.id.D1R1).text = opzioniCorrenti[0]
        findViewById<RadioButton>(R.id.D1R2).text = opzioniCorrenti[1]
        findViewById<RadioButton>(R.id.D1R3).text = opzioniCorrenti[2]
        findViewById<RadioButton>(R.id.D1R4).text = opzioniCorrenti[3]

        // 4. LOGICA DEL TASTO AVANTI
        btnAvanti.setOnClickListener {
            // Controlla la risposta selezionata
            val idSelezionato = gruppoRisposte.checkedRadioButtonId
            if (idSelezionato != -1) {
                val radioSelezionato = findViewById<RadioButton>(idSelezionato)
                if (radioSelezionato.text.toString() == risposteCorrette[indiceDomandaAttuale]) {
                    punteggio++
                }
            }

            // Controlla se passare alla prossima domanda o bloccare il gioco
            if (indiceDomandaAttuale < domande.size - 1) {
                indiceDomandaAttuale++

                // Aggiorna lo schermo con i nuovi testi della nuova domanda
                txtDomanda.text = domande[indiceDomandaAttuale]
                gruppoRisposte.clearCheck()
                opzioniCorrenti = resources.getStringArray(idOpzioniXML[indiceDomandaAttuale])
                findViewById<RadioButton>(R.id.D1R1).text = opzioniCorrenti[0]
                findViewById<RadioButton>(R.id.D1R2).text = opzioniCorrenti[1]
                findViewById<RadioButton>(R.id.D1R3).text = opzioniCorrenti[2]
                findViewById<RadioButton>(R.id.D1R4).text = opzioniCorrenti[3]
            } else {
                txtDomanda.text = "Hai finito le domande! Premi INVIA per vedere il punteggio."
                btnAvanti.isEnabled = false
            }
        }

        // 5. LOGICA DEL TASTO INVIA
        btnInvia.setOnClickListener {
            // Se l'utente clicca direttamente INVIA sull'ultima domanda senza premere prima AVANTI
            if (btnAvanti.isEnabled) {
                val idSelezionato = gruppoRisposte.checkedRadioButtonId
                if (idSelezionato != -1) {
                    val radioSelezionato = findViewById<RadioButton>(idSelezionato)
                    if (radioSelezionato.text.toString() == risposteCorrette[indiceDomandaAttuale]) {
                        punteggio++
                    }
                }
                btnAvanti.isEnabled = false
            }

            // Mostra il risultato finale
            txtRisultato.text = "Punteggio Finale: $punteggio / ${domande.size}"
        }
    }
}