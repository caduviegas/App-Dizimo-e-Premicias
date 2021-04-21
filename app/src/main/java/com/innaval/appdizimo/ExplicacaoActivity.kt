package com.innaval.appdizimo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class ExplicacaoActivity : AppCompatActivity() {

    lateinit var botaoVoltar: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicacao)

        botaoVoltar = findViewById(R.id.iv_volta)
        botaoVoltar.setOnClickListener{
            finish()
        }
    }
}