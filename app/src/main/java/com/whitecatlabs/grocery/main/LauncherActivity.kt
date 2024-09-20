package com.whitecatlabs.grocery.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.whitecatlabs.grocery.R
import com.whitecatlabs.grocery.main.ui.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LauncherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    LauncherPage(modifier = Modifier.padding(it))
                }
            }
        }
        lifecycleScope.launch {
            delay(3000)
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

@Composable
private fun LauncherPage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.End)
                .padding(20.dp)

        ) {
            Spacer(modifier = Modifier.height(127.dp))
            Text(
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                text = "Grocery"
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                fontSize = 16.sp,
                text = "Track your groceries yourself"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(
                modifier = Modifier
                    .align(Alignment.End)
                    .width(100.dp)
                    .height(10.dp)
                    .background(color = colorResource(id = R.color.app_blue))
                    .clip(RoundedCornerShape(8.dp))
            )
        }
        Box(
            modifier = Modifier
                .offset(-100.dp, 300.dp)
                .size(400.dp)
                .clip(CircleShape)
                .background(color = colorResource(id = R.color.app_blue))
        )
    }
}


@Composable
@Preview
private fun LauncherPreview() {
    AppTheme {
        Scaffold {
            LauncherPage(modifier = Modifier.padding(it))
        }
    }
}
