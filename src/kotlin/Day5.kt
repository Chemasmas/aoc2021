import java.io.File
import java.io.InputStream

class Mapa(instrucciones: MutableList<MutableList<Int>>){

    private var res: Int
    var m:MutableList<MutableList<Int>>

    init {
        val max = instrucciones.flatten().maxOrNull() ?: 9

        m = (0..max).map {
            MutableList(max+1){0}
        }.toMutableList()

        instrucciones.map{ linea ->
            if(linea[0] == linea[2]){
                // Vertical
                vertical( linea[0],linea[1],linea[3])
            }
            if(linea[1] == linea[3]){
                // Horizontal
                horizontal( linea[1],linea[0],linea[2])
            }
        }

        res = m.flatten().count { it > 1 }
    }

    private fun vertical(pivote: Int, s: Int, s1: Int) {
        val base = if(s <= s1) s else s1
        val fin = if(s > s1) s else s1
        (base..fin).forEach {
            m[it][pivote] = m[it][pivote] + 1
        }
    }

    private fun horizontal(pivote: Int, s: Int, s1: Int) {
        val base = if(s <= s1) s else s1
        val fin = if(s > s1) s else s1
        (base..fin).forEach {
            m[pivote][it] = m[pivote][it] + 1
        }
    }

    override fun toString(): String {
        return "Mapa(\n${
            m.joinToString("\n")
        }\n) RES: \n$res"
    }


}

class Mapa2(instrucciones: MutableList<MutableList<Int>>){

    private var res: Int
    var m:MutableList<MutableList<Int>>

    init {
        val max = instrucciones.flatten().maxOrNull() ?: 9

        m = (0..max).map {
            MutableList(max+1){0}
        }.toMutableList()

        instrucciones.map{ linea ->
            if(linea[0] == linea[2]){
                // Vertical
                vertical( linea[0],linea[1],linea[3])
            }
            if(linea[1] == linea[3]){
                // Horizontal
                horizontal( linea[1],linea[0],linea[2])
            }
            if(linea[1] != linea[3] && linea[0] != linea[2]){
                //diagonal
                diagonal(linea[0],linea[1],linea[2],linea[3])
            }
        }

        res = m.flatten().count { it > 1 }
    }

    private fun diagonal(x1: Int, y1: Int, x2: Int, y2: Int) {
        when{
            x1 <= x2 && y1 <= y2 -> {
                var tempx = x1
                var tempy = y1
                while (tempx<=x2){
                    m[tempy][tempx] = m[tempy][tempx] + 1
                    tempx++
                    tempy++
                }
            }
            x1 > x2 && y1 > y2 -> {
                var tempx = x1
                var tempy = y1
                while (tempx>=x2){
                    m[tempy][tempx] = m[tempy][tempx] + 1
                    tempx--
                    tempy--
                }
            }
            x1 <= x2 && y1 > y2 -> {
                var tempx = x1
                var tempy = y1
                while (tempx<=x2){
                    m[tempy][tempx] = m[tempy][tempx] + 1
                    tempx++
                    tempy--
                }
            }
            else -> {
            //x1 > x2 && y1 <= y2 -> {
                var tempx = x1
                var tempy = y1
                while (tempx>=x2){
                    m[tempy][tempx] = m[tempy][tempx] + 1
                    tempx--
                    tempy++
                }
            }
            //x1 > x2 && y1 < y2 -> (x1..x2).forEach {x -> (y1..y2).forEach { y-> if(x == y) m[y][x] = m[y][x] + 1 } }
        }

        //m[baseY][baseX] = m[baseY][baseX] + 1

        /*
        (baseX..finX).forEach { x->
            (baseY..finY).forEach { y->
                m[y][x] = m[y][x] + 1
            }
        }
         */
    }

    private fun vertical(pivote: Int, s: Int, s1: Int) {
        val base = if(s <= s1) s else s1
        val fin = if(s > s1) s else s1
        (base..fin).forEach {
            m[it][pivote] = m[it][pivote] + 1
        }
    }

    private fun horizontal(pivote: Int, s: Int, s1: Int) {
        val base = if(s <= s1) s else s1
        val fin = if(s > s1) s else s1
        (base..fin).forEach {
            m[pivote][it] = m[pivote][it] + 1
        }
    }

    override fun toString(): String {
        return "Mapa(\n${
            m.joinToString("\n")
        }\n) RES: \n$res"
    }


}

object Day5 {
    //var tableros: MutableList<List<String>> = mutableListOf()
    //var tablerosC: MutableList<Tablero> = mutableListOf()
    val inputStream: InputStream = File("src","day5.txt").inputStream()
    val numeros = "\\d+".toRegex()
    var instrucciones: MutableList<MutableList<Int>> = mutableListOf()

    @JvmStatic
    fun main(args: Array<String>) {


        val reader = inputStream.bufferedReader()
        reader.useLines { lines ->
            //0,9 -> 5,9
            lines.forEach { line ->
                val a = numeros.findAll(line).map { r -> r.value.toInt() }.toMutableList()
                instrucciones.add(a)
            }
        }
        println(instrucciones)
        val mapa = Mapa(instrucciones)
        println(mapa)
        val mapa2 = Mapa2(instrucciones)
        println(mapa2)
    }
}

