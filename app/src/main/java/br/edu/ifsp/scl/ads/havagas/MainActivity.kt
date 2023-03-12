package br.edu.ifsp.scl.ads.havagas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import br.edu.ifsp.scl.ads.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            saveBtn.setOnClickListener { view ->
                var message : String = ""

                if (fullNameInp.text.isNotBlank()) message += "Nome completo: ${fullNameInp.text}\n"

                if (emailInp.text.isNotBlank()) {
                    message += "E-mail: ${emailInp.text}\n"
                    if (emailChk.isChecked) message += "Receber e-mails: Sim\n"
                }

                if (phoneInp.text.isNotBlank()) {
                    val phonetype = if (phoneResidencialRd.isChecked) "Residencial" else "Comercial"
                    message += "Telefone (${phonetype}): ${phoneInp.text}\n"
                }
                if (cellphoneInp.text.isNotBlank()) message += "Telefone (Celular): ${cellphoneInp.text}\n"

                message += "Sexo: " + if (sexMaleRd.isChecked) "Masculino\n" else "Feminino\n"

                message += "Formação: ${educationSp.selectedItem}\n"
                if (conclusionYearInp.text.isNotBlank()) message += "Ano de conclusão: ${conclusionYearInp.text}\n"

                when (educationSp.selectedItemPosition) {
                    2, 3-> {
                        if (institutionInp.text.isNotBlank()) message += "Instituição: ${institutionInp.text}\n"
                    }
                    4, 5 -> {
                        if (institutionInp.text.isNotBlank()) message += "Instituição: ${institutionInp.text}\n"
                        if (monographyInp.text.isNotBlank()) message += "Monografia: ${monographyInp.text}\n"
                        if (supervisorInp.text.isNotBlank()) message += "Orientador: ${supervisorInp.text}\n"
                    }
                }

                if (interestInp.text.isNotBlank()) message += "Vagas de interesse: ${interestInp.text}\n"

                Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
            }

            cleanBtn.setOnClickListener { view ->
                fullNameInp.setText("")
                emailInp.setText("")
                emailChk.isChecked = false
                phoneInp.setText("")
                phoneRd.check(phoneResidencialRd.id)
                phoneSw.isChecked = false
                sexRd.check(sexMaleRd.id)
                educationSp.setSelection(0)
                conclusionYearInp.setText("")
                institutionInp.setText("")
                monographyInp.setText("")
                supervisorInp.setText("")
                interestInp.setText("")
            }

            phoneSw.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked)
                    cellphoneContainer.visibility = View.VISIBLE
                else {
                    cellphoneContainer.visibility = View.GONE
                    cellphoneInp.setText("")
                }
            }

            educationSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(educationSp.selectedItem) {
                        "Fundamental", "Médio" -> {
                            institutionContainer.visibility = View.GONE
                            monographyContainer.visibility = View.GONE
                            supervisorContainer.visibility = View.GONE
                        }
                        "Graduação", "Especialização" -> {
                            institutionContainer.visibility = View.VISIBLE
                            monographyContainer.visibility = View.GONE
                            supervisorContainer.visibility = View.GONE
                        }
                        "Mestrado", "Doutorado" -> {
                            institutionContainer.visibility = View.VISIBLE
                            monographyContainer.visibility = View.VISIBLE
                            supervisorContainer.visibility = View.VISIBLE
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        with(outState) {
            with(binding) {
                putString("name", fullNameInp.text.toString())
                putString("email", emailInp.text.toString())
                putBoolean("emailCheck", emailChk.isChecked)
                putString("phone", phoneInp.text.toString())
                putInt("phoneTypeId", phoneRd.checkedRadioButtonId)
                putBoolean("useCellphone", phoneSw.isChecked)
                putString("cellphone", cellphoneInp.text.toString())
                putInt("sexId", sexRd.checkedRadioButtonId)
                putInt("education", educationSp.selectedItemPosition)
                putString("conclusion", conclusionYearInp.text.toString())
                putString("institution", institutionInp.text.toString())
                putString("monography", monographyInp.text.toString())
                putString("supervisor", supervisorInp.text.toString())
                putString("interests", interestInp.text.toString())
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        with(savedInstanceState) {
            with(binding) {
                fullNameInp.setText(getString("name"))
                emailInp.setText(getString("email"))
                emailChk.isChecked = getBoolean("emailCheck")
                phoneInp.setText(getString("phone"))
                phoneRd.check(getInt("phoneTypeId"))
                phoneSw.isChecked = getBoolean("useCellphone")
                cellphoneInp.setText(getString("cellphone"))
                sexRd.check(getInt("sexId"))
                educationSp.setSelection(getInt("education"))
                conclusionYearInp.setText(getString("conclusion"))
                institutionInp.setText(getString("institution"))
                monographyInp.setText(getString("monography"))
                supervisorInp.setText(getString("supervisor"))
                interestInp.setText(getString("interests"))
            }
        }
    }
}