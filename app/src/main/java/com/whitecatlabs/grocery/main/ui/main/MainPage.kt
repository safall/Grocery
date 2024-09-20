package com.whitecatlabs.grocery.main.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.whitecatlabs.grocery.main.databse.entity.TaskOwnerEntity
import com.whitecatlabs.grocery.main.ui.theme.DBPGTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    modifier: Modifier = Modifier,
    viewState: MainContract.ViewState
) {

    Column(modifier = modifier) {
        TopAppBar(modifier = Modifier.background(Color.Green), title = { Text(text = "DBPG") })
        Column(modifier = modifier) { }
    }
}

@Composable
fun TableScreen(tableItems: List<TaskOwnerEntity>) {
    // Just a fake data... a Pair of Int and String
    // Each cell of a column must have the same weight.
    val column1Weight = .3f // 30%
    val column2Weight = .7f // 70%
    // The LazyColumn will be our table. Notice the use of the weights below
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Here is the header
        item {
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "Column 1", weight = column1Weight)
                TableCell(text = "Column 2", weight = column2Weight)
            }
        }
        items(tableItems.size) {
            val (id, text) = tableItems[it]
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = id.toString(), weight = column1Weight)
                TableCell(text = text, weight = column2Weight)
            }
        }
    }
}

@Composable
fun RowScope.TableCell(text: String, weight: Float) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DBPGTheme {
        MainPage(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            viewState = MainContract.ViewState.Result(emptyList(), emptyList())
        )
    }
}
