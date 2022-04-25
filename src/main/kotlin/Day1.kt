fun main() {
    val list = read("src/main/resources/day1.txt")
    println(comparePairs(list))
    println(compareTriples(list))
}

fun comparePairs(list: List<String>): Int{
    var counter = 0

    for(i in 0 until list.size-1){
        if(list[i].toInt() < list[i+1].toInt())
            counter++
    }
    return counter
}

fun compareTriples(list: List<String>): Int {
    var counter = 0

    for(i in 0 until list.size-3){
        val firstTriple = list[i].toInt() + list[i+1].toInt() + list[i+2].toInt()
        val secondTriple = list[i+1].toInt() + list[i+2].toInt() + list[i+3].toInt()
        if(firstTriple < secondTriple)
            counter++
    }
    return counter
}
