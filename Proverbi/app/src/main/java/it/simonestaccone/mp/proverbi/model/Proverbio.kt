package it.simonestaccone.mp.proverbi.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class Proverbio {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
    var text:String = ""
    var language:String = ""
    var category:Int = 0
    var favorite:Int = 0
}