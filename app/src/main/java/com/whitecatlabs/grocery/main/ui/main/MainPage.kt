package com.whitecatlabs.grocery.main.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whitecatlabs.grocery.R
import com.whitecatlabs.grocery.main.ui.theme.AppTheme

@Composable
fun MainPage(
    viewState: MainContract.ViewState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = { AppBar() }) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (viewState) {
                is MainContract.ViewState.Loading -> Loading()
                is MainContract.ViewState.Result -> Content()
            }
        }
    }
}

@Composable
private fun Loading() {
    CircularProgressIndicator(
        modifier = Modifier.size(40.dp),
        color = Color.Blue
    )
}

@Composable
private fun Content() {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),

        ) {
        Grocery(
            modifier = Modifier
                .clip(RoundedCornerShape(size = 8.dp))
                .background(colorResource(id = R.color.green_groceries))
                .weight(1f, true), title = "Green Groceries"
        )
        Grocery(
            modifier = Modifier
                .clip(RoundedCornerShape(size = 8.dp))
                .background(colorResource(id = R.color.red_groceries))
                .weight(1f, true), title = "Red Groceries"
        )
    }
}

@Composable
fun Grocery(
    title: String, modifier: Modifier = Modifier
) {
    Box(modifier = modifier.size(155.dp, 60.dp)) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontWeight = FontWeight.Bold,
            text = title,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }
}

@Composable
fun AppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back), contentDescription = "Back"
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Your Groceries",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun AppBarPreview() {
    AppTheme {
        MainPage(MainContract.ViewState.Loading)
    }
}
