package it.simonestaccone.mp.proverbi.ui.layout

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import it.simonestaccone.mp.proverbi.model.Proverbio
import it.simonestaccone.mp.proverbi.viewmodel.MainViewModel

@Composable
fun AddLayout(context: Context, viewModel: MainViewModel) {

    var newText by remember { mutableStateOf("") }




    Column{
        TopAppBar(contentColor = Color.White) {
            IconButton(onClick = { ScreenRouter.navigateTo(1) }) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = "Back button")
            }
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Card(backgroundColor = Color.White, modifier = Modifier.fillMaxWidth()) {
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
                    ScreenRouter.navigateTo(1 )
                }, modifier = Modifier.padding(horizontal = 10.dp)) {
                    Text(text = "Indietro")
                }
                Button(onClick = {
                    var proverbio = Proverbio()
                    proverbio.text = newText
                    viewModel.addText(proverbio)
                    Toast.makeText(context, "Elemento aggiunto correttamente", Toast.LENGTH_SHORT).show()
                    ScreenRouter.navigateTo(1)
                }) {
                    Text(text = "Aggiungi")
                }
            }
        }




    }

}