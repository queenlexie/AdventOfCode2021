import kotlin.math.abs

fun main(){
    val fishList = readIntList("src/main/resources/day7.txt")
    val position = findPosition(fishList)
    println(countFuel(fishList, position))
}

fun findPosition(list: List<Int>): Int {
    val listSorted = list.sorted()
    return if (list.size % 2 == 0)
        listSorted[list.size / 2]
    else
        listSorted[(list.size + 1) / 2]
}

fun countFuel(list: List<Int>, position: Int): Int {
    var positionChanged = position
    var min = 89558991
    for(i in 1..1000) {
        var fuel = 0
        list.forEach {
            fuel += add(abs(it - positionChanged))
        }
        if(fuel<min) {
            min = fuel
            println(positionChanged)
        }
        positionChanged++
    }
    return min
}

fun add(number: Int): Int{
    return if(number>0)
        add(number-1)+number
    else 0
}
