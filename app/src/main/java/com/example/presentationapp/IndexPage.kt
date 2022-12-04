package com.example.presentationapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentationapp.ui.theme.Typography
import com.google.accompanist.web.WebView

@Composable
fun IndexPage() {
    Column(modifier = Modifier) {
        Text(text = "今回の内容", style = Typography.headlineLarge)
        Text(text = "・Jetpack Composeはもう流行している", style = Typography.headlineMedium)
        Text(text = "・Jetpack Compose最新情報", style = Typography.headlineMedium)
        Text(text = "・Compose Material3", style = Typography.headlineMedium)
        Text(text = "・Compose 1.2-1.3付近の話", style = Typography.headlineMedium)
    }
}


@Preview
@Composable
fun IndexPagePrev() {
    IndexPage()
}