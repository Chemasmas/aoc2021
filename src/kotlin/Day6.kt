import java.io.File
import java.io.InputStream


object Day6 {
    private val input: MutableList<Int> = mutableListOf()
    val inputStream: InputStream = File("src","day6.txt").inputStream()
    val map = mutableMapOf(
        0 to 0L,
        1 to 0L,
        2 to 0L,
        3 to 0L,
        4 to 0L,
        5 to 0L,
        6 to 0L,
        7 to 0L,
        8 to 0L
    )


    @JvmStatic
    fun main(args: Array<String>) {


        val reader = inputStream.bufferedReader()
        input.addAll(
            reader.readLine().split(",").map { it.toInt() }.toMutableList()
        )


        //println(input)
        println(map)
        input.forEach {
            map[it] = (map[it] ?: 0) + 1
        }
        println(map)
        //repeat(5){
        //repeat(80){
        repeat(256){
            turno()
            println(map)
        }
        println(map)
        println(map.values)
        println(map.values.sum())
    }

    private fun turno() {
        val temp = (map[0] ?: 0 )

        (1..8).forEach {
            map[it-1] = map[it] ?: 0
        }
        map[8] = temp
        map[6] = (map[6] ?: 0) + (map[8] ?: 0)
    }
}

