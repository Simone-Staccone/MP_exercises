package it.simonestaccone.mp.codicefiscale.ui.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import it.simonestaccone.mp.codicefiscale.model.CFModel


@Composable
fun TheLayout() {
    val cfModel = rememberSaveable() {
        CFModel()
    }


    MainLayout(cfModel)
}