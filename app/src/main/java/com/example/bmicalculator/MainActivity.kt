package com.example.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bmicalculator.ViewModel.BmiViewModel
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: BmiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(BmiViewModel::class.java)

        binding.buttonCalculate.setOnClickListener {
            calculateBMI()
        }

        viewModel.bmiResult.observe(this, Observer {
            binding.textViewResult.text = it
        })

        viewModel.bmiRange.observe(this, Observer {
            binding.textViewResult.append("\n$it")
        })
    }
    private fun calculateBMI() {
        val weight = binding.editTextWeight.text.toString().toDoubleOrNull()
        val height = binding.editTextHeight.text.toString().toDoubleOrNull()

        if (weight != null && height != null) {
            viewModel.calculateBMI(weight, height)
        } else {
            binding.textViewResult.text = "Invalid input. Please enter valid weight and height."
        }
    }
}