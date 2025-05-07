package com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.bostatechnicalassessment.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onClose: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = { AppBarTitle(Modifier.fillMaxWidth(), text = title) },
        actions = {
            ExitSearchButton(onClick = onClose)
        },
    )
}

@Composable
private fun AppBarTitle(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
private fun ExitSearchButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    var isEnabled by remember { mutableStateOf(true) }
    IconButton(
        modifier = modifier,
        onClick = {
            isEnabled = false
            onClick()
        },
        enabled = isEnabled
    ) {
        Icon(Icons.Sharp.Close, stringResource(R.string.exit_choose_the_delivery_area))
    }
}

//region Previews
@Preview(showBackground = true)
@Composable
fun AppTopAppBarPreview() {
    MaterialTheme {
        AppTopAppBar(
            title = "Sample Title",
            onClose = {  }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppBarTitlePreview() {
    MaterialTheme {
        AppBarTitle(text = "Sample AppBar Title")
    }
}

@Preview(showBackground = true)
@Composable
fun ExitSearchButtonPreview() {
    MaterialTheme {
        ExitSearchButton(onClick = {  })
    }
}
//endregion