package com.example.basiclayoutcodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiclayoutcodelab.ui.theme.BasicLayoutCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySoothAppPortainer()
        }
    }
}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@Composable
fun MySoothAppPortainer(){
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ){ padding ->
        HomeScreen(Modifier.padding(padding))
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    Column(modifier.verticalScroll(rememberScrollState())){
        Spacer(modifier.height(16.dp))
        SearchBar(modifier.padding(horizontal = 16.dp))
        HomeSection(
            title = R.string.align_your_body,
            { AlignYourBodyRow() }
        )
        HomeSection(
            title = R.string.favorite_collections,
            { FavoriteCollectionGrid() }
        )
        Spacer(modifier.height(16.dp))
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    content: @Composable ()->Unit,
    modifier: Modifier = Modifier)
{
    Column(modifier){
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier){
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){
        items(items = alignYourBodyData) { item ->
            AlignYourBodyElement(item.drawable, item.text)
        }
    }
}

@Composable
fun AlignYourBodyElement(@DrawableRes drawable: Int, @StringRes text: Int, modifier: Modifier = Modifier){
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.size(88.dp).clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun FavoriteCollectionGrid(modifier: Modifier = Modifier){
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(items = favoriteCollectionsData) {item ->
            FavoriteCollectionCard(item.drawable, item.text)
        }
    }
}

@Composable
fun FavoriteCollectionCard(@DrawableRes drawable: Int, @StringRes text: Int, modifier: Modifier = Modifier){
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant
    ){
        Row(
            modifier = modifier.width(255.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.size(80.dp),
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier){
    NavigationBar(
        modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ){
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(R.string.bottom_navigation_home))
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(R.string.bottom_navigation_profile))
            },
            selected = false,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "HomeScreen")
@Composable
fun HomeScreenPreview() {
    BasicLayoutCodelabTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true, name = "HomeSection")
@Composable
fun HomeSectionPreview() {
    BasicLayoutCodelabTheme {
        HomeSection(R.string.align_your_body, {AlignYourBodyRow()})
    }
}

@Preview(showBackground = true, name = "SearchBar")
@Composable
fun SearchBarPreview() {
    BasicLayoutCodelabTheme {
        SearchBar()
    }
}

@Preview(showBackground = true, name = "AlignYourBodyRow")
@Composable
fun AlignYourBodyRowPreview() {
    BasicLayoutCodelabTheme {
        AlignYourBodyRow()
    }
}

@Preview(showBackground = true, name = "FavoriteCollectionGrid")
@Composable
fun FavoriteCollectionGridPreview() {
    BasicLayoutCodelabTheme {
        FavoriteCollectionGrid()
    }
}

@Preview(showBackground = true, name = "BottomNavigationBar")
@Composable
fun BottomNavigationBarPreview() {
    BasicLayoutCodelabTheme {
        BottomNavigationBar()
    }
}