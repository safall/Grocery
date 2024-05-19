package com.safal.android.dbpg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApp).applicationComponent.inject(this)
        enableEdgeToEdge()
        setContent {
            DBPGTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android", modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }


        lifecycleScope.launch {
//            insertTasks()
            taskDao.fetchAllTask().map {
                println(it)
            }
        }

        lifecycleScope.launch {
//            insertTaskOwners()
            taskOwnerDao.fetchAllTaskOwner().map {
                println(it)
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
                    name = "TaskOwner $i",
                    address = "Address $i",
                )
            )
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!", modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        DBPGTheme {
            Greeting("Android")
        }
    }
}
