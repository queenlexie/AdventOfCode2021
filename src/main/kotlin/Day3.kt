fun main() {
    val array = readArray("src/main/resources/day3.txt")
    // println(powerConsumption(array))
    println(supportRating(array))
}

fun powerConsumption(array: Array<CharArray>): Int {
    val gamma = getGamma(array)
    val epsilon = gamma
        .replace("0", "x")
        .replace("1", "0")
        .replace("x", "1")

    return Integer.parseInt(epsilon, 2) * Integer.parseInt(gamma, 2)
}

fun supportRating(array: Array<CharArray>): Int {
    var co2 = ""
    var oxygen = ""
    var arrayOxygen = array
    var arrayCO2 = array
    for (i in 0 until array[0].size) {
        val valueCO2 = findValueCO2(arrayCO2, i)
        val valueOxygen = findValueOxygen(arrayOxygen, i)

        arrayOxygen = arrayOxygen.filter { it[i].digitToInt() == valueOxygen }.toTypedArray()
        if (arrayOxygen.size == 1) oxygen = String(arrayOxygen[0])

        arrayCO2 = arrayCO2.filter { it[i].digitToInt() != valueCO2 }.toTypedArray()
        if (arrayCO2.size == 1) co2 = String(arrayCO2[0])
    }
    println("$oxygen $co2")
    return Integer.parseInt(co2, 2) * Integer.parseInt(oxygen, 2)
}

fun findValueOxygen(array: Array<CharArray>, position: Int): Int {
    var sum = 0
    array.forEach {
        sum += it[position].digitToInt()
    }

    val medium = if(array.size%2==0)
        (array.size) / 2
    else
        (array.size+1)/2
    return if (sum >= medium) 1 else 0
}

fun findValueCO2(array: Array<CharArray>, position: Int): Int {
    var sum = 0
    array.forEach {
        sum += it[position].digitToInt()
    }
    val medium = if(array.size%2==0)
        (array.size) / 2
    else
        (array.size+1)/2

    return if (sum >= medium) 1 else 0
}

fun getGamma(array: Array<CharArray>): String {
    var gamma = ""
    for (i in 0 until array[0].size) {
        gamma += findValueCO2(array, i)
    }
    return gamma
}
