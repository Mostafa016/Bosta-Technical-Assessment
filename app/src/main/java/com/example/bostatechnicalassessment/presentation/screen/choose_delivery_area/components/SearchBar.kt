package com.example.bostatechnicalassessment.presentation.screen.choose_delivery_area.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.bostatechnicalassessment.R
import com.example.bostatechnicalassessment.presentation.theme.ROUNDED_CORNER_DIMS

@Composable
fun SearchBar(modifier: Modifier = Modifier, text: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        text,
        modifier = modifier,
        shape = RoundedCornerShape(ROUNDED_CORNER_DIMS),
        onValueChange = onValueChange,
        placeholder = { Text(stringResource(R.string.city_area)) },
        trailingIcon = {
            IconButton(onClick = {/*TODO: Add same as IME action*/ }) {
                Icon(Icons.Sharp.Search, stringResource(R.string.search_icon))
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(onSearch = {/*TODO: Add same as pressing search icon*/ }),
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    MaterialTheme {
        SearchBar(text = stringResource(R.string.city_area), onValueChange = {})
    }
}