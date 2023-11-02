package com.example.mortagecalculatorapp
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat
class Mortgage : ViewModel(){
    val MONEY: DecimalFormat = DecimalFormat("$#,##0.00")
    private var amount = 0f
    private var years = 0
    private var rate = 0f
    fun Mortgage() {
        setAmount(100000.0f)
        setYears(30)
        setRate(0.035f)
    }

    fun setAmount(newAmount: Float) {
        if (newAmount >= 0) amount = newAmount
    }

    fun setYears(newYears: Int) {
        if (newYears >= 0) years = newYears
    }

    fun setRate(newRate: Float) {
        if (newRate >= 0) rate = newRate
    }

    fun getAmount(): Float {
        return amount
    }

    fun getFormattedAmount(): String? {
        return MONEY.format(amount)
    }

    fun getYears(): Int {
        return years
    }

    fun getRate(): Float {
        return rate
    }

    fun monthlyPayment(): Float {
        val mRate = rate / 12 // monthly interest rate
        val temp = Math.pow(
            (1 / (1 + mRate)).toDouble(), (years *
                    12).toDouble()
        )
        return amount * mRate / (1 - temp).toFloat()
        3
    }

    fun formattedMonthlyPayment(): String? {
        return MONEY.format(monthlyPayment())
    }

    fun totalPayment(): Float {
        return monthlyPayment() * years * 12
    }

    fun formattedTotalPayment(): String? {
        return MONEY.format(totalPayment())
    }
}

