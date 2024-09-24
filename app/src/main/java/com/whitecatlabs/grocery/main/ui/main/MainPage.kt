package com.whitecatlabs.grocery.main.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whitecatlabs.grocery.main.databse.SampleData
import com.whitecatlabs.grocery.main.databse.entity.GroceryCategoryEntity
import com.whitecatlabs.grocery.main.ui.AppBar
import com.whitecatlabs.grocery.main.ui.theme.AppTheme

@Composable
fun MainPage(
    viewState: MainContract.ViewState,
    modifier: Modifier = Modifier,
    onEvent: (MainContract.Event) -> Unit
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            AppBar(title = "Your Groceries") {
                onEvent(MainContract.Event.BackButtonClickedEvent)
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            when (viewState) {
                is MainContract.ViewState.Error -> Error()
                is MainContract.ViewState.Loading -> Loading()
                is MainContract.ViewState.Result -> Content(
                    viewState.groceryCategories,
                    onEvent
                )
            }
        }
    }
}

@Composable
private fun Error() {
    Text(
        modifier = Modifier.size(40.dp),
        textAlign = TextAlign.Center,
        text = "Error Occured Please try again~",
        color = Color.Red,
    )
}

@Composable
private fun Loading() {
    CircularProgressIndicator(
        modifier = Modifier.size(40.dp),
        color = Color.Blue,
    )
}

@Composable
private fun Content(
    items: List<GroceryCategoryEntity>,
    onEvent: (MainContract.Event) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items.forEach {
                Grocery(
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                onEvent(MainContract.Event.ItemClickedEvent(it.id, it.title))
                            }
                        )
                        .clip(RoundedCornerShape(size = 8.dp))
                        .background(Color(android.graphics.Color.parseColor(it.color))),
                    title = it.title,
                )
            }
        }
    }
}

@Composable
fun Grocery(
    title: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            text = title,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
        )
    }
}

@Preview
@Composable
private fun AppBarPreview() {
    AppTheme {
        MainPage(
            MainContract.ViewState.Result(
                SampleData.categories
            )
        ) {}
    }
}
