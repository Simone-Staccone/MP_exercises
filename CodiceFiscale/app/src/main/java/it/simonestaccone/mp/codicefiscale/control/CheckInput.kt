package it.simonestaccone.mp.codicefiscale.control

fun checkCity(value: String): String {
    return checkEnd(value)
}

fun checkName(value: String): String {
    val temp = value
    temp.replace(".","")
    return checkEnd(temp)
}

fun checkDate(value: String): String {
    var temp = value
    temp = temp.replace("-","/")
    temp = temp.replace("\\","/")
    temp = checkEnd(temp)
    if(temp.length > 10){
        throw Exception()
    }
    else if(temp.length == 9){
        temp = "0".plus(temp)
    }


    if(temp.length==10){
        var num:Int
        if(temp.get(2) != '/' || temp.get(5) != '/'){
            throw Exception()
        }
        num = temp.substring(0,2).toInt()
        System.out.println(temp.substring(0,2) + num)
        if(num > 31 || num <= 0)
            throw Exception()

        num = temp.substring(3,5).toInt()
        System.out.println(temp.substring(4,6) + num)
        if(num>12 || num < 0)
            throw Exception()
    }
    return temp
}

fun checkEnd(value: String): String {
    var temp = value
    while(temp.length > 0 && temp.get(temp.length-1) == ' '){
        temp = temp.substring(0,value.length-1)
    }
    return temp
}