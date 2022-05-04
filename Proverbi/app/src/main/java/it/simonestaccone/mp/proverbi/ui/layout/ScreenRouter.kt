package it.simonestaccone.mp.proverbi.ui.layout

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import it.simonestaccone.mp.proverbi.model.Proverbio
import it.simonestaccone.mp.proverbi.viewmodel.MainViewModel

object ScreenRouter {
    var currentScreen: MutableState<Int> = mutableStateOf(1)
    var singleProverbio: MutableState<Proverbio> = mutableStateOf(Proverbio())

    fun navigateTo(destination: Int) {
        currentScreen.value = destination
    }

    fun swap(destination:Int, proverbio:Proverbio){
        currentScreen.value = destination
        singleProverbio.value = proverbio
    }

}




@Composable
fun ProverbsLayout() {

    val context = LocalContext.current.applicationContext
    val viewModel = MainViewModel(context as Application)

    val allProverbi by viewModel.allProverbi.observeAsState(listOf())
    val favoriteProverbi by viewModel.favoriteProverbio.observeAsState(listOf())

    when (ScreenRouter.currentScreen.value) {
        1 -> MainScreen(viewModel, allProverbi,0)
        2 -> MainScreen(viewModel, favoriteProverbi,1)
        3 -> EditLayout(context = context, viewModel = viewModel, proverb = ScreenRouter.singleProverbio.value)
        4 -> AddLayout(context = context, viewModel = viewModel)
    }
}


fun changeBack(color: Color){
    var dest = 1
    if(color == Color.Red){
        dest = 2
    }
    ScreenRouter.navigateTo(dest)
}
