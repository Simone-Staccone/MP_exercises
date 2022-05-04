package it.simonestaccone.mp.proverbi.ui.layout

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.unit.dp
import it.simonestaccone.mp.proverbi.model.Proverbio
import it.simonestaccone.mp.proverbi.viewmodel.MainViewModel

@Composable
fun EditLayout(context: Context, viewModel: MainViewModel, proverb: Proverbio) {

    var color by remember {
        mutableStateOf(
            if (proverb.favorite == 0)
                Color.Black
            else
                Color.Red
        )
    }
    var actualIcon by remember {
        mutableStateOf(
            if (proverb.favorite == 0)
                Icons.Rounded.FavoriteBorder
            else
                Icons.Rounded.Favorite
        )
    }
    var isFavorite by remember {
        mutableStateOf(
            if (proverb.favorite == 0)
                0
            else
                1
        )
    }
    var newText by remember { mutableStateOf(proverb.text) }




    Column{
        TopAppBar(contentColor = Color.White) {
            IconButton(onClick = { changeBack(color) }) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = "Back button")
            }
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = {
                    viewModel.deleteProverbio(proverb)
                    Toast.makeText(context, "Elemento eliminato correttamente", Toast.LENGTH_SHORT).show()
                    changeBack(color)
                },modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Rounded.Delete,"Delete Button")
                }

                IconButton(
                    onClick = {
                        if (isFavorite == 0) {
                            color = Color.Red
                            actualIcon = Icons.Rounded.Favorite
                            isFavorite = 1
                            viewModel.updateFavoriteOn(proverb)
                        } else {
                            color = Color.Black
                            actualIcon = Icons.Rounded.FavoriteBorder
                            isFavorite = 0
                            viewModel.updateFavoriteOff(proverb)
                        }
                    }, modifier = Modifier
                        .size(24.dp)
                ) {
                    Icon(
                        actualIcon,
                        contentDescription = "Favorite icon",
                        tint = color
                    )
                }
            }

            Card(backgroundColor = Color.White, modifier = Modifier.fillMaxWidth().padding(top = 6.dp)) {
                TextField(
                    value = newText,
                    onValueChange = { newText = it },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Blue,textColor = Color.White)
                )
            }

            Row(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .align(Alignment.End)
            ) {
                Button(onClick = {
                    changeBack(color)
                }, modifier = Modifier.padding(horizontal = 10.dp)) {
                    Text(text = "Indietro")
                }
                Button(onClick = {
                    viewModel.updateText(proverb.text,newText)
                    Toast.makeText(context, "Elemento salvato correttamente", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Salva")
                }
            }
        }




    }

}