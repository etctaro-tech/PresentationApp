package com.example.presentationapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentationapp.ui.theme.PresentationAppTheme
import com.example.presentationapp.ui.theme.Typography

@Composable
fun StartPage() {
    var showSource by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                text = "ざっくり最新Jetpack Compose 2022秋",
                style = Typography.headlineLarge
            )
            Text(
                text = "etctaro",
                style = Typography.headlineSmall
            )
        }
        Row(modifier = Modifier) {
            ShowCodeButton(showSource, { showSource = it })
            if (showSource) {

                SourceCodeViewer(
                    """
                     fun StartPage() {
                         var showSource by remember { mutableStateOf(false)}
                         Box(modifier = Modifier.fillMaxSize()) {
                            Column(modifier = Modifier.align(Alignment.Center)) {
                                Text(
                                    text = "ざっくり最新Jetpack Compose 2022秋",
                                    style = Typography.headlineLarge
                                )
                                Text(text = "etctaro",
                                    style = Typography.headlineSmall
                                    )
                            }
                         }
                     }
                 """
                )
            }
        }
    }
}

@Preview
@Composable
fun StartPagePrev() {
    PresentationAppTheme() {
        StartPage()
    }
}