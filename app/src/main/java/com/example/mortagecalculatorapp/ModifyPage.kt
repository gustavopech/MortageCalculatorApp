@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mortagecalculatorapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import android.widget.RadioGroup
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TextField

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsProperties.Selected

class ModifyPage : ComponentActivity() {
   // private lateinit var mortgageCalcViewModel: MortgageCalcViewModel
    private lateinit var yearsRadioGroup: RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        val mortgageCalcViewModel: MortgageCalcViewModel by viewModels()
        val amount = intent.getStringExtra("amount")
        val years = intent.getStringExtra("years")
        val rate = intent.getStringExtra("rate")

        if (years != null) {
            mortgageCalcViewModel.setYears(years.toInt())
        }
        if (rate != null) {
            mortgageCalcViewModel.setRate(rate.toFloat())
        }
        if (amount != null) {
            mortgageCalcViewModel.setAmount(amount.toFloat())
        }

        val intent = Intent(this, MainActivity::class.java)
        super.onCreate(savedInstanceState)

        yearsRadioGroup = RadioGroup(this)
        yearsRadioGroup.orientation = RadioGroup.HORIZONTAL

        //radio toggle options
        val tenYears = RadioButton(this)
        tenYears.text = "10"
        yearsRadioGroup.addView(tenYears)

        val fifteenYears = RadioButton(this)
        fifteenYears.text = "15"
        yearsRadioGroup.addView(fifteenYears)

        val thirtyYears = RadioButton(this)
        thirtyYears.text = "30"
        yearsRadioGroup.addView(thirtyYears)

        yearsRadioGroup.setOnCheckedChangeListener { group, checkedID ->
            if (checkedID == tenYears.id) {
                mortgageCalcViewModel.setYears(10)
            }
            if (checkedID == fifteenYears.id) {
                mortgageCalcViewModel.setYears(15)
            }
            if (checkedID == thirtyYears.id) {
                mortgageCalcViewModel.setYears(30)
            }
        }

        setContent() {
            var selectedYear by remember {
                mutableStateOf(mortgageCalcViewModel.getYears().toString())
            }
            var amount by remember {
                mutableStateOf(mortgageCalcViewModel.getAmount().toString())
            }
            var interest_rate by remember {
                mutableStateOf(mortgageCalcViewModel.getRate().toString())
            }

            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(2.dp)
                    .fillMaxSize(),
                // horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Mortgage V0 Edit",
                    modifier = Modifier
                        .background(Color.Blue)
                )

                /*
            Details
             */

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    // horizontalArrangement = Arrangement.
                ) {
                    GridMortgageDetail(
                        detail = "Years",
                    )
                    Column {

                        YearRadioButton(
                            year = "10",
                            selectedYear = selectedYear,
                            onYearSelected = { selectedYear = it })


                        YearRadioButton(
                            year = "15",
                            selectedYear = selectedYear,
                            onYearSelected = { selectedYear = it })


                        YearRadioButton(
                            year = "30",
                            selectedYear = selectedYear,
                            onYearSelected = { selectedYear = it })

                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    // horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    GridMortgageDetail(
                        detail = "Amount",
                    )
                    TextField(
                        value = amount,
                        onValueChange = {
                            newAmount -> amount = newAmount
                        }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    //     horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    GridMortgageDetail(
                        detail = "Interest\nRate",
                    )
                    TextField(
                        value = interest_rate,
                        onValueChange = { newRate ->
                            interest_rate = newRate
                        }
                    )
                }
                    Row() {
                        Button(



                            onClick = {
                                mortgageCalcViewModel.setAmount(amount.toFloat())
                                mortgageCalcViewModel.setRate(interest_rate.toFloat())
                                mortgageCalcViewModel.setYears(selectedYear.toInt())

                                intent.putExtra(
                                    "years",
                                    mortgageCalcViewModel.getYears().toString()
                                )
                                intent.putExtra(
                                    "amount",
                                    mortgageCalcViewModel.getAmount().toString()
                                )
                                intent.putExtra("rate", mortgageCalcViewModel.getRate().toString())

                                startActivity(intent)
                            }
                        ) {
                            Text("Save")
                        }
                    }

            }
        }
    }

    @Composable
    fun YearRadioButton(
        year: String,
        selectedYear: String,
        onYearSelected: (String) -> Unit
    ) {
        val isSelected =  selectedYear == year
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = {
                    onYearSelected(year)
                }
            )
            Text(text = year)
        }

    }


    @Composable
    fun GridMortgageDetail(detail: String, modifier: Modifier = Modifier) {
        Column() {
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(40.dp)
                    .background(Color.White)
                    .padding(3.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text(detail, fontSize = 12.sp, color = Color.Black, textAlign = TextAlign.Start)

            }
        }

    }
}