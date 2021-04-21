package com.innaval.appdizimo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.innaval.appdizimo.MainActivity.Companion.DIAS_PRIMICIA
import com.innaval.appdizimo.MainActivity.Companion.PORCENTAGEM_DIZIMO
import com.innaval.appdizimo.MainActivity.Companion.TEM_PRIMICIAS
import com.innaval.appdizimo.MainActivity.Companion.TIPO_OPERACAO
import com.innaval.appdizimo.databinding.ActivityAjustesIniciaisBinding
import java.text.SimpleDateFormat
import java.util.*

class AjustesIniciaisActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAjustesIniciaisBinding.inflate(layoutInflater) }
    private val tipoOperacao:TipoOperacao by lazy{intent.getSerializableExtra(TIPO_OPERACAO) as TipoOperacao}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupButtons()
        setupCheckBox()
        setupMesAtual()
        setupSlider()
        exibirTipoOperacao()

    }

    private fun exibirTipoOperacao(){
        val isPrimicia = tipoOperacao == TipoOperacao.PRIMICIA
        binding.sbBarraDePorcentagem.visibility=if(isPrimicia)View.GONE else View.VISIBLE
        binding.cbMarcar.visibility=if(isPrimicia)View.GONE else View.VISIBLE
        binding.tvPorcentagem.visibility=if(isPrimicia)View.GONE else View.VISIBLE
        binding.tvValorPorcentagem.visibility=if(isPrimicia)View.GONE else View.VISIBLE
        binding.tvVintePorcento.visibility=if(isPrimicia)View.GONE else View.VISIBLE
        binding.tvCincoPorcento.visibility=if(isPrimicia)View.GONE else View.VISIBLE
    }

    private fun setupSlider(){
        binding.sbBarraDePorcentagem.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val porcentagem = (progress + 1) * 5
                binding.tvValorPorcentagem.text = getString(R.string.porcentagem_variavel, porcentagem)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })
    }

    private fun setupCheckBox(){
        binding.cbMarcar.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                binding.rgPrimicia.visibility = View.VISIBLE
            } else{
                binding.rgPrimicia.visibility = View.GONE
            }
        }
    }

    private fun setupButtons(){
        binding.btEntendimento.setOnClickListener{
            abrirCalculadora()
        }

        binding.ivVolta.setOnClickListener{
            finish()
        }
    }

    private fun setupMesAtual(){
        val mesAtual = SimpleDateFormat("MMMM", Locale("pt","BR")).format(Date())
        binding.rbCalculoComFevereiro.text = getString(R.string.opcao_mes_de_fevereiro, mesAtual)
    }

    private fun getPorcentagemDizimo():Int{
        return (binding.sbBarraDePorcentagem.progress+1)*5
    }

    private fun getDiasPrimicias():Int{
        if(binding.rbCalculoTodosOsMeses.isChecked){
            return 30
        } else{
            val mesAtual = SimpleDateFormat("MM", Locale("pt","BR")).format(Date()).toInt()
            return when(mesAtual){
                1 -> 31
                2 -> 28
                3 -> 31
                5-> 31
                7 -> 31
                8 -> 31
                10 -> 31
                12 -> 31
                else -> 30
            }
        }

    }

    private fun abrirCalculadora(){
        val intent = Intent(this, CalculadoraActivity::class.java)
        intent.putExtra(TIPO_OPERACAO, tipoOperacao)
        intent.putExtra(TEM_PRIMICIAS, binding.cbMarcar.isChecked)
        intent.putExtra(DIAS_PRIMICIA, getDiasPrimicias())
        intent.putExtra(PORCENTAGEM_DIZIMO, getPorcentagemDizimo())
        startActivity(intent)

    }

}