package com.innaval.appdizimo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    lateinit var botaoExplicacao: Button
    lateinit var botaoDizimo: CardView
    lateinit var botaoPrimicias: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        botaoExplicacao = findViewById(R.id.bt_entendimento)
        botaoExplicacao.setOnClickListener {
            val intent = Intent(this, ExplicacaoActivity::class.java)
            startActivity(intent)
        }

        botaoDizimo = findViewById(R.id.cv_dizimo)
        botaoPrimicias = findViewById(R.id.cv_premicia)
        botaoDizimo.setOnClickListener {
            val intent = Intent(this, AjustesIniciaisActivity::class.java)
            intent.putExtra(TIPO_OPERACAO, TipoOperacao.DIZIMO)
            startActivity(intent)

        }

        botaoPrimicias.setOnClickListener {
            val intent = Intent(this, AjustesIniciaisActivity::class.java)
            intent.putExtra(TIPO_OPERACAO, TipoOperacao.PRIMICIA)
            startActivity(intent)

        }

    }

    companion object{
        const val TIPO_OPERACAO = "TipoOperacao"
        const val PORCENTAGEM_DIZIMO = "PorcentagemDizimo"
        const val DIAS_PRIMICIA ="DiasPrimicias"
        const val TEM_PRIMICIAS = "TemPrimicias"
        const val SALARIO_BRUTO = "SalarioBruto"
    }


}