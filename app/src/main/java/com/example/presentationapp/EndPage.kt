package com.example.presentationapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.presentationapp.ui.theme.Typography

@Composable
fun EndPage() {
    var showSource by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Happy Composing!",
            modifier = Modifier.align(Alignment.Center),
            style = Typography.headlineLarge
        )
        Row {
            ShowCodeButton(showSource, { showSource = it })
            if (showSource) {
                SourceCodeViewer(
                    """
                    fun StartPage() {
                        var showSource by remember { mutableStateOf(false)}
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(text = "Happy Composing!",
                                modifier = Modifier.align(Alignment.Center),
                                style = Typography.headlineLarge
                            )
                        }
                    }
                """
                )
            }
        }
    }
}