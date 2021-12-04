import java.io.File
import java.io.InputStream

object Day3 {
    val inputStream: InputStream = File("src","day3.txt").inputStream()
    val lineList = mutableListOf<String>()

    @JvmStatic
    fun main(args: Array<String>) {
        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }
        println(lineList)
        val size = lineList.size
        val max = lineList.map {
            it.toCharArray().toList().map { it.digitToInt() }
        }.reduce { acc, ints ->
            ints.mapIndexed { index, i -> i + acc[index] }
        }.map{
            if(it>size-it) 1 else 0
        }

        val min = max.map { if(it == 0) 1 else 0 }
        val gamma = max.joinToString("").toInt(2)
        val epsilon = min.joinToString("").toInt(2)
        val res = gamma * epsilon


        println(size)
        println(gamma)
        println(epsilon)
        println(res)
    }
}

object Day3Part2 {
    val inputStream: InputStream = File("src","day3.txt").inputStream()
    val lineList = mutableListOf<String>()

    @JvmStatic
    fun main(args: Array<String>) {
        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }
        println(lineList)
        val digits = lineList.map {
            it.toCharArray().toList().map { c -> c.digitToInt() }
        }

        val oxigeno = funOx(digits,0)
        val co2 = funCO2(digits,0)

        println(digits)
        println(oxigeno)
        println(co2)

        val a = oxigeno.joinToString("").toInt(2)
        val b = co2.joinToString("").toInt(2)
        val res = a * b
        println(res)
    }

    private fun funCO2(digits: List<List<Int>>, i: Int): List<Int> {
        if(digits.size == 1) return digits.first()
        if(i >= digits.first().size) return digits.first()
        val max = digits.sumOf { it[i] }
        val filtro = if (max >= digits.size-max) 0 else 1
        val filtrados = digits.filter { it[i] == filtro }
        return funCO2(filtrados,i+1)
    }

    private fun funOx(digits: List<List<Int>>, i: Int): List<Int> {
        if(digits.size == 1) return digits.first()
        if(i >= digits.first().size) return digits.first()
        val max = digits.sumOf { it[i] }
        val filtro = if (max >= digits.size-max) 1 else 0
        val filtrados = digits.filter { it[i] == filtro }
        return funOx(filtrados,i+1)
    }
}