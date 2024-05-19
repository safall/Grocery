package com.safal.android.dbpg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.safal.android.dbpg.app.MyApp
import com.safal.android.dbpg.databse.dao.TaskDao
import com.safal.android.dbpg.databse.dao.TaskOwnerDao
import com.safal.android.dbpg.databse.entity.TaskEntity
import com.safal.android.dbpg.databse.entity.TaskOwnerEntity
import com.safal.android.dbpg.ui.theme.DBPGTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var taskDao: TaskDao

    @Inject
    lateinit var taskOwnerDao: TaskOwnerDao

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApp).applicationComponent.inject(this)
        enableEdgeToEdge()
        setContent {
            DBPGTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Page(modifier = Modifier.padding(innerPadding))
                }
            }
        }


        lifecycleScope.launch {
            taskDao.fetchAllTask().map {
                println(it)
            }
        }

        lifecycleScope.launch {
            taskOwnerDao.fetchAllTaskOwner().map {
                println(it)
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun Page(modifier: Modifier = Modifier) {
        Column(modifier = modifier) {
            TopAppBar(modifier = Modifier.background(Color.Green), title = { Text(text = "DBPG") })
            Column(modifier = modifier) {
                Button(modifier = Modifier
                    .size(200.dp, 50.dp)
                    .align(Alignment.CenterHorizontally),
                    onClick = {
                        lifecycleScope.launch {
                            insertTasks()
                            insertTaskOwners()
                        }
                    }) {
                    Text(text = "Fill up table")
                }
            }
        }
    }

    private suspend fun insertTasks() {
        for (i in 1..5) {
            taskDao.insert(
                TaskEntity(
                    id = i.toLong(),
                    title = "Task $i",
                    description = "Description $i",
                    priority = "HIGH",
                )
            )
        }
    }

    private suspend fun insertTaskOwners() {
        for (i in 1..5) {
            taskOwnerDao.insert(
                TaskOwnerEntity(
                    id = i.toLong(),
                    name = "TaskOwner $i"
                )
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        DBPGTheme {
            Page(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
    }
}
