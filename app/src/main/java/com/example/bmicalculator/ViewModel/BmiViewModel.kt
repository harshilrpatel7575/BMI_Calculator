package com.example.bmicalculator.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BmiViewModel: ViewModel() {
    private val _bmiResult = MutableLiveData<String>()
    val bmiResult: LiveData<String> get() = _bmiResult

    private val _bmiRange = MutableLiveData<String>()
    val bmiRange: LiveData<String> get() = _bmiRange

    fun calculateBMI(weight: Double, height: Double) {
        val bmi = weight / ((height / 100) * (height / 100))
        _bmiResult.value = String.format("Your BMI: %.2f", bmi)
        _bmiRange.value = getBmiRange(bmi)
    }

    private fun getBmiRange(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 24.9 -> "Normal weight"
            bmi < 29.9 -> "Overweight"
            else -> "Overweight and Healthy"
        }
    }
}