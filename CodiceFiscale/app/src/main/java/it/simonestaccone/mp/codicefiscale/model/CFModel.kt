package it.simonestaccone.mp.codicefiscale.model

import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import it.simonestaccone.mp.codicefiscale.MainActivity
import it.simonestaccone.mp.codicefiscale.MainActivity.Companion.fiscalCode
import it.simonestaccone.mp.codicefiscale.ui.layout.MainLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class CFModel : Parcelable {
    companion object {
        const val TAG = "CF2021"
        private const val FILENAME = "countrycode.csv"
        const val VOWELS = "AEIOU"
        const val CONSONANTS = "BCDFGHJKLMNPQRSTVWXYZ"
        const val MONTHS = "ABCDEHLMPRST"
        const val CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val ODDVALUES = listOf(
            1, 0, 5, 7, 9, 13, 15, 17, 19,
            21, 1, 0, 5, 7, 9, 13, 15, 17,
            19, 21, 2, 4, 18, 20, 11, 3, 6,
            8, 12, 14, 16, 10, 22, 25, 24, 23
        )
        val EVENVALUES = listOf(
            0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 0, 1, 2, 3, 4, 5, 6, 7,
            8, 9, 10, 11, 12, 13, 14, 15, 16,
            17, 18, 19, 20, 21, 22, 23, 24, 25
        )
    }

    @IgnoredOnParcel
    private var _firstName = mutableStateOf("")
    var firstName: String
        get() {
            return _firstName.value
        }
        set(value) {
            _firstName.value = value
        }
    @IgnoredOnParcel
    private var _lastName = mutableStateOf("")
    var lastName: String
        get() {
            return _lastName.value
        }
        set(value) {
            _lastName.value = value
        }
    @IgnoredOnParcel
    var gender: String = ""
    @IgnoredOnParcel
    var birthDate: String = ""
    @IgnoredOnParcel
    var birthPlace: String = ""


    fun encode(): String {
        val first15 = getLastNameCode() +
                getFirstNameCode() +
                getBirthAndSexCode() +
                getCountryCode()
        return first15 + getControlCode(first15)
    }

    private fun getBirthAndSexCode(): String {
        var ret = "99X99"
        if (birthDate.length == 10) {
            try {
                with(birthDate) {
                    val day =
                        (substring(0, 2)
                            .toInt() + if (gender.compareTo("F") == 0) 40 else 0).toString()
                            .padStart(2, '0')
                    val month = MONTHS[substring(3, 5).toInt() - 1]
                    ret = substring(8) + month + day
                }
            } catch (e: Exception) {
                Log.w(MainActivity.TAG, "Bad Data")
            }
        }
        return ret
    }

    private fun getFirstNameCode(): String {
        var vowels = ""
        var consonants = ""
        val name = firstName.uppercase(Locale.getDefault())
        for (c in name) {
            if (c in CONSONANTS)
                consonants += c
            if (c in VOWELS)
                vowels += c
        }
        if (consonants.length > 3)
            return "" + consonants[0] + consonants[2] + consonants[3]
        if (consonants.length == 3)
            return consonants
        return (consonants + vowels + "XXX").substring(0, 3)
    }

    private fun getLastNameCode(): String {
        var consonants = ""
        var vowels = ""
        val name = lastName.uppercase(Locale.getDefault())
        for (c in name) {
            if (c in CONSONANTS)
                consonants += c
            if (c in VOWELS)
                vowels += c
        }
        return (consonants + vowels + "XXX").substring(0, 3)
    }

    private fun getCountryCode(): String {
        val ret = findSingleCountry(birthPlace)
        return ret
    }

    private fun findSingleCountry(name: String): String {
        var birth:String = ""
        val countryList = MainActivity.countryList
        if (name == ""){
            birth = "X999"
        }
        else{
            for (country in countryList) {
                if (country.description.uppercase(Locale.getDefault()) == name.uppercase(Locale.getDefault())) {
                    birth = country.code
                    break
                }
            }
        }
        return birth
    }


    private fun getControlCode(first15: String): String {
        var code = 0

        first15.forEachIndexed { index, char ->
            val pos = CHARACTERS.indexOf(char)
            if (index % 2 == 0)
                code += ODDVALUES[pos]
            else
                code += EVENVALUES[pos]
        }
        return CHARACTERS[code % 26 + 10].toString()
    }


}