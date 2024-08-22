package com.digi.composeuiux.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.digi.composeuiux.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HeroNavigation(modifier: Modifier = Modifier) {
    SharedTransitionLayout {
        val nc = rememberNavController()
        NavHost(navController = nc, startDestination = "list") {
            composable("list") {
                ListScreen(
                    nc,
                    this@SharedTransitionLayout,
                    this@composable,
                    modifier
                )
            }
            composable(
                "detail/{item}",
                arguments = listOf(navArgument("item") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("item") ?: 0
                val newsItem = getList()[id]
                DetailScreen(
                    modifier = modifier,
                    nc,
                    newsItem,
                    this@SharedTransitionLayout,
                    this@composable
                )
            }
        }
    }
}

data class News(
    val image: Int,
    val title: String,
)

fun getList() = listOf(
    News(image = R.drawable.girl_trekking, title = "Trekking"),
    News(image = R.drawable.girl_trekking, title = "Green Forest"),
    News(image = R.drawable.girl_trekking, title = "Journey to the West"),
    News(image = R.drawable.girl_trekking, title = "Journey to the East"),
    News(image = R.drawable.girl_trekking, title = "Journey to the South"),
)


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ListScreen(
    navController: NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
) {
    LazyRow(modifier = modifier.fillMaxWidth()) {
        itemsIndexed(getList()) { idx, item ->
            with(sharedTransitionScope) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            navController.navigate("detail/$idx")
                        }
                ) {
                    Image(
                        painter = painterResource(id = item.image),
                        contentDescription = item.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .sharedElement(
                                sharedTransitionScope.rememberSharedContentState(key = item.title),
                                animatedVisibilityScope = animatedContentScope,
                            )
                    )
                    Text(text = item.title, style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    nc: NavHostController,
    newsItem: News,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {

    BackHandler {
        nc.popBackStack()
    }

    with(sharedTransitionScope) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = newsItem.image),
                contentDescription = newsItem.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = newsItem.title),
                        animatedVisibilityScope = animatedContentScope,
                    )
            )
            Text(
                text = newsItem.title,
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}
