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
import androidx.compose.material3.RadioButton
import androidx.compose.ui.semantics.SemanticsProperties.Selected

class ModifyPage : ComponentActivity(){
    private lateinit var mortgageCalcViewModel: MortgageCalcViewModel
    private lateinit var yearsRadioGroup: RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        mortgageCalcViewModel = ViewModelProvider(this).get(MortgageCalcViewModel::class.java)
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

        yearsRadioGroup.setOnCheckedChangeListener{group, checkedID ->
            if (checkedID == tenYears.id){
                mortgageCalcViewModel.setYears(10)
            }
            if(checkedID == fifteenYears.id){
                mortgageCalcViewModel.setYears(15)
            }
            if(checkedID == thirtyYears.id){
                mortgageCalcViewModel.setYears(30)
            }
        }

        setContent (){
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
                        yearsRadioGroup
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
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    //     horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    GridMortgageDetail(
                        detail = "Interest\nRate",
                    )
                }
            }
        }
    }

    @Composable
    fun YearRadioButton(
        year: int,
        selectedYear: String,
        onYearSelected: (String) -> Unit
    ){
        val isSelected = year == selectedYear
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            RadioButton(selected = isSelected, onClick = { mortgageCalcViewModel.setYears(Selected) })
        }

    }



    @Composable
    fun GridMortgageDetail(detail: String, modifier: Modifier = Modifier){
        Column (){
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(40.dp)
                    .background(Color.White)
                    .padding(3.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text(detail, fontSize = 12.sp, color = Color.Black,  textAlign = TextAlign.Start)

            }
        }

    }
}