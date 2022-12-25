package com.yzh.wanandroid

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yzh.wanandroid.ext.formatToTime
import com.yzh.wanandroid.network.response.Article
import com.yzh.wanandroid.repository.MainActivityRepository
import com.yzh.wanandroid.ui.theme.WanAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val repository = remember {
                MainActivityRepository()
            }
            WanAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val list = repository.list.collectAsState(initial = emptyList()).value
                    Column {
                        Button(onClick = {
                            repository.loadData()
                        }) {
                            Text(text = "按钮")
                        }
                        LazyColumn(
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            if (list.isEmpty()) {
                                item {
                                    Greeting("Empty Data")
                                }
                            } else {
                                items(list) { item: Article ->
                                    ArticleComposable(article = item)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun ArticleComposable(article: Article) {
    val context = LocalContext.current
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Toast
                    .makeText(context, "This is${article.link}", Toast.LENGTH_SHORT)
                    .show()
            }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = article.title,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body1,
            )
            Spacer(modifier = Modifier.size(4.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "发表于${article.publishTime.formatToTime()}",
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WanAndroidTheme {
        Greeting("Android")
    }
}