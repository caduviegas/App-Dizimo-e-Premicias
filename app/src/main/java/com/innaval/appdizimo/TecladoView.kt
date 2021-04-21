package com.innaval.appdizimo

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import com.innaval.appdizimo.databinding.ViewTecladoBinding

class TecladoView: LinearLayout {
    constructor(context: Context):super(context){
        init(context)
    }
    constructor(context: Context, attributeSet: AttributeSet):super(context, attributeSet){
        init(context)
    }
    private val binding by lazy{ViewTecladoBinding.bind(inflate(context, R.layout.view_teclado, this))}
    private var numero: Double = 0.0
    private var listener: TecladoListener? = null
    private val numeroClickListener = OnClickListener {

        (it as Button).let{
            val num = it.text.toString().toInt()
            numero = (numero * 10) + (num/100.0)
            listener?.numeroMudou(numero)
        }

    }

    private val apagarClickListener = OnClickListener{
        numero = ((numero*100)-((numero*100)%10))/1000
        listener?.numeroMudou(numero)
    }

    private fun init(context:Context) {
        binding.btUm.setOnClickListener (numeroClickListener)
        binding.btDois.setOnClickListener (numeroClickListener)
        binding.btTres.setOnClickListener (numeroClickListener)
        binding.btQuatro.setOnClickListener (numeroClickListener)
        binding.btCinco.setOnClickListener (numeroClickListener)
        binding.btSeis.setOnClickListener (numeroClickListener)
        binding.btSete.setOnClickListener (numeroClickListener)
        binding.btOito.setOnClickListener (numeroClickListener)
        binding.btNove.setOnClickListener (numeroClickListener)
        binding.btApagar.setOnClickListener (apagarClickListener)
        binding.btZero.setOnClickListener (numeroClickListener)
        binding.btCalcular.setOnClickListener{
            listener?.clickCalcular(numero)
        }
    }
    fun setTecladoListener(listener:TecladoListener){
        this.listener = listener
    }


    interface TecladoListener{
        fun numeroMudou(numero:Double)
        fun clickCalcular(numero:Double)
    }
}