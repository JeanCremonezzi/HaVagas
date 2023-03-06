package br.edu.ifsp.scl.ads.havagas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import br.edu.ifsp.scl.ads.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            cleanBtn.setOnClickListener { view ->
                fullNameInp.setText("")
                emailInp.setText("")
                emailChk.isChecked = false
                phoneInp.setText("")
                phoneRd.check(phoneResidencialRd.id)
                phoneSw.isChecked = false
                sexRd.check(sexMaleRd.id)
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

            ArrayAdapter.createFromResource(
                this@MainActivity,
                R.array.education_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                educationSp.adapter = adapter
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

                    conclusionYearInp.setText("")
                    institutionInp.setText("")
                    monographyInp.setText("")
                    supervisorInp.setText("")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }
}