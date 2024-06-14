package com.example.tweetsapplication.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.loader.content.Loader
import com.example.tweetsapplication.R
import com.example.tweetsapplication.viewModels.CategoryViewModel
import java.util.Locale

@Composable
fun CategoryScreen(onClick: (category: String) -> Unit) {
    // create object of composable viewModel
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    // collect the StateFlow as State to be used within composable func
    val categories: State<List<String>> = categoryViewModel.categories.collectAsState()

    if (categories.value.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 2.dp,
                modifier = Modifier.size(30.dp)
            )
        }

    } else {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            items(categories.value.distinct()) {
                CategoryItem(category = it, onClick)
            }
        }
    }
}

@Composable
fun CategoryItem(category: String, onClick: (category: String) -> Unit) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .padding(4.dp)
            .size(160.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(
                1.dp, Color(0xFFEEEEEE)
            )
            .paint(
                painter = painterResource(id = R.drawable.bg),
                contentScale = ContentScale.Crop
            )
            .clickable {
                onClick(category)
            }
    ) {
        Text(
            text = category.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 20.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}