/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Cat
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val snackBarHostState = SnackbarHostState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Kitten Adoption")
                }
            )
        },
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        }) {

        val viewModel = MainViewModel()
        CatList(viewModel)
        viewModel.currentCat?.let {
            CatDetails(it) { cat ->
                coroutineScope.launch {
                    snackBarHostState.showSnackbar("You have adopted ${cat.name}")
                }
            }
        }
    }
}

@Composable
fun CatList(viewModel: MainViewModel) {
    LazyColumn(
        contentPadding = PaddingValues(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(items = viewModel.cats) { cat ->
            Card(
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (viewModel.currentCat == null) {
                            viewModel.showDetails(cat)
                        }
                    }) {
                Box {
                    Image(
                        painterResource(cat.photo),
                        contentDescription = "Picture of cat: ${cat.name}",
                        Modifier
                            .fillMaxWidth()
                            .height(240.dp),
                        contentScale = ContentScale.Crop
                    )


                    Column(Modifier.padding(paddingValues = PaddingValues(start = 5.dp))) {
                        Text(cat.name, color = Color.White, style = MaterialTheme.typography.h4)
                        Text(cat.age, color = Color.White, style = MaterialTheme.typography.body1)
                        Text(
                            cat.describe,
                            color = Color.White,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CatDetails(cat: Cat, onClick: (Cat) -> Unit = {}) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Box {
            Image(
                painterResource(cat.photo),
                "Photo: ${cat.name}",
                Modifier.aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Button(
                { onClick(cat) },
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text(text = "Adopt")
            }
        }
        Column(Modifier.padding(16.dp, 8.dp)) {
            Text("Name: ${cat.name}", style = MaterialTheme.typography.h4)
            ProvideTextStyle(MaterialTheme.typography.h5) {
                Text("Gender: ${cat.gender}")
                Text("Age: ${cat.age}")
                Text("Gender: ${cat.gender}")
                Text("Weight: ${cat.weight}")
                Text("Describe: ${cat.describe}")
            }
        }
    }
}


