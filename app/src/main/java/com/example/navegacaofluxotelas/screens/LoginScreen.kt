package com.example.navegacaofluxotelas.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "LOGIN"
        )
    }
}