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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
val SliderActiveColor = Color(0xFF5C6B9C)
val SliderInactiveColor = Color(0xFFE8EAF6)

fun getDiscountPercent(dishCount: Int): Int {
    return when {
        dishCount in 1..2 -> 3
        dishCount in 3..5 -> 5
        dishCount in 6..10 -> 7
        dishCount > 10 -> 10
        else -> 0
    }
}

@Composable
fun TipCalculatorScreen(modifier: Modifier = Modifier) {
    var orderAmountText by remember { mutableStateOf("") }
    var dishCountText by remember { mutableStateOf("") }
    var tipPercent by remember { mutableFloatStateOf(10f) }

    val dishCount = dishCountText.toIntOrNull() ?: 0
    val discountPercent = getDiscountPercent(dishCount)
    val discountOptions = listOf(3, 5, 7, 10)

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

            Spacer(modifier = Modifier.height(24.dp))

            // Чаевые
            Text(
                text = "Чаевые:",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = DarkTextColor
            )

            Spacer(modifier = Modifier.height(8.dp))

            Slider(
                value = tipPercent,
                onValueChange = { tipPercent = it },
                valueRange = 0f..25f,
                colors = SliderDefaults.colors(
                    activeTrackColor = SliderActiveColor,
                    inactiveTrackColor = SliderInactiveColor,
                    thumbColor = SliderActiveColor
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "0",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkTextColor
                )
                Text(
                    text = "25",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkTextColor
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Скидка (RadioGroup)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Скидка:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkTextColor
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    discountOptions.forEach { option ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            RadioButton(
                                selected = (discountPercent == option),
                                onClick = null,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = SliderActiveColor,
                                    unselectedColor = Color.Gray
                                )
                            )
                            Text(
                                text = "$option%",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkTextColor
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Расчет итоговой суммы без излишеств
            val orderAmount = orderAmountText.toDoubleOrNull() ?: 0.0
            val discountAmount = orderAmount * (discountPercent / 100.0)
            val amountAfterDiscount = (orderAmount - discountAmount).coerceAtLeast(0.0)
            val tipAmount = amountAfterDiscount * (tipPercent / 100.0)
            val totalAmount = amountAfterDiscount + tipAmount

            Text(
                text = "Итого к оплате: %.2f ₽".format(totalAmount),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkTextColor
            )
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