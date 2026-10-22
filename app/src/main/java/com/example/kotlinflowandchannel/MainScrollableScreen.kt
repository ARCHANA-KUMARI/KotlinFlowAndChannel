package com.example.kotlinflowandchannel


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp

@Composable
fun ScrollableColumnScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Samples", style = MaterialTheme.typography.headlineMedium)

        repeat(30) { index ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Item #$index",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@PreviewLightDark
@Preview(name = "Large font", fontScale = 1.5f, showBackground = true)
@Composable
private fun ScrollableColumnScreenPreview() {
    MaterialTheme {
        Surface {
            ScrollableColumnScreen()
        }
    }
}