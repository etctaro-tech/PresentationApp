package com.example.presentationapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentationapp.ui.theme.Typography
import com.google.accompanist.web.WebContent
import com.google.accompanist.web.WebView
import com.google.accompanist.web.WebViewState
import com.google.accompanist.web.rememberWebViewState

/*
    こんな感じの内容を発表予定
    - Jetpack Composeの流行について記載
        - Android Dev SummitやDroidKaigiなどカンファレンスではComposeの話が大部分になっている
        - →というのを実際のWebサイトを見せながら伝える。
        - 余裕があれば、Top1000のアプリのうちどれくらいがComposeに、とかそんな話をしても良いかも。

 */

@Composable
fun ComposeTrendPage() {
    val webViewState =
        rememberWebViewState("https://developer.android.com/events/dev-summit/technical-talks#modern-android-development")
    var checked by remember { mutableStateOf(false) }

    Column(modifier = Modifier) {
        Row {
            Text(text = "最近の傾向", style = Typography.headlineLarge)
            FilledIconToggleButton(checked = checked, onCheckedChange = { checked = it }) {
                if (checked) {
                    Icon(Icons.Filled.Lock, contentDescription = "Localized description")
                } else {
                    Icon(Icons.Outlined.Lock, contentDescription = "Localized description")
                }
            }
        }

        Box() {
            if (!checked) {
                Column {
                    Row {
                        Button(onClick = {
                            webViewState.content =
                                WebContent.Url("https://developer.android.com/events/dev-summit/technical-talks#modern-android-development")
                        }) {
                            Text("Android Dev Summit")
                        }
                        Button(onClick = {
                            webViewState.content =
                                WebContent.Url("https://droidkaigi.jp/2022/timetable")
                        }) {
                            Text("DroidKaigi")
                        }
                    }
                    WebView(webViewState)
                }
            } else {
                Column {
                    Text(
                        text = "・Jetpack Composeのセッションがかなり増えている",
                        style = Typography.headlineMedium
                    )
                    Text(
                        text = "・移行をどうするか？というテーマが盛んに議論されている",
                        style = Typography.headlineMedium
                    )
                    Text(
                        text = "  例）部分的？全面移行？新規の部分だけ？移行しない？",
                        style = Typography.headlineSmall
                    )
                    Text(
                        text = "・Android StudioがJetpack Compose前提になりつつある",
                        style = Typography.headlineMedium
                    )
                    Text(
                        text = "・Androidアプリ開発者にとって無関係ではいられない",
                        style = Typography.headlineMedium
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun ComposeTrendPrev() {
    ComposeTrendPage()
}