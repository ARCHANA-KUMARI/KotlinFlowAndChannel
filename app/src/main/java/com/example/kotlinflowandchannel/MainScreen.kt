package com.example.kotlinflowandchannel

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
private const val TAG = "MainScreen"
data class DemoItem(
    val label: String,
    val description: String,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoHubScreen(
    demos: List<DemoItem>,
    onDemoClick: (DemoItem) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Kotlin Flow and Channel") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            demos.forEach { demo ->
                DemoButton(demo = demo, onClick = { onDemoClick(demo) })
            }
        }
    }
}

@Composable
private fun DemoButton(
    demo: DemoItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledTonalButton(
        onClick = {
            Log.i(TAG, "DemoButton: ")
            onClick()
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(demo.label, style = MaterialTheme.typography.titleMedium)
            Text(
                demo.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/* ---------- Sample data (demo only) ---------- */

private val sampleDemos = listOf(

    DemoItem("MutableStateFlow", "Hot state holder: always has a value, conflated, replays latest to collectors", "mutable_state_flow"),
    DemoItem("Lists", "LazyColumn with stable keys", "lists"),
    DemoItem("Forms", "Text fields and validation", "forms"),
    DemoItem("Animations", "animate*AsState and transitions", "animations"),
    DemoItem("Navigation", "NavHost and back stack", "navigation"),
    DemoItem("Theming", "Material 3 dynamic color", "theming")
)

/* ---------- Previews ---------- */

@PreviewLightDark
@Preview(name = "Large font", fontScale = 1.5f, showSystemUi = true)
@Composable
private fun DemoHubScreenPreview() {
    MaterialTheme {
        DemoHubScreen(demos = sampleDemos, onDemoClick = {})
    }
}

@Preview(name = "Few items", showSystemUi = true)
@Composable
private fun DemoHubScreenShortPreview() {
    MaterialTheme {
        DemoHubScreen(demos = sampleDemos.take(2), onDemoClick = {})
    }
}