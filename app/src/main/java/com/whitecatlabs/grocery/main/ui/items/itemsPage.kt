package com.whitecatlabs.grocery.main.ui.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whitecatlabs.grocery.main.databse.dao.ItemWithSelected
import com.whitecatlabs.grocery.main.ui.AppBar

@Composable
fun ItemsPage(
    title: String,
    viewState: ItemsContract.ViewState,
    modifier: Modifier = Modifier,
    onEvent: (ItemsContract.Event) -> Unit
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            AppBar(title = title) {
                onEvent(ItemsContract.Event.BackButtonClickedEvent)
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
                is ItemsContract.ViewState.Error -> Error()
                is ItemsContract.ViewState.Loading -> Loading()
                is ItemsContract.ViewState.Result -> Content(
                    items = viewState.items,
                    onEvent = onEvent
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
    items: List<ItemWithSelected>,
    modifier: Modifier = Modifier,
    onEvent: (ItemsContract.Event) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(count = items.size, contentType = { it.javaClass }) {
            Item(
                modifier = Modifier.clip(RoundedCornerShape(size = 8.dp)),
                item = items[it],
                onEvent = onEvent
            )
        }
    }
}

@Composable
fun Item(
    item: ItemWithSelected,
    modifier: Modifier = Modifier,
    onEvent: (ItemsContract.Event.ItemCheckedEvent) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = item.selectedItem?.isSelected ?: false, onCheckedChange = {
            onEvent(ItemsContract.Event.ItemCheckedEvent(item.groceryItem.itemId, it))
        })
        Text(
            fontWeight = FontWeight.Bold,
            text = item.groceryItem.title,
            color = Color.Black,
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
        )
    }
}
