package it.simonestaccone.mp.codicefiscale

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import it.simonestaccone.mp.codicefiscale.model.CFModel
import it.simonestaccone.mp.codicefiscale.model.CFViewModel
import it.simonestaccone.mp.codicefiscale.model.CountryModel
import it.simonestaccone.mp.codicefiscale.ui.layout.TheLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class MainActivity : ComponentActivity() {
    companion object {
        const val TAG = "CF2021"
        private const val FILENAME = "countrycode.csv"
        var fiscalCode = CFModel()
        var countryList = mutableListOf<CountryModel>()
    }

    private var cFviewModel = CFViewModel()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readFile()
        lockDeviceRotation(true)
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Works also without calling, check themes.xml
        setContent {
            Surface(modifier = Modifier.fillMaxSize()){
                TheLayout()
            }
        }
    }

    fun lockDeviceRotation(value: Boolean) {
        requestedOrientation = if (value) {
            val currentOrientation = resources.configuration.orientation
            if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            } else {
                ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
            }
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            ActivityInfo.SCREEN_ORIENTATION_FULL_USER
        }
    }



    private fun readFile() {
        var i:Int = 0
        val reader = this.assets.open(FILENAME)
            .bufferedReader()
        CoroutineScope(Dispatchers.IO).launch {
            reader.useLines { lines ->
                lines.forEach {
                    val fields = it.uppercase(Locale.getDefault()).split(";")
                    countryList.add(CountryModel(fields[0], fields[1], fields[2]))
                }
                launch(Dispatchers.Default) {
                    countryList.sortBy { it.description }

                }
            }
        }
    }


}


