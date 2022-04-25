import java.io.File

data class Octopus(val energyLevel: Int, val flashed: Boolean)

private fun incrementEnergy(map: Array<Array<Octopus>>, i: Int, j: Int) {
    map[i][j] = Octopus(map[i][j].energyLevel + 1, map[i][j].flashed)
}

fun adjacentPositions(i: Int, j: Int): List<Pair<Int, Int>> {
    return listOf(
        Pair(i - 1, j),
        Pair(i + 1, j),
        Pair(i, j - 1),
        Pair(i, j + 1),
        Pair(i - 1, j - 1),
        Pair(i - 1, j + 1),
        Pair(i + 1, j - 1),
        Pair(i + 1, j + 1),
    ).filter {
        it.first in 0..9 && it.second in 0..9
    }
}

fun tryFlash(map: Array<Array<Octopus>>, i: Int, j: Int) {
    if (map[i][j].energyLevel > 9 && !map[i][j].flashed) {
        map[i][j] = Octopus(map[i][j].energyLevel, true)
        adjacentPositions(i, j).forEach { incrementEnergy(map, it.first, it.second) }
        adjacentPositions(i, j).forEach { tryFlash(map, it.first, it.second) }
    }
}

private fun step(map: Array<Array<Octopus>>): Int {
    for (i in map.indices) {
        for (j in map[i].indices) {
            incrementEnergy(map, i, j)
        }
    }
    for (i in map.indices) {
        for (j in map[i].indices) {
            tryFlash(map, i, j)
        }
    }
    var flashCount = 0
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j].flashed) {
                map[i][j] = Octopus(0, false)
                flashCount++
            }
        }
    }
    return flashCount
}

fun main() {
    val input = File("src/main/resources/day11.txt").readLines().toList()
    val map = Array(input.size) { i ->
        input[i].split("").filter { it.isNotEmpty() }.map { Octopus(it.toInt(), false) }.toTypedArray()
    }
    val octopusCount = map.size * map[0].size
    var step = 1
    var flashesAfter100Steps = 0
    do {
        val flashes = step(map)
        flashesAfter100Steps += flashes
        if (step == 100) {
            println("Result A: $flashesAfter100Steps")
        }
        if (flashes == octopusCount) {
            println("Result B: $step")
        }
        step += 1
    } while (step <= 100 || flashes != octopusCount)

}
