import java.io.File
import java.io.InputStream

object Day1 {
    val inputStream: InputStream = File("src","day1.txt").inputStream()
    val lineList = mutableListOf<Int>()

    @JvmStatic
    fun main(args: Array<String>) {
        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it.toInt())} }
        //println(lineList)
        var prevValue = lineList.first()
        val res = lineList.fold(0){ acc,elem -> if(elem > prevValue){ prevValue = elem; acc+1 } else { prevValue = elem; acc } }
        println(res)
    }
}

object Day1Part2 {
    val inputStream: InputStream = File("src","day1.txt").inputStream()
    val lineList = mutableListOf<Int>()

    @JvmStatic
    fun main(args: Array<String>) {
        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it.toInt())} }
        val windowSum = lineList.mapIndexed { index, i -> i + lineList.getOrElse(index + 1){0} + lineList.getOrElse(index + 2){0} }.dropLast(2)
        var prevValue = windowSum.first()
        val res = windowSum.fold(0){ acc,elem -> if(elem > prevValue){ prevValue = elem; acc+1 } else { prevValue = elem; acc } }
        println(res)
    }
}

