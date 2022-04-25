import java.io.File

fun read(fileName: String): List<String> = File(fileName).readLines()

fun readPairs(fileName: String): List<Pair<String, Int>> {
    val listOfPairs = mutableListOf<Pair<String, Int>>()
    val list = File(fileName).readLines()
    list.forEach {
        val splitted = it.split(" ")
        listOfPairs.add(Pair(splitted[0], splitted[1].toInt()))
    }
    return listOfPairs
}

fun readArray(fileName: String): Array<CharArray>{
    val lines = File(fileName).readLines()
    val arrayOfArray = mutableListOf<CharArray>()
    lines.forEach {
        arrayOfArray.add(it.toCharArray())
    }
    return arrayOfArray.toTypedArray()
}

fun readInt2DArray(fileName: String): Array<IntArray>{
    val lines = File(fileName).readLines()
    val arrayOfArray = mutableListOf<IntArray>()
    lines.forEach {
        arrayOfArray.add(
            it.toCharArray().map { it.digitToInt() }.toIntArray()
        )
    }
    return arrayOfArray.toTypedArray()
}

fun readMatrices(fileName: String): Pair<List<Int>, List<Array<IntArray>>>{
    val lines = File(fileName).readLines()
    val listOfNumbers: List<Int> = lines[0].split(" ").map { it.toInt() }
    val length = lines.size
    val listOfBoards = mutableListOf<Array<IntArray>>()
    val tables = (2 until lines.size step 6).map { i ->  }

    return Pair(listOfNumbers, listOfBoards)

}

fun readLongList(fileName: String): List<Long> = File(fileName).readLines()[0].split(",").map { it.toLong() }

fun readIntList(fileName: String): List<Int> = File(fileName).readLines()[0].split(",").map { it.toInt() }
