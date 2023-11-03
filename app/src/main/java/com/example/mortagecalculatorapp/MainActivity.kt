package com.example.mortagecalculatorapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.mortagecalculatorapp.ui.theme.MortageCalculatorAppTheme


class MainActivity : ComponentActivity() {
    //private lateinit var mortgageCalcViewModel;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //mortgageCalcViewModel = ViewModelProvider(this).get(MortgageCalcViewModel::class.java)
       val mortgageCalcViewModel: MortgageCalcViewModel by viewModels()

        //mortgageCalcViewModel.Mortgage()
        val amount = intent.getStringExtra("amount")
        val years = intent.getStringExtra("years")
        val rate = intent.getStringExtra("rate")

        if (years != null) {
            mortgageCalcViewModel.setYears(years.toInt())
        }
        else{
            mortgageCalcViewModel.setYears(30)
        }
        if (rate != null) {
            mortgageCalcViewModel.setRate(rate.toFloat())
        }
        else{
            mortgageCalcViewModel.setRate(0.035f)
        }
        if (amount != null) {
            mortgageCalcViewModel.setAmount(amount.toFloat())
        }
        else{
            mortgageCalcViewModel.setAmount(100000.0f)
        }
        val intent = Intent(this, ModifyPage::class.java)
        setContent {
            /*
            Heading of First Page
             */
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(2.dp)
                    .fillMaxSize(),
               // horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Mortgage V0",
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
                       detail = "Amount",
                       value = mortgageCalcViewModel.getAmount().toString()
                   )
               }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                   // horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    GridMortgageDetail(
                        detail = "Years",
                        value = mortgageCalcViewModel.getYears().toString()
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
               //     horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    GridMortgageDetail(
                        detail = "Interest\nRate",
                        value = mortgageCalcViewModel.getRate().toString()
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),

                ) {
                    GridMortgageDetail(
                        detail = "Monthly\nPayment",
                        value = mortgageCalcViewModel.formattedMonthlyPayment().toString()
                    )
                }
                Row(
                    modifier = Modifier
                    .fillMaxWidth(),) {
                    GridMortgageDetail(
                        detail = "Total\nPayment",
                        value = mortgageCalcViewModel.formattedTotalPayment().toString()
                    )
                }
                Row()
                {
                    Button(
                        onClick = {
                            intent.putExtra("years", mortgageCalcViewModel.getYears().toString())
                            intent.putExtra("amount", mortgageCalcViewModel.getAmount().toString())
                            intent.putExtra("rate", mortgageCalcViewModel.getRate().toString())

                            startActivity(intent)
                        }
                    ){
                        Text("Modify")
                    }
                }
            }



            
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MortageCalculatorAppTheme {
        Greeting("Android")
    }
}


@Composable
fun GridMortgageDetail(detail: String, value:String, modifier: Modifier = Modifier){
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
    Column {
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(40.dp)
                .background(Color.White)
                .padding(3.dp),
            contentAlignment = Alignment.Center
        )
        {
            Text(value, fontSize = 12.sp, color = Color.Black,  textAlign = TextAlign.End)

        }
    }
}
