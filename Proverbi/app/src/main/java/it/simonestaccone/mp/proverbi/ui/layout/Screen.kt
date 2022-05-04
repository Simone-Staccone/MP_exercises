package it.simonestaccone.mp.proverbi.ui.layout

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import it.simonestaccone.mp.proverbi.model.Proverbio
import it.simonestaccone.mp.proverbi.ui.layout.util.ScrollableColumn
import it.simonestaccone.mp.proverbi.viewmodel.MainViewModel


@Composable
fun MainScreen(viewModel: MainViewModel, allProverbi: List<Proverbio>, i: Int) {
    val what = rememberSaveable {
        mutableStateOf("")
    }

    val filteredList: List<Proverbio> = allProverbi.filter { proverb ->
        (proverb.text.uppercase().contains(what.value.uppercase()))
    }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { ScreenRouter.navigateTo(4)}) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "")
            }
        },
        topBar = {MakeTopBar(what)}
    ) {
        ScrollableColumn(allProverbi, viewModel,what)

    }





}
