package com.example.hw2simplearithmetic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.hw2simplearithmetic.ui.theme.HW2SimpleArithmeticTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Arithmetic()
        }
    }
}

@Composable
fun Arithmetic() {

    val (input1, setInput1) = remember { mutableStateOf("") }
    val (input2, setInput2) = remember { mutableStateOf("") }
    val (result, setResult) = remember { mutableStateOf(0f) }

    val onInput1 = { value: String ->
        setInput1(value)
    }

    val onInput2 = { value: String ->
        setInput2(value)
    }

    val calculateResult = {
        val num1 = input1.toFloatOrNull() ?: 0f
        val num2 = input2.toFloatOrNull() ?: 0f
        setResult(num1 + num2) // example, this only works for addition
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = input1,
            onValueChange = onInput1,
            label = { Text("Input 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = input2,
            onValueChange = onInput2,
            label = { Text("Input 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = calculateResult) {
            Text("Calculate")
        }
        Text("Result: $result")
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {

}