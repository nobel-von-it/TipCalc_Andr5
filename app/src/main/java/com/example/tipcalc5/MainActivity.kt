package com.example.tipcalc5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalc5.ui.theme.TipCalc5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalc5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculatorScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

val PinkInputBg = Color(0xFFFFB6C1)
val DarkTextColor = Color(0xFF1A1A1A)

@Composable
fun TipCalculatorScreen(modifier: Modifier = Modifier) {
    var orderAmountText by remember { mutableStateOf("") }
    var dishCountText by remember { mutableStateOf("") }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8FC)),
        color = Color(0xFFF8F8FC)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            // Сумма заказа
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Сумма заказа:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = DarkTextColor
                )
                TextField(
                    value = orderAmountText,
                    onValueChange = { orderAmountText = it },
                    modifier = Modifier.width(160.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = PinkInputBg,
                        unfocusedContainerColor = PinkInputBg,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Количество блюд
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Количество блюд:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = DarkTextColor
                )
                TextField(
                    value = dishCountText,
                    onValueChange = { dishCountText = it },
                    modifier = Modifier.width(160.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = PinkInputBg,
                        unfocusedContainerColor = PinkInputBg,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorScreenPreview() {
    TipCalc5Theme {
        TipCalculatorScreen()
    }
}