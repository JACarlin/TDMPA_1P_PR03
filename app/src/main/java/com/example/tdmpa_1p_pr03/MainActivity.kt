package com.example.tdmpa_1p_pr03

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnCalcular = findViewById<Button>(R.id.btnCalcular);
        var rdGrupo = findViewById<RadioGroup>(R.id.rbGrupo);
        var txtTotal= findViewById<TextView>(R.id.txtTotal);

        btnCalcular.setOnClickListener {
            val selectedOption: Int = rdGrupo!!.checkedRadioButtonId;
            txtTotal.text = calcular(selectedOption);
        }

        var btnAdd = findViewById<Button>(R.id.btnAdd)
        var txtLista = findViewById<TextView>(R.id.txtLista)
        btnAdd.setOnClickListener {
            var txtNumero = findViewById<EditText>(R.id.txtNumero)

            try {
                var num: Int = txtNumero.text.toString().toInt();
                numeros.add(num);
                txtLista.text = imprimir()
            }catch (e:Exception){

            mensaje("El campo esta vacio")
            }
        }



    }
    var numeros : MutableList<Int> = mutableListOf()
    fun imprimir():String{
        var cadena = ""
        for (i in  0 .. numeros.size-1){
            cadena = cadena + "${numeros[i]}, "
        }
        return cadena
    }
    fun calcular(id: Int):String{
        var total = "";
        when{
            id == -1 -> mensaje("Seleccione un calculo")
            id == 2131231229 -> total = suma()
            id == 2131231226 -> total = media()
            id == 2131231227 -> total = mediana()
            id == 2131231228 -> total = moda()
        }
        return total;

    }
    fun suma():String{
        var total:Int = 0;
        for (i in numeros){
            total = total + i;
        }

        return total.toString();
    }
    fun media():String{
        var total:Double = 0.0;
        for (i in numeros){
            total = total + i;
        }
        total = total/numeros.size;
        return total.toString();
    }
    fun mediana():String{
        val listaOrdenada = numeros.sorted()
        val n = listaOrdenada.size
        val medianas = mutableListOf<Int>()

        if (n % 2 == 1) {

            medianas.add(listaOrdenada[n / 2])
        } else {

            val medio1 = listaOrdenada[n / 2 - 1]
            val medio2 = listaOrdenada[n / 2]
            medianas.add(medio1)
            medianas.add(medio2)
        }

        return medianas.toString()
    }
    fun moda():String{
        val conteo = mutableMapOf<Int, Int>()


        for (numero in numeros) {
            conteo[numero] = conteo.getOrDefault(numero, 0) + 1
        }


        val modaFrecuenciaMaxima = conteo.values.maxOrNull()


        val moda = mutableListOf<Int>()
        if (modaFrecuenciaMaxima != null) {
            for ((numero, frecuencia) in conteo) {
                if (frecuencia == modaFrecuenciaMaxima) {
                    moda.add(numero)
                }
            }
        }

        return moda.toString()
    }

    fun mensaje(text: String){
        var toast = Toast.makeText(this,text,
            Toast.LENGTH_SHORT).show()
    }
}