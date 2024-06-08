package com.example.trialpin

import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat

class SignUp_OTP : AppCompatActivity() {

    private lateinit var otp1: EditText
    private lateinit var otp2: EditText
    private lateinit var otp3: EditText
    private lateinit var otp4: EditText
    private lateinit var otp5: EditText
    private lateinit var otp6: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_otp)

        otp1 = findViewById(R.id.otp1)
        otp2 = findViewById(R.id.otp2)
        otp3 = findViewById(R.id.otp3)
        otp4 = findViewById(R.id.otp4)
        otp5 = findViewById(R.id.otp5)
        otp6 = findViewById(R.id.otp6)

        setupOtpInputs()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val toolbarTitle: TextView = findViewById(R.id.toolbar_title)
        setSupportActionBar(toolbar)

        // Set the title on the toolbar
        supportActionBar?.title = null // Set to null to use custom title view
        toolbarTitle.text = "Verification"

        // Enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Change the color of the back button
        toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, android.R.color.black), PorterDuff.Mode.SRC_ATOP)

        // Handle the back press using OnBackPressedDispatcher
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Navigate back
                finish()
            }
        })
    }

    // Handle the Up button press
    override fun onSupportNavigateUp(): Boolean {
        // Use finish() to handle back navigation
        finish()
        return true
    }
    private fun setupOtpInputs() {
        val otps = arrayOf(otp1, otp2, otp3, otp4, otp5, otp6)

        for (i in otps.indices) {
            otps[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && i < otps.size - 1) {
                        otps[i + 1].requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            otps[i].setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    if (otps[i].text.isEmpty() && i > 0) {
                        otps[i - 1].requestFocus()
                        otps[i - 1].setText("")
                    }
                }
                false
            })
        }
    }
}

