package com.example.presentationapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentationapp.ui.theme.Typography


/*
    - この辺りのサイトを紹介？
        - What’s new in Jetpack Compose
            - https://android-developers.googleblog.com/2022/10/whats-new-in-jetpack-compose.html
        など

 */

@Composable
fun ReferencePage() {
    Column(modifier = Modifier) {
        val clipboardManager: ClipboardManager = LocalClipboardManager.current

        Text(text = "参考資料", style = Typography.headlineLarge)
        Text(text = "・公式サイト", style = Typography.headlineMedium, modifier = Modifier.clickable {
            clipboardManager.setText(
                AnnotatedString("https://developer.android.com/jetpack/compose?hl=ja")
            )
        })
        Text(
            text = "・What’s new in Jetpack Compose",
            style = Typography.headlineMedium,
            modifier = Modifier.clickable {
                clipboardManager.setText(
                    AnnotatedString("https://android-developers.googleblog.com/2022/10/whats-new-in-jetpack-compose.html")
                )
            })
        Text(
            text = "・Jetpack Compose で Clipboard を利用したコピー＆ペーストを実装する",
            style = Typography.headlineMedium,
            modifier = Modifier.clickable {
                clipboardManager.setText(
                    AnnotatedString("https://zenn.dev/kaleidot725/articles/2021-11-13-jc-clipboard")
                )
            })
        Text(
            text = "・stack overflow: Jetpack Compose saving state on orientation change",
            style = Typography.headlineMedium,
            modifier = Modifier.clickable {
                clipboardManager.setText(
                    AnnotatedString("https://stackoverflow.com/questions/63733944/jetpack-compose-saving-state-on-orientation-change/63746043#63746043")
                )
            })
        Text(
            text = "・Jetpack Compose: BOM",
            style = Typography.headlineMedium,
            modifier = Modifier.clickable {
                clipboardManager.setText(
                    AnnotatedString("https://medium.com/jetpack-composers/jetpack-compose-bom-38e36a43b8cb")
                )
            })
        Text(
            text = "・このアプリのソースコード",
            style = Typography.headlineMedium,
            modifier = Modifier.clickable {
                clipboardManager.setText(
                    AnnotatedString("https://github.com/etctaro-tech/PresentationApp")
                )
            })
    }
}

@Preview
@Composable
fun ReferencePagePrev() {
    ReferencePage()
}