package com.yzh.wanandroid

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import com.yzh.wanandroid.ext.formatToTime
import com.yzh.wanandroid.network.response.Article
import com.yzh.wanandroid.network.response.Tag
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
                val uri = Uri.parse(article.link)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            }
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = article.title,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body1,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Chapter(
                    text = article.publishTime.formatToTime(),
                )
                Chapter(
                    text = article.chapterName
                )
            }
            if (article.tags.isNotEmpty()) {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    items(article.tags) { tag ->
                        Tag(tag)
                    }
                }
            }
        }
    }
}

@Composable
fun Chapter(
    text: String
) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .background(
                color = MaterialTheme.colors.secondary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(percent = 50),
            )
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.body2,
        )
    }
}

@Composable
fun Tag(
    tag: Tag
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clickable {
                showToast(text = tag.url, context = context)
            }
            .wrapContentWidth()
            .background(
                color = MaterialTheme.colors.secondary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(percent = 50),
            )
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = tag.name,
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.body2,
        )
    }
}

fun showToast(
    text: String,
    context: Context
) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}