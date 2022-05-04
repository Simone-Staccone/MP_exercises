package it.simonestaccone.mp.proverbi.database

import androidx.lifecycle.LiveData
import it.simonestaccone.mp.proverbi.model.Proverbio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoryProverbi (private val daoProverbi:DAOProverbi){
    var allProverbi: LiveData<List<Proverbio>> = daoProverbi.getAll()
    var favoriteProverbio: LiveData<List<Proverbio>> = daoProverbi.switchToFavorite()

    fun insertProverbio(proverbi:Proverbio){
        CoroutineScope(Dispatchers.IO).launch {
            System.out.println("Ciao" + proverbi.text)
            daoProverbi.insertOne(proverbi)
        }
    }

    fun deleteProverbio(proverbi:Proverbio){
        CoroutineScope(Dispatchers.IO).launch {
            System.out.println("Ciao" + proverbi.text)
            daoProverbi.removeProverbio(proverbi)
        }
    }



    fun changeOnFavorite(proverbio:Proverbio){
        CoroutineScope(Dispatchers.IO).launch {
            daoProverbi.isFavorite(proverbio.text)
        }
    }

    fun changeOffFavorite(proverbio:Proverbio){
        CoroutineScope(Dispatchers.IO).launch {
            daoProverbi.isNotFavorite(proverbio.text)
        }
    }



    fun updateTextQuery(proverbio: String, newProverbio: String) {
        CoroutineScope(Dispatchers.IO).launch {
            daoProverbi.updateProverbio(proverbio,newProverbio)
        }
    }
}