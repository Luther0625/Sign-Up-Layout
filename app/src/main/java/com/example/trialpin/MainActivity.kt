package com.example.trialpin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var editTextPhone: EditText
    private lateinit var pin1: EditText
    private lateinit var pin2: EditText
    private lateinit var pin3: EditText
    private lateinit var pin4: EditText
    private lateinit var repin1: EditText
    private lateinit var repin2: EditText
    private lateinit var repin3: EditText
    private lateinit var repin4: EditText
    private lateinit var tAndP: CheckBox

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextPhone = findViewById(R.id.editTextPhone)
        pin1 = findViewById(R.id.pin1)
        pin2 = findViewById(R.id.pin2)
        pin3 = findViewById(R.id.pin3)
        pin4 = findViewById(R.id.pin4)
        repin1 = findViewById(R.id.repin1)
        repin2 = findViewById(R.id.repin2)
        repin3 = findViewById(R.id.repin3)
        repin4 = findViewById(R.id.repin4)
        tAndP = findViewById(R.id.tAndP)
        val submitButton: Button = findViewById(R.id.signUp)

        setupPinInputs(pin1, pin2, pin3, pin4)
        setupPinInputs(repin1, repin2, repin3, repin4)

        submitButton.setOnClickListener {
            val phone = editTextPhone.text.toString().trim()
            val pin = pin1.text.toString() + pin2.text.toString() + pin3.text.toString() + pin4.text.toString()
            val repin = repin1.text.toString() + repin2.text.toString() + repin3.text.toString() + repin4.text.toString()

            when {
                phone.isEmpty() -> {
                    Toast.makeText(this@MainActivity, "Please enter your phone number", Toast.LENGTH_SHORT).show()
                }
                pin.length != 4 -> {
                    Toast.makeText(this@MainActivity, "Please enter your 4-digit PIN", Toast.LENGTH_SHORT).show()
                }
                repin.length != 4 -> {
                    Toast.makeText(this@MainActivity, "Please re-enter your 4-digit PIN", Toast.LENGTH_SHORT).show()
                }
                pin != repin -> {
                    Toast.makeText(this@MainActivity, "PINs do not match", Toast.LENGTH_SHORT).show()
                }
                !tAndP.isChecked -> {
                    Toast.makeText(this@MainActivity, "Please agree to the terms and conditions", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this@MainActivity, "PIN matched", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SignUp_OTP::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun setupPinInputs(vararg editTexts: EditText) {
        for (i in editTexts.indices) {
            val currentEditText = editTexts[i]
            currentEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        currentEditText.setBackgroundResource(R.drawable.edit_text_underline_filled)
                        if (i < editTexts.size - 1) {
                            editTexts[i + 1].requestFocus()
                        }
                    } else if (s?.isEmpty() == true) {
                        currentEditText.setBackgroundResource(R.drawable.edit_text_underline_empty)
                        if (i > 0) {
                            editTexts[i - 1].requestFocus()
                        }
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }
}
