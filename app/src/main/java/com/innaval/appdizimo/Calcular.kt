package com.innaval.appdizimo

object Calcular {
    fun dizimo(salarioBruto:Double, porcentagem:Int):Double{
        return (salarioBruto/100)* porcentagem

    }

    fun primicia(salarioBruto:Double, diasDoMes:Int):Double{
        return salarioBruto/diasDoMes

    }
}