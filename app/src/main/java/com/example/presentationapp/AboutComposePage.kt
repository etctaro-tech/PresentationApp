package com.example.presentationapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.presentationapp.ui.theme.Typography

/*
    こんな感じの内容を発表予定
    - Composeとは
    - こんな感じのソースコードから、UIが作れるんだよ、みたいなことを説明
    - 誰かにこのUIの実装どうなっているの？と説明しやすい開発手法
 */

@Composable
fun AboutComposePage() {
    var showSource by remember { mutableStateOf(false) }
    var showMessage by remember { mutableStateOf(false) }

    Row(modifier = Modifier) {
        var textState by remember { mutableStateOf("") }
        Column {
            Text(text = "Jetpack Composeについて", style = Typography.headlineLarge)
            Button(onClick = { showMessage = true }) {
                Text(text = "Sample")
            }
            TextField(value = textState,
                onValueChange = { textState = it },
                label = { Text(text = "Sample") })
        }
        Column {
            Row(modifier = Modifier) {
                ShowCodeButton(showSource, { showSource = it })
                if (showSource) {
                    SourceCodeViewer(
                        """@Composable
fun AboutComposePage() {
    var textState by remember { mutableStateOf("") }
    var showMessage by remember { mutableStateOf(false) }
    Column {
        Text(text = "Jetpack Composeについて", style = Typography.headlineLarge)
        Button(onClick = { showMessage = true }) {
            Text(text = "Sample")
        }
        TextField(value = textState,
            onValueChange = { textState = it },
            label = { Text(text = "Sample") })
    }
}"""
                    )
                }
            }
            if (showMessage) {
                Text(text = "UIの実装を説明・共有するのが容易な実装方法", style = Typography.headlineSmall)
            }
        }
    }
}