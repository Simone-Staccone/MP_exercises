package it.simonestaccone.mp.codicefiscale.control

import it.simonestaccone.mp.codicefiscale.model.CFModel

fun calculateCF(cf : CFModel): String {
    return cf.encode()
}
