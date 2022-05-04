package it.simonestaccone.mp.proverbi.Util

class DBName() {
    val DBNAME:String = "p2.db"

}

fun DBName.getName(): String {
    return DBNAME
}