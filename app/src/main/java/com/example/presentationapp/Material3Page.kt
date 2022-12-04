package com.example.presentationapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentationapp.ui.theme.Typography

@Composable
fun Material3Page() {
    Column {
        Text(text = "Material3に正式対応(1.0 Stable)", style = Typography.headlineLarge)

        Row() {
            Column(modifier = Modifier.padding(5.dp)) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "M3")
                }
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Image(Icons.Filled.Lock, contentDescription = "")
                }
            }
            Column(modifier = Modifier.padding(5.dp)) {
                androidx.compose.material.Button(onClick = { /*TODO*/ }) {
                    Text(text = "M2")
                }
                androidx.compose.material.FloatingActionButton(onClick = { /*TODO*/ }) {
                    Image(Icons.Filled.Lock, contentDescription = "")
                }
            }
            Column(modifier = Modifier.padding(3.dp)) {
                Text(text = "・Material Designの最新バージョン", style = Typography.headlineMedium)
                Text(
                    text = "・Android12のMaterialYouに対応。背景の色に合わせてカラーデザインを指定可能",
                    style = Typography.headlineMedium
                )
                Text(
                    text = "・Jetpack Composeではmaterial3のライブラリを導入することで利用可能",
                    style = Typography.headlineMedium
                )
                Text(
                    text = """
                    implementation 'androidx.compose.material3:material3'
                """.trimIndent(),
                    modifier = Modifier
                        .border(width = 2.dp, color = Color.Black)
                        .padding(3.dp)
                )
                Text(text = "・Flamingo以降では標準となる可能性が高い", style = Typography.headlineMedium)
            }
        }
    }

}

@Preview
@Composable
fun Material3PagePrev() {
    Material3Page()
}