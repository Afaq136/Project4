package com.example.project4

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var baseAmountEditText: EditText
    private lateinit var percentageSlider: SeekBar
    private lateinit var percentageTextView: TextView
    private lateinit var tipAmountTextView: TextView
    private lateinit var totalAmountTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        baseAmountEditText = findViewById(R.id.baseAmountEditText)
        percentageSlider = findViewById(R.id.percentageSlider)
        percentageTextView = findViewById(R.id.percentageTextView)
        tipAmountTextView = findViewById(R.id.tipAmountTextView)
        totalAmountTextView = findViewById(R.id.totalAmountTextView)

        setupListeners()
    }

    private fun setupListeners() {
        baseAmountEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateAmounts()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        percentageSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateAmounts()
                updatePercentageTextView(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun updateAmounts() {
        val baseAmount = baseAmountEditText.text.toString().toDoubleOrNull() ?: 0.0
        val percentage = percentageSlider.progress.toDouble()
        val tipAmount = (percentage / 100) * baseAmount
        val totalAmount = baseAmount + tipAmount

        tipAmountTextView.text = String.format("Tip: $%.2f", tipAmount)
        totalAmountTextView.text = String.format("Total: $%.2f", totalAmount)
    }

    private fun updatePercentageTextView(progress: Int) {
        percentageTextView.text = "$progress%"
    }
}
