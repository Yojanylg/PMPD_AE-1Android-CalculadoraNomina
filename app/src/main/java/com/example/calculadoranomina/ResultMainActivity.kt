package com.example.calculadoranomina

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadoranomina.MainActivity.Companion.DEDUCCION_CC
import com.example.calculadoranomina.MainActivity.Companion.DEDUCCION_FP
import com.example.calculadoranomina.MainActivity.Companion.DEDUCCION_MEI
import com.example.calculadoranomina.MainActivity.Companion.RETENCION_IRPF
import com.example.calculadoranomina.MainActivity.Companion.SALARIO_BRUTO
import com.example.calculadoranomina.MainActivity.Companion.SALARIO_NETO

class ResultMainActivity : AppCompatActivity() {

    private lateinit var tvSalarioBrutoAnual: TextView
    private lateinit var tvRetencionIRPF: TextView
    private lateinit var tvCC: TextView
    private lateinit var tvFP: TextView
    private lateinit var tvMEI: TextView
    private lateinit var tvSalarioNetoAnual: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Obtenemos el extra del intent del valor IMC, leemos la key con el compagnion object
        val salarioBruto = intent.extras?.getDouble(SALARIO_BRUTO)?: -1.0 //si no obtenemos el extra le damos valor -1
        val deduccionCC = intent.extras?.getDouble(DEDUCCION_CC)?: -1.0 //si no obtenemos el extra le damos valor -1
        val deduccionFP = intent.extras?.getDouble(DEDUCCION_FP)?: -1.0 //si no obtenemos el extra le damos valor -1
        val dedeccionMEI = intent.extras?.getDouble(DEDUCCION_MEI)?: -1.0 //si no obtenemos el extra le damos valor -1
        val retencionIRPF = intent.extras?.getDouble(RETENCION_IRPF)?: -1.0 //si no obtenemos el extra le damos valor -1
        val salarioNeto = intent.extras?.getDouble(SALARIO_NETO)?: -1.0 //si no obtenemos el extra le damos valor -1

        //Definimos 3 metodos (tomemoslo como un buen patron)
        //Para iniciar los componentes visuales
        initComponents()
        //Para creamos los listenners de los eventos
        initListenners()
        //Configuraciones visuales de los componentes
        initUI(salarioBruto, deduccionCC, deduccionFP, dedeccionMEI, retencionIRPF, salarioNeto)

    }

    private fun initUI(salarioBruto: Double, deduccionCC: Double, deduccionFP: Double, deduccionMEI: Double, retencionIRPF: Double, salarioNeto: Double) {

        this.setSalarioBruto(salarioBruto)
        this.setDeduccionCC(deduccionCC)
        this.setDeduccionFP(deduccionFP)
        this.setDeduccionMEI(deduccionMEI)
        this.setRetencionIRPF(retencionIRPF)
        this.setSalarioNeto(salarioNeto)

    }

    private fun setSalarioNeto(salarioNeto: Double) {

        this.tvSalarioNetoAnual.text = salarioNeto.toString()
    }

    private fun setRetencionIRPF(retencionIRPF: Double) {

        this.tvRetencionIRPF.text = retencionIRPF.toString()
    }

    private fun setDeduccionMEI(deduccionMEI: Double) {

        this.tvMEI.text = deduccionMEI.toString()
    }

    private fun setDeduccionFP(deduccionFP: Double) {

        this.tvFP.text = deduccionFP.toString()
    }

    private fun setDeduccionCC(deduccionCC: Double) {
        this.tvCC.text = deduccionCC.toString()
    }

    private fun setSalarioBruto(salarioBruto: Double) {
        this.tvSalarioNetoAnual.text = salarioBruto.toString()
    }

    private fun initListenners() {

    }

    private fun initComponents() {
        this.tvSalarioBrutoAnual = findViewById(R.id.tvSalarioBrutoAnual)
        this.tvCC = findViewById(R.id.tvCC)
        this.tvFP = findViewById(R.id.tvFP)
        this.tvMEI = findViewById(R.id.tvMEI)
        this.tvRetencionIRPF = findViewById(R.id.tvRetencionIRPF)
        this.tvSalarioNetoAnual = findViewById(R.id.tvSalarioNetoAnual)

    }


}