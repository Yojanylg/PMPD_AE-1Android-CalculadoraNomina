package com.example.calculadoranomina

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    // este objeto me permite estandarizar los argumentos que luego pasare en el metodo
    // que navega entre pantallas
    companion object {
        const val SALARIO_BRUTO = "SALARIO_BRUTO"
        const val DEDUCCION_CC = "DEDUCCION_CC"
        const val DEDUCCION_FP = "DEDUCCION_FP"
        const val DEDUCCION_MEI = "DEDUCCION_MEI"
        const val RETENCION_IRPF = "RETENCION_IRPF"
        const val SALARIO_NETO = "SALARIO_NETO"
    }

        private lateinit var cvSalario: CardView
        private lateinit var etSalario: EditText

        private lateinit var cvPagas: CardView
        private lateinit var tvPagas: TextView
        private lateinit var btnRestarPagas: FloatingActionButton
        private lateinit var btnAumentarPagas: FloatingActionButton

        private lateinit var cvEdad: CardView
        private lateinit var tvEdad: TextView
        private lateinit var btnRestarEdad: FloatingActionButton
        private lateinit var btnAumentarEdad: FloatingActionButton

        private lateinit var cvNumeroHijos: CardView
        private lateinit var tvHijos: TextView
        private lateinit var btnRestarHijos: FloatingActionButton
        private lateinit var btnAumentarHijos: FloatingActionButton

        private lateinit var cvDiscapacidad: CardView
        private lateinit var tvDiscapacidad: TextView
        private lateinit var btnRestarDiscapacidad: FloatingActionButton
        private lateinit var btnAumentarDiscapacidad: FloatingActionButton

        private lateinit var cvSoltero: CardView
        private lateinit var cvCasado: CardView

        private lateinit var btnCalcularNomina: Button

        private var SolteroSelected: Boolean = true
        private var CasadoSelectec: Boolean = false
        private var pagasDefecto = 14
        private var edadDefecto = 16
        private var hijosDefecto = 0
        private var discapacidadDefecto = 0
        private var salarioBruto = 0.00



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Definimos 3 metodos (tomemoslo como un buen patron)
        //Para iniciar los componentes visuales
        initComponents()

        //Para creamos los listenners de los eventos
        initListeners()

        //Configuraciones visuales de los componentes
        initUI()
    }

    private fun initComponents() {
        this.cvSalario = findViewById(R.id.cvSalarioBruto)
        this.etSalario = findViewById(R.id.etSalarioBruto)

        this.cvPagas = findViewById(R.id.cvPagas)
        this.tvPagas = findViewById(R.id.tvPagas)
        this.btnAumentarPagas = findViewById(R.id.btnAumentarPagas)
        this.btnRestarPagas = findViewById(R.id.btnRestarPagas)

        this.cvEdad = findViewById(R.id.cvEdad)
        this.tvEdad = findViewById(R.id.tvEdad)
        this.btnAumentarEdad = findViewById(R.id.btnAumentarEdad)
        this.btnRestarEdad = findViewById(R.id.btnRestarEdad)

        this.cvNumeroHijos = findViewById(R.id.cvNumeroHijos)
        this.tvHijos = findViewById(R.id.tvHijos)
        this.btnAumentarHijos = findViewById(R.id.btnAumentarHijos)
        this.btnRestarHijos = findViewById(R.id.btnRestarHijos)

        this.cvDiscapacidad = findViewById(R.id.cvDiscapacidad)
        this.tvDiscapacidad = findViewById(R.id.tvDiscapacidad)
        this.btnAumentarDiscapacidad = findViewById(R.id.btnAumentarDiscapacidad)
        this.btnRestarDiscapacidad = findViewById(R.id.btnRestarDiscapacidad)

        this.cvSoltero = findViewById(R.id.cvSoltero)
        this.cvCasado = findViewById(R.id.cvCasado)

        this.btnCalcularNomina = findViewById(R.id.btnCalcular)
    }

    private fun initListeners() {

        this.etSalario.addTextChangedListener(object : TextWatcher {


            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val df = DecimalFormat("#.##")
                // Convertir a Double y formatear
                val salario = s.toString().toDouble()
                salarioBruto = df.format(salario).toDouble()


            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        this.btnRestarPagas.setOnClickListener{
            this.pagasDefecto -= 1
            setPagas()
        }
        this.btnAumentarPagas.setOnClickListener{
            this.pagasDefecto += 1
            setPagas()
        }
        this.btnRestarEdad.setOnClickListener{
            this.edadDefecto -= 1
            setEdad()
        }
        this.btnAumentarEdad.setOnClickListener{
            this.edadDefecto += 1
            setEdad()
        }
        this.btnRestarHijos.setOnClickListener{
            this.hijosDefecto -= 1
            setNumeroHijos()
        }
        this.btnAumentarHijos.setOnClickListener{
            this.hijosDefecto += 1
            setNumeroHijos()
        }
        this.btnRestarDiscapacidad.setOnClickListener{
            this.discapacidadDefecto -= 1
            setDiscapacidad()
        }
        this.btnAumentarDiscapacidad.setOnClickListener{
            this.discapacidadDefecto += 1
            setDiscapacidad()
        }
        this.cvSoltero.setOnClickListener {
            if(!this.SolteroSelected){
                cambiarColorSolteroCasado()

            }
            establecerColorSolteroCasado()
        }
        this.cvCasado.setOnClickListener {
            if(!this.CasadoSelectec){
                cambiarColorSolteroCasado()

            }
            establecerColorSolteroCasado()
        }
        this.btnCalcularNomina.setOnClickListener {
            val salarioBruto = this.salarioBruto
            val deducionCC = calcularCC()
            val deducionFP = calcularFP()
            val deducionMEI = calcularMEI()
            val retencionIRPF = calcularRetencionIRPF()
            val salarioNeto = calcularSalarioNeto()
            navigateToResult(salarioBruto, deducionCC, deducionFP, deducionMEI, retencionIRPF, salarioNeto)
        }
    }

    private fun calcularCC(): Double {
        val df = DecimalFormat("#.##")
        val salBruto = this.salarioBruto
        val cc = salBruto * 0.047
        val result = df.format(cc).toDouble()
        return result
    }
    private fun calcularFP(): Double {
        val df = DecimalFormat("#.##")
        val salBruto = this.salarioBruto
        val fp = salBruto * 0.0155
        val result = df.format(fp).toDouble()
        return result
    }
    private fun calcularMEI(): Double {
        val df = DecimalFormat("#.##")
        val salBruto = this.salarioBruto
        val mei = salBruto * 0.001
        val result = df.format(mei).toDouble()
        return result
    }
    private fun calcularRetencionIRPF(): Double {
        val df = DecimalFormat("#.##")
        val salBruto = this.salarioBruto
        val baseIRPF = salBruto - calcularCC() - calcularFP() - calcularMEI()
        var tramoIRPF = 1.0

        if(salBruto >= 300000){
            tramoIRPF = 0.47
        } else if (salBruto >= 60000){
            tramoIRPF = 0.45
        } else if (salBruto >= 32500){
            tramoIRPF = 0.37
        } else if (salBruto >=20200){
            tramoIRPF = 0.30
        } else if (salBruto >= 12450){
            tramoIRPF = 0.24
        } else {
            tramoIRPF = 0.19
        }

        val retencionIRPF = baseIRPF * tramoIRPF
        val result = df.format(retencionIRPF).toDouble()

        return result
    }
    private fun calcularSalarioNeto(): Double {
        val df = DecimalFormat("#.##")
        val salBruto = this.salarioBruto
        val salarioNeto = salBruto - calcularCC() - calcularFP() - calcularMEI() - calcularRetencionIRPF()
        val result = df.format(salarioNeto).toDouble()
        return result
    }
    private fun initUI() {
        this.establecerColorSolteroCasado()
        this.setEdad()
        this.setPagas()
        this.setNumeroHijos()
        this.setDiscapacidad()
    }
    private fun setPagas(){
        this.tvPagas.text = this.pagasDefecto.toString()
    }
    private fun setEdad(){
        this.tvEdad.text = this.edadDefecto.toString()
    }
    private fun setNumeroHijos(){
        this.tvHijos.text = this.hijosDefecto.toString()
    }
    private fun setDiscapacidad(){
        this.tvDiscapacidad.text = this.discapacidadDefecto.toString() + " %"
    }
    private fun cambiarColorSolteroCasado() {
        this.CasadoSelectec = !this.CasadoSelectec
        this.SolteroSelected = !this.SolteroSelected
    }
    private fun establecerColorSolteroCasado() {
        cvCasado.setCardBackgroundColor(this.getBackgroundColor(CasadoSelectec))
        cvSoltero.setCardBackgroundColor(this.getBackgroundColor(SolteroSelected))
    }
    private fun getBackgroundColor(isSelectedComponet: Boolean): Int {

        val colorReference = if(isSelectedComponet){
            R.color.component_selcted
        } else {
            R.color.component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun navigateToResult(salarioBruto: Double, deduccionCC: Double, deduccionFP: Double, deduccionMEI: Double, retencionIRPF: Double, salarioNeto: Double) {
        //Creamos el objeto intent
        val intent = Intent(this, ResultMainActivity::class.java)
        //Añadimos el extra necesario para pasar el resultado del IMC
        intent.putExtra(SALARIO_BRUTO, salarioBruto)
        intent.putExtra(DEDUCCION_CC, deduccionCC)
        intent.putExtra(DEDUCCION_FP, deduccionFP)
        intent.putExtra(DEDUCCION_MEI, deduccionMEI)
        intent.putExtra(RETENCION_IRPF, retencionIRPF)
        intent.putExtra(SALARIO_NETO, salarioNeto)
        //Navegamos a la siguiente activity
        this.startActivity(intent)

    }
}