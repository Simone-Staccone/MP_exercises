package it.simonestaccone.mp.proverbi.ui.layout.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.simonestaccone.mp.proverbi.model.Proverbio
import it.simonestaccone.mp.proverbi.ui.layout.ScreenRouter
import it.simonestaccone.mp.proverbi.viewmodel.MainViewModel


@Composable
fun ScrollableColumn(list:List<Proverbio>,viewModel:MainViewModel,what:MutableState<String>){

    val filteredList: List<Proverbio> = list.filter { proverb ->
        (proverb.text.uppercase().contains(what.value.uppercase()))
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {



        items(filteredList) { proverb ->
            Card(modifier = Modifier.padding(6.dp)) {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colors.background)
                        .fillMaxWidth()
                ) {
                    Text(
                        proverb.text, fontSize = 32.sp, modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.background)
                            .clickable { ScreenRouter.swap(3, proverb) }
                    )

                    var color by remember { mutableStateOf(
                        if(proverb.favorite == 0)
                            Color.Black
                        else
                            Color.Red
                    ) }
                    var actualIcon by remember { mutableStateOf(
                        if(proverb.favorite == 0)
                            Icons.Rounded.FavoriteBorder
                        else
                            Icons.Rounded.Favorite
                    )
                    }
                    IconButton(modifier = Modifier.padding(start = 2.dp), onClick = {
                        if (proverb.favorite == 1) {
                            color = Color.Black
                            actualIcon = Icons.Rounded.FavoriteBorder
                            viewModel.updateFavoriteOff(proverb)
                        } else {
                            color = Color.Red
                            actualIcon = Icons.Rounded.Favorite
                            viewModel.updateFavoriteOn(proverb)                            }
                    }) {
                        Icon(
                            imageVector = actualIcon,
                            tint = color,
                            contentDescription = "favorite"
                        )
                    }

                    Divider(
                        color = Color.Blue, thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }

    }
}