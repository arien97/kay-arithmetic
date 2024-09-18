package com.example.hw2simplearithmetic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp


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
    val (result, setResult) = remember { mutableStateOf("") }
    val (selectedOperation, setSelectedOperation) = remember { mutableStateOf("+") }

    val onInput1 = { value: String ->
        setInput1(value)
    }

    val onInput2 = { value: String ->
        setInput2(value)
    }

    val calculateResult = {
        val num1 = input1.toFloatOrNull()
        val num2 = input2.toFloatOrNull()

        if (num1 == null || num2 == null) {
            setResult("Please enter valid numbers.")
        } else {
            when (selectedOperation) {
                "+" -> setResult("Result: ${num1 + num2}")
                "-" -> setResult("Result: ${num1 - num2}")
                "*" -> setResult("Result: ${num1 * num2}")
                "/" -> {
                    if (num2 == 0f) {
                        setResult("Cannot divide by zero! The universe implodes.")
                    } else {
                        setResult("Result: ${num1 / num2}")
                    }
                }
                "%" -> {
                    if (num2 == 0f) {
                        setResult("Modulus by zero is undefined. Time stops.")
                    } else {
                        setResult("Result: ${num1 % num2}")
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Display the result at the top
        Text(
            text = result,
            style = TextStyle(fontSize = 30.sp), // Set font size to 30sp
            modifier = Modifier.padding(bottom = 16.dp) // Add bottom margin of 16dp
        )

        // Input fields
        TextField(
            value = input1,
            onValueChange = onInput1,
            label = { Text("Left Operand") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = input2,
            onValueChange = onInput2,
            label = { Text("Right Operand") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        // Operation selection
        Text("Select Operation:")
        Row {
            Button(onClick = { setSelectedOperation("+") }) {
                Text("+")
            }
            Button(onClick = { setSelectedOperation("-") }) {
                Text("-")
            }
            Button(onClick = { setSelectedOperation("*") }) {
                Text("*")
            }
            Button(onClick = { setSelectedOperation("/") }) {
                Text("/")
            }
            Button(onClick = { setSelectedOperation("%") }) {
                Text("%")
            }
        }
        // Calculate button
        Button(onClick = calculateResult) {
            Text("Calculate")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Arithmetic()
}