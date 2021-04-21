package com.innaval.appdizimo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.innaval.appdizimo.MainActivity.Companion.SALARIO_BRUTO
import com.innaval.appdizimo.databinding.ActivityResultadosBinding

class ResultadosActivity : AppCompatActivity() {
    private val binding by lazy { ActivityResultadosBinding.inflate(layoutInflater) }
    private val tipoOperacao:TipoOperacao by lazy{intent.getSerializableExtra(MainActivity.TIPO_OPERACAO) as TipoOperacao}
    private val temPrimicia:Boolean by lazy{intent.getBooleanExtra(MainActivity.TEM_PRIMICIAS,true)}
    private val porcentagemDizimo:Int by lazy{intent.getIntExtra(MainActivity.PORCENTAGEM_DIZIMO,10)}
    private val diasPrimicias:Int by lazy{intent.getIntExtra(MainActivity.DIAS_PRIMICIA, 30)}
    private val salarioBruto:Double by lazy{intent.getDoubleExtra(SALARIO_BRUTO,0.0)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivVolta.setOnClickListener{
            onBackPressed()

        }
        exibirResultados()

        exibirTipoOperacao()
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)

    }

    fun exibirResultados(){
        val primicia = if(temPrimicia) Calcular.primicia(salarioBruto, diasPrimicias) else 0.0
        val dizimo = Calcular.dizimo(salarioBruto-primicia,porcentagemDizimo)
        val salarioRestante = salarioBruto-dizimo-primicia
        binding.tvValor2.text = getString(R.string.dinheiro_formatado, primicia)
        binding.tvValor1.text = getString(R.string.dinheiro_formatado, dizimo)
        binding.tvValorDinheiroDizimo.text = getString(R.string.dinheiro_formatado, salarioBruto)
        binding.tvValorDinheiroPrimicia.text = getString(R.string.dinheiro_formatado,salarioRestante)
        binding.tvValorDias.text = diasPrimicias.toString()
        binding.tvValorPorcentagem.text= getString(R.string.porcentagem_variavel,porcentagemDizimo)
    }

    private fun exibirTipoOperacao() {
        val isPrimicia = tipoOperacao == TipoOperacao.PRIMICIA
        binding.tvPorcentagemDizimo.visibility = if (isPrimicia) View.GONE else View.VISIBLE
        binding.tvValor1.visibility = if (isPrimicia) View.GONE else View.VISIBLE
        binding.tvValorPorcentagem.visibility = if (isPrimicia) View.GONE else View.VISIBLE
        binding.tvTitulo.visibility = if (isPrimicia) View.GONE else View.VISIBLE
    }
}