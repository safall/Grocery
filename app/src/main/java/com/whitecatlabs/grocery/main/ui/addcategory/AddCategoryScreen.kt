package com.whitecatlabs.grocery.main.ui.addcategory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
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
import com.whitecatlabs.grocery.main.ui.AppBar
import com.whitecatlabs.grocery.main.ui.theme.AppTheme

@Composable
fun AddCategoryScreen(
    viewState: AddCategoryContract.ViewState,
    modifier: Modifier = Modifier,
    onEvent: (AddCategoryContract.Event) -> Unit
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            AppBar(
                title = "Select Categories",
                onBackButtonClicked = { onEvent(AddCategoryContract.Event.BackButtonClickedEvent) },
                showAction = false,
                onActionButtonClicked = { }
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            when (viewState) {
                is AddCategoryContract.ViewState.Error -> Error()
                is AddCategoryContract.ViewState.Loading -> Loading()
                is AddCategoryContract.ViewState.Result -> Content(
                    viewState.items,
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
    items: List<CategoryViewState>,
    onEvent: (AddCategoryContract.Event) -> Unit
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
                Grocery(item = it) { event ->
                    onEvent(event)
                }
            }
        }
    }
}

@Composable
fun Grocery(
    item: CategoryViewState,
    modifier: Modifier = Modifier,
    onEvent: (AddCategoryContract.Event) -> Unit
) {
    Row(
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(size = 8.dp))
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color(android.graphics.Color.parseColor(item.color)))
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Bold,
            text = item.title,
            color = Color.Black,
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
        )

        Checkbox(checked = item.isSelected, onCheckedChange = {
            onEvent(AddCategoryContract.Event.ItemCheckedEvent(id = item.id, isSelected = it))
        })
    }
}

@Composable
@Preview
private fun AddCategoryScreenPreview() {
    AppTheme {
        AddCategoryScreen(
            viewState = AddCategoryContract.ViewState.Result(
                listOf(
                    CategoryViewState("1", "Apple", color = "#0f0f0f0f", isSelected = true),
                    CategoryViewState("2", "Orange", color = "#0f0f0f0f", isSelected = true)
                )
            )
        ) {}
    }
}
