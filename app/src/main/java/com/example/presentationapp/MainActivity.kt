package com.example.presentationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentationapp.ui.theme.PresentationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PresentationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PresentationsNavHost()
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Start : Screen("start")
    object Index : Screen("index")
    object Introduction : Screen("introduction")
    object AboutCompose : Screen("aboutCompose")
    object ComposeTrend : Screen("composeTrend")
    object Material3 : Screen("material3")
    object ComposeLatest : Screen("composeLatest")
    object Conclusion : Screen("conclusion")
    object Reference : Screen("reference")
    object End : Screen("end")
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresentationsNavHost() {

    // このリストの順にページを表示するイメージ
    val items = listOf(
        Screen.Start,
        Screen.Index,
        Screen.Introduction,
        Screen.AboutCompose,
        Screen.ComposeTrend,
        Screen.Material3,
        Screen.ComposeLatest,
        Screen.Conclusion,
        Screen.Reference,
        Screen.End
    )
    // 現在のページを保持するState。Saveableにすることでバックグラウンドになった際もStateが保持される。
    // これがないと、ホーム画面の背景を変更した時に状態がクリアされる。
    // (逆に、これさえやっておけばとりあえず大丈夫)
    var currentPage by rememberSaveable { mutableStateOf(0) }
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar() {
                IconButton(
                    onClick = {
                        currentPage--
                        navController.navigate(items[currentPage].route)
                    }, enabled = currentPage > 0
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "back page")
                }
                Text("${currentPage + 1}")
                IconButton(
                    onClick = {
                        currentPage++
                        navController.navigate(items[currentPage].route)
                    }, enabled = currentPage < items.size - 1
                ) {
                    Icon(Icons.Filled.ArrowForward, contentDescription = "back page")
                }
            }
        }
    ) {
        NavHost(navController, startDestination = items[0].route, Modifier.padding(it)) {
            composable(Screen.Start.route) { StartPage() }
            composable(Screen.Introduction.route) { IntroductionPage() }
            composable(Screen.Index.route) { IndexPage() }
            composable(Screen.AboutCompose.route) { AboutComposePage() }
            composable(Screen.ComposeTrend.route) { ComposeTrendPage() }
            composable(Screen.Material3.route) { Material3Page() }
            composable(Screen.ComposeLatest.route) { ComposeLatestPage() }
            composable(Screen.Conclusion.route) { ConclusionPage() }
            composable(Screen.Reference.route) { ReferencePage() }
            composable(Screen.End.route) { EndPage() }
        }
    }

}

