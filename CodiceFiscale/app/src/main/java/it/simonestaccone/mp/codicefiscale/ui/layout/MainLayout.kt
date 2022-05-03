package it.simonestaccone.mp.codicefiscale.ui.layout


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.simonestaccone.mp.codicefiscale.control.calculateCF
import it.simonestaccone.mp.codicefiscale.control.checkCity
import it.simonestaccone.mp.codicefiscale.control.checkDate
import it.simonestaccone.mp.codicefiscale.control.checkName
import it.simonestaccone.mp.codicefiscale.model.CFModel
import java.lang.Exception


@Composable
fun MainLayout(cfModel: CFModel) {
    val lastname = rememberSaveable {
        mutableStateOf("")
    }
    val firstname = rememberSaveable {
        mutableStateOf("")
    }
    val date = rememberSaveable {
        mutableStateOf("")
    }
    val city = rememberSaveable {
        mutableStateOf("")
    }
    val cf = rememberSaveable() {
        mutableStateOf("")
    }


    val billingPeriodItems = listOf("M", "F")

    var billingPeriodExpanded by remember { mutableStateOf(false) }

    var selectedIndex by remember { mutableStateOf(0) }



    Column(){
        TopAppBar(
            title = {
                Text(text = "Calcolatore Codice Fiscale")
            },
            backgroundColor = Color.Red
        )

        Column(modifier=Modifier.verticalScroll(rememberScrollState())) {
            MakeEditText("Cognome", lastname)
            MakeEditText("Nome",firstname)
            MakeEditText("Data di nascita",date)
            MakeEditText("Città di nascita",city)


            Box(
                modifier = Modifier
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                ScrollMenu(
                    menuItems = billingPeriodItems,
                    menuExpandedState = billingPeriodExpanded,
                    seletedIndex = selectedIndex,
                    updateMenuExpandStatus = {
                        billingPeriodExpanded = true
                    },
                    onDismissMenuView = {
                        billingPeriodExpanded = false
                    },
                    onMenuItemclick = { index ->
                        selectedIndex = index
                        billingPeriodExpanded = false
                    }
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        try{
                            cfModel.lastName = checkName(lastname.value)
                            cfModel.firstName = checkName(firstname.value)
                            cfModel.birthDate = checkDate(date.value)
                            cfModel.birthPlace = checkCity(city.value)
                            cfModel.gender = billingPeriodItems[selectedIndex]
                            cf.value = calculateCF(cfModel)
                        }catch(e:Exception){
                            e.printStackTrace()
                            cf.value = "Invalid date [dd/mm/yyyy]"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text("Calcola")
                }
                SelectionContainer() {
                    Text(
                        text = cf.value,
                        textAlign = TextAlign.Center,
                        fontSize = 32.sp,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 8.dp)
                    )
                }
            }
        }
    }
}



@Composable
fun MakeEditText(s: String, text: MutableState<String>)  {

    OutlinedTextField(
        value = text.value,
        onValueChange = {
            text.value = it.uppercase()
        },
        label = {
            Text(
                text = s,
                fontSize = 24.sp,
                color = Color.Red
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Red,
            unfocusedBorderColor = Color.Red)
    )
}