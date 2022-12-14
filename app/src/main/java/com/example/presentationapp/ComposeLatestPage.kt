package com.example.presentationapp

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.ExperimentalMaterialApi

import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LookaheadLayout
import androidx.compose.ui.layout.LookaheadLayoutScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentationapp.ui.theme.PresentationAppTheme
import com.example.presentationapp.ui.theme.Typography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

/*
    ???????????????????????????????????????
    - Jetpack Compose 1.2???1.3??????????????????API???????????????????????????
        - LazyGrid/LazyStaggeredGrid (??????)
        - pull-refresh (??????)
        - lookahead Layout
        - Fling Behavor (??????)
        - ...
 */

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ComposeLatestPage() {

    // ???????????????2????????????
    var showGrid by remember {
        mutableStateOf(true)
    }
    Column {
        Row {
            Text(text = "Jetpack Compose 1.2-1.3??????????????????", style = Typography.headlineLarge)
            // ???????????????????????????????????????????????????????????????
            Button(onClick = { showGrid = true })
            {
                Text("Grid + Pull-Refresh")
            }
            Button(onClick = { showGrid = false }) {
                Text("Fling")
            }
        }
        if (showGrid) {
            GridLayout()
        } else {
            Fling()
        }
    }

}

/*
 LazyGrid
 - 0-100???????????????+????????????????????????????????????????????????1????????????????????????????????????
 - Staggered????????????3????????????????????????????????????????????????????????????
 Pull-Refresh
 - Grid???????????????????????????????????????????????????????????????????????????????????????????????????????????????
 - ??????????????????????????????????????????????????????????????????????????????????????????????????????
 */

@Composable
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
private fun GridLayout() {
    // List????????????????????????
    val items = (0..100).toList()

    // pull-refresh??????State
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    var gridColumnCount by remember { mutableStateOf(5) }

    val pullRefreshState = rememberPullRefreshState(refreshing,
        onRefresh = {
            refreshScope.launch {
                refreshing = true
                delay(1000)
                gridColumnCount = Random.nextInt(2, 10)
                refreshing = false
            }
        })

    // Grid/StaggeredGrid???????????????
    var useStaggered by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.pullRefresh(pullRefreshState)) {
        Row {
            Switch(useStaggered, onCheckedChange = { useStaggered = it })
            Text("Staggered")
        }
        Box {
            if (useStaggered) {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(gridColumnCount)
                ) {
                    items(items) {
                        Column(
                            modifier = Modifier
                                .padding(3.dp)
                                .background(Color.Green)
                        ) {
                            if (it % 3 == 0)
                                Image(
                                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                    contentDescription = "$it"
                                )
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription = "$it"
                            )
                            Text("$it")
                        }
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(gridColumnCount)
                ) {
                    items(items) {
                        Column(
                            modifier = Modifier
                                .padding(3.dp)
                                .background(Color.Green)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription = "$it"
                            )
                            Text("$it")
                        }
                    }
                }
            }
            PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(TopCenter))

        }
    }
}

@Preview
@Composable
fun ComposeLatestPagePrev() {
    ComposeLatestPage()
}

/*
 Fling??????????????????????????????
 ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Fling() {
    val state = rememberLazyListState()
    var enableFling by remember { mutableStateOf(false) }

    val snappingLayout = remember(state) { SnapLayoutInfoProvider(state) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    Column {
        Row {
            Switch(checked = enableFling, onCheckedChange = { enableFling = it })
            Text("1.3")
        }
        LazyRow(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            state = state,
            flingBehavior = if (enableFling) flingBehavior else ScrollableDefaults.flingBehavior()
        ) {
            items(200) {
                Box(
                    modifier = Modifier
                        .height(400.dp)
                        .width(200.dp)
                        .padding(8.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(it.toString(), fontSize = 32.sp)
                }
            }
        }
    }
}

@Preview
@Composable
fun FlingPrev() {
    Fling()
}

