package com.innaval.appdizimo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.innaval.appdizimo.MainActivity.Companion.DIAS_PRIMICIA
import com.innaval.appdizimo.MainActivity.Companion.PORCENTAGEM_DIZIMO
import com.innaval.appdizimo.MainActivity.Companion.SALARIO_BRUTO
import com.innaval.appdizimo.MainActivity.Companion.TEM_PRIMICIAS
import com.innaval.appdizimo.databinding.ActivityCalculadoraBinding

class CalculadoraActivity : AppCompatActivity(), TecladoView.TecladoListener {
    private val binding by lazy { ActivityCalculadoraBinding.inflate(layoutInflater) }
    private val tipoOperacao:TipoOperacao by lazy{intent.getSerializableExtra(MainActivity.TIPO_OPERACAO) as TipoOperacao}
    private val temPrimicia:Boolean by lazy{intent.getBooleanExtra(TEM_PRIMICIAS,true)}
    private val porcentagemDizimo:Int by lazy{intent.getIntExtra(PORCENTAGEM_DIZIMO,10)}
    private val diasPrimicias:Int by lazy{intent.getIntExtra(DIAS_PRIMICIA, 30)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvCalculadora.setTecladoListener(this)
        binding.ivVolta.setOnClickListener{
            finish()
        }

    }

    override fun numeroMudou(numero: Double) {
        binding.tvValor.text = getString(R.string.valor_formatado, numero)
    }

    override fun clickCalcular(numero: Double) {
        val intent = Intent(this, ResultadosActivity::class.java)
        intent.putExtra(MainActivity.TIPO_OPERACAO, tipoOperacao)
        intent.putExtra(TEM_PRIMICIAS, temPrimicia)
        intent.putExtra(DIAS_PRIMICIA, diasPrimicias)
        intent.putExtra(PORCENTAGEM_DIZIMO, porcentagemDizimo)
        intent.putExtra(SALARIO_BRUTO,numero)
        startActivity(intent)

    }
}