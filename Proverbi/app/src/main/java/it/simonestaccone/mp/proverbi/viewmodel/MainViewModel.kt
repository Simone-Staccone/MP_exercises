package it.simonestaccone.mp.proverbi.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import it.simonestaccone.mp.proverbi.database.DBProverbi
import it.simonestaccone.mp.proverbi.database.RepositoryProverbi
import it.simonestaccone.mp.proverbi.model.Proverbio

class MainViewModel(application: Application) {
    var allProverbi: LiveData<List<Proverbio>>
    var favoriteProverbio: LiveData<List<Proverbio>>
    private val rep: RepositoryProverbi

    init{
        val db = DBProverbi.getInstance(application)
        val dao = db.proverbiDAO()
        rep = RepositoryProverbi(dao)

        allProverbi = rep.allProverbi
        favoriteProverbio = rep.favoriteProverbio
    }

    fun updateFavoriteOn(proverbio:Proverbio){
        rep.changeOnFavorite(proverbio)
    }

    fun updateFavoriteOff(proverbio:Proverbio){
        rep.changeOffFavorite(proverbio)
    }

    fun updateText(proverbio:String,newProverbio: String){
        rep.updateTextQuery(proverbio,newProverbio)
    }

    fun addText(proverbio:Proverbio){
        rep.insertProverbio(proverbio)
    }

    fun deleteProverbio(proverbio:Proverbio){
        rep.deleteProverbio(proverbio)
    }

}