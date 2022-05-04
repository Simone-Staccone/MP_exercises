package it.simonestaccone.mp.proverbi.database

import androidx.lifecycle.LiveData
import androidx.room.*
import it.simonestaccone.mp.proverbi.model.Proverbio

@Dao
interface DAOProverbi {
    @Delete
    fun removeProverbio(proverbio: Proverbio)
    @Delete
    fun removeProverbi(proverbi:List<Proverbio>)
    @Insert
    fun insertAll(proverbi: List<Proverbio>)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOne(proverbio: Proverbio)
    @Query("UPDATE Proverbio SET text = :newProverbio WHERE text = :proverbio")
    fun updateProverbio(proverbio: String,newProverbio: String)
    @Query("SELECT * FROM Proverbio ORDER BY text")
    fun getAll():LiveData<List<Proverbio>>
    @Query("SELECT * FROM Proverbio WHERE favorite=1 ORDER BY text")
    fun getFavorites():LiveData<List<Proverbio>>
    @Query("SELECT * FROM Proverbio WHERE text LIKE :filter ORDER BY text")
    fun filterProverbi(filter:String):LiveData<List<Proverbio>>
    @Query("UPDATE Proverbio SET favorite = 1 WHERE text = :filter")
    fun isFavorite(filter:String)
    @Query("UPDATE Proverbio SET favorite = 0 WHERE text = :filter")
    fun isNotFavorite(filter:String)
    @Query("SELECT * FROM Proverbio WHERE favorite = 1 ORDER BY text")
    fun switchToFavorite():LiveData<List<Proverbio>>
}
