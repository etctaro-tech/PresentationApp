package com.example.presentationapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentationapp.ui.theme.Typography

@Composable
fun SourceCodeViewer(code: String) {

    SelectionContainer {
        Text(
            text = code,
            style = Typography.bodyMedium,
            modifier = Modifier
                .background(color = if (isSystemInDarkTheme()) Color.Black else Color.White)
                .verticalScroll(rememberScrollState())
                .border(
                    width = 2.dp,
                    color = if (!isSystemInDarkTheme()) Color.Black else Color.White
                )
                .padding(3.dp)
        )
    }
}

@Composable
fun ShowCodeButton(showSource: Boolean, onChecked: (Boolean) -> Unit) {
    IconToggleButton(checked = showSource, onCheckedChange = onChecked) {
        if (showSource) {
            Icon(Icons.Filled.Favorite, contentDescription = "show Source")
        } else {
            Icon(Icons.Outlined.FavoriteBorder, contentDescription = "hide Source")
        }

    }
}


@Preview
@Composable
fun SourceCodePreview() {
    SourceCodeViewer(
        code = """
       Text("Hello, Compose") 
    """
    )
}

@Preview
@Composable
fun ShowCodePreview() {
    var show by remember { mutableStateOf(false) }
    Row {
        ShowCodeButton(showSource = show, onChecked = { show = it })
        if (show) {
            SourceCodeViewer(
                code = """
                   Text("Hello, Compose") 
                 """
            )
        }
    }
}