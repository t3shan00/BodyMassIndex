package com.example.bodymassindex

import java.text.DecimalFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bodymassindex.ui.theme.BodyMassIndexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BodyMassIndexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Bmi(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Bmi(modifier: Modifier = Modifier){
    var heightInput : String by remember { mutableStateOf("") }
    var weightInput: String by remember { mutableStateOf("") }
    val height = heightInput.toFloatOrNull() ?: 0.0f
    val weight = weightInput.toFloatOrNull() ?: 0.0f
    val formatter = DecimalFormat("0.00")
    val bmi = if (weight > 0 && height > 0) formatter.format(weight / (height * height)) else "0.00"
    Column (modifier = modifier.padding(16.dp)) {
        Text (
            text = stringResource(R.string.body_mass_index),
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        OutlinedTextField(
            value = heightInput,
            onValueChange = { heightInput = it.replace(',','.')},
            label = {Text(stringResource(R.string.height_meters))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
        )
        OutlinedTextField(
            value = weightInput,
            onValueChange = { weightInput = it.replace(',','.')},
            label = {Text(stringResource(R.string.weight_kg))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
        )
        Text(
            text = "${stringResource(R.string.body_mass_index_is)} $bmi",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BmiPreview() {
    BodyMassIndexTheme{
        Bmi()
    }
}