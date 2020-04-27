package com.example.tts

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.LANG_MISSING_DATA
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import java.util.UUID


class MainActivity : AppCompatActivity() {
    lateinit var textToSpeech: TextToSpeech;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mButtonSpeak: Button = findViewById(R.id.button_speak)
        val editText: EditText = findViewById(R.id.edit_text)
        val btnCheckLang: Button = findViewById(R.id.button_check_lang)

        textToSpeech = TextToSpeech(this) { status ->
            val result = textToSpeech.setLanguage(Locale("vi", "VN"))

            if (result != TextToSpeech.LANG_NOT_SUPPORTED || result != LANG_MISSING_DATA) {
                Toast.makeText(this, "Lang not supported", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
        }

        mButtonSpeak.setOnClickListener {
            textToSpeech.speak(editText.text.toString(), TextToSpeech.QUEUE_FLUSH, null, UUID.randomUUID().toString())
        }

        btnCheckLang.setOnClickListener{
            textToSpeech.availableLanguages.forEach {
                Log.d("Supported lang", "Country: ${it.country}, language: ${it.language}")
            }
        }
    }
}
