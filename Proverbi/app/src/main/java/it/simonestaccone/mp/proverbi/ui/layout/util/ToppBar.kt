package it.simonestaccone.mp.proverbi.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MakeTopBar(s: MutableState<String>) {

    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Tutti", "Preferiti")




    TopAppBar(backgroundColor = Color.Blue) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Sharp.Search,
                    tint = Color.White,
                    contentDescription = "Search Lens"
                )
            }

            TextField(
                textStyle = LocalTextStyle.current.copy(fontSize = 16.sp, color = Color.White),
                label = { Text(text = "Search", color = Color.White) },
                value = s.value,
                onValueChange = {
                    s.value = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Blue
                ),
                trailingIcon = {
                    Icon(Icons.Filled.Clear,
                        contentDescription = "clear text",
                        tint = Color.White,
                        modifier = Modifier
                            .offset(x = 10.dp)
                            .clickable {
                                s.value = ""
                            }
                    )
                }
            )
            IconButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Sharp.Menu,
                    tint = Color.White,
                    contentDescription = "Search Form"
                )
            }
        }



        Box(modifier = Modifier.fillMaxSize()) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Color.Blue)
            ) {
                items.forEachIndexed { index, s ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        if (index == 0)
                            ScreenRouter.navigateTo(1)
                        else
                            ScreenRouter.navigateTo(2)
                    }) {
                        Text(text = s, color = Color.White)
                    }
                }
            }
        }
    }


}