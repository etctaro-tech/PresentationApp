package com.example.presentationapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentationapp.ui.theme.Typography

@Composable
fun IntroductionPage() {
    var showMessage by remember { mutableStateOf(false) }
    Column(modifier = Modifier) {
        Text(text = "自己紹介", style = Typography.headlineLarge)
        Text(text = "・etctaro", style = Typography.headlineMedium)
        Text(text = "・某社モバイルエンジニア", style = Typography.headlineMedium)
        Text(text = "・主にAndroidのアプリ開発支援、社内研修講師など", style = Typography.headlineMedium)
        Text(text = "・勉強会開催、テスト自動化関連のオーガナイザーなど", style = Typography.headlineMedium)
        Row {
            Image(
                painter = painterResource(id = R.drawable.leopa), contentDescription = "うちのレオパです",
                modifier = Modifier
                    .clickable { showMessage = !showMessage }
            )
            if (showMessage) Text("＜ よろしくー")
        }
    }
}


@Preview
@Composable
fun IntroductionPrev() {
    IntroductionPage()
}