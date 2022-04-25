
import java.util.*
import java.util.function.Consumer


//fun main() {
//    val fishList = readLongList("src/main/resources/day6.txt")
//    //countFishes(fishList)
//    countFishes(fishList, 256)
//}

fun countFishes(fishes: List<Long>, days: Long) {
    var dupa = fishes.toMutableList()
    for (i in 1..days) {
        for (i in 1..dupa.filter { it.equals(0) }.size)
            dupa += 9

        dupa = replace(dupa) as MutableList<Long>
    }
    println(dupa.size)
}

fun replace(fishes: List<Long>): List<Long> {
    return fishes.toMutableList()
        .map { it - 1 }
        .map { if (it.equals(-1)) 6 else it } as MutableList<Long>
}


fun main() {
    //List<Integer> input = List.of(3,4,3,1,2);
    val input = java.util.List.of(
        1,1,1,1,3,1,4,1,4,1,1,2,5,2,5,1,1,1,4,3,1,4,1,1,1,1,1,1,1,2,1,2,4,1,1,1,1,1,1,1,3,1,1,5,1,1,2,1,5,1,1,1,1,1,1,1,1,4,3,1,1,1,2,1,1,5,2,1,1,1,1,4,5,1,1,2,4,1,1,1,5,1,1,1,1,5,1,3,1,1,4,2,1,5,1,2,1,1,1,1,1,3,3,1,5,1,1,1,1,3,1,1,1,4,1,1,1,4,1,4,3,1,1,1,4,1,2,1,1,1,2,1,1,1,1,5,1,1,3,5,1,1,5,2,1,1,1,1,1,4,4,1,1,2,1,1,1,4,1,1,1,1,5,3,1,1,1,5,1,1,1,4,1,4,1,1,1,5,1,1,3,2,2,1,1,1,4,1,3,1,1,1,2,1,3,1,1,1,1,4,1,1,1,1,2,1,4,1,1,1,1,1,4,1,1,2,4,2,1,2,3,1,3,1,1,2,1,1,1,3,1,1,3,1,1,4,1,3,1,1,2,1,1,1,4,1,1,3,1,1,5,1,1,3,1,1,1,1,5,1,1,1,1,1,2,3,4,1,1,1,1,1,2,1,1,1,1,1,1,1,3,2,2,1,3,5,1,1,4,4,1,3,4,1,2,4,1,1,3,1
    )
    val ages = LongArray(9)
    input.forEach(Consumer { l: Int? -> ages[l!!]++ })
    for (i in 0 until 256) {
        val zero = ages[0]
        for (j in 0..7) {
            ages[j] = ages[j + 1]
        }
        ages[6] += zero
        ages[8] = zero
        println("Day: " + (i + 1) + ", Size: " + Arrays.stream(ages).sum())
    }
}
