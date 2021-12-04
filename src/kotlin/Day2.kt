import java.io.File
import java.io.InputStream

object Day2 {
    val inputStream: InputStream = File("src","day2.txt").inputStream()
    val lineList = mutableListOf<String>()

    @JvmStatic
    fun main(args: Array<String>) {
        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }
        println(lineList)
        val points = lineList.map { it.split(" ") }
            .map { Pair(it[0],it[1].toInt()) }
            .fold( Pair(0,0) ){ acc,pair ->
                when(pair.first){
                    "forward" -> Pair(acc.first + pair.second, acc.second)
                    "down" -> Pair(acc.first, acc.second  + pair.second)
                    "up" -> Pair(acc.first, acc.second - pair.second)
                    else -> acc
                }
            }

        println(points)
        val res = points.first * points.second
        println(res)
    }
}

object Day2Part2 {
    val inputStream: InputStream = File("src","day2.txt").inputStream()
    val lineList = mutableListOf<String>()

    @JvmStatic
    fun main(args: Array<String>) {
        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }
        println(lineList)
        val points = lineList.map { it.split(" ") }
            .map { Pair(it[0],it[1].toInt()) }
            .fold( Triple(0,0,0) ){ acc, pair ->
                when(pair.first){
                    "down" -> Triple(acc.first, acc.second, acc.third + pair.second)
                    "up" -> Triple(acc.first, acc.second, acc.third - pair.second)
                    "forward" -> Triple(acc.first + pair.second, acc.second + (acc.third * pair.second), acc.third)
                    /*
                    "forward" -> Pair(acc.first + pair.second, acc.second)
                    "down" -> Pair(acc.first, acc.second  + pair.second)
                    "up" -> Pair(acc.first, acc.second - pair.second)*/
                    else -> acc
                }
            }

        println(points)
        val res = points.first * points.second
        println(res)
    }
}