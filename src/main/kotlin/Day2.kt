fun main() {
    val list = readPairs("src/main/resources/day2.txt")
    println(diveDeeper(list))
}

fun dive(pairs: List<Pair<String, Int>>): Int {
    var depth = 0
    var horizontal = 0

    pairs.forEach {
        when(it.first){
            "forward" -> horizontal+=it.second
            "up" -> depth-=it.second
            "down" -> depth+=it.second
        }
    }
    return depth*horizontal
}

fun diveDeeper(pairs: List<Pair<String, Int>>): Int{
    var depth = 0
    var horizontal = 0
    var aim = 0

    pairs.forEach {
        when(it.first){
            "forward" -> {
                horizontal+=it.second
                depth+=it.second*aim
            }
            "up" -> aim-=it.second
            "down" -> aim+=it.second
        }
    }
    return depth*horizontal
}


