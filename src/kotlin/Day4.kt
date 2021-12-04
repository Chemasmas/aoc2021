import java.io.File
import java.io.InputStream

class Tablero(generador: List<String>){
    var puntos: Int = -1
    var tirada: Int = -1
    var turnos = 0
    fun jugar(partida: String) {
        run game@{
            partida.split(",").forEach {
                turnos++
                println(it)
                ficha(it)
                println(this)
                if(gano()) return@game
            }
        }
        calcularPuntaje()
        println("Turnos $turnos")
    }

    private fun calcularPuntaje() {
        val noMarcadas = t.flatten().filter { !it.second }.map { it.first.toInt() }.sum()
        puntos = tirada * noMarcadas
    }

    private fun gano(): Boolean {
        val todas = t.flatten()
        var res = false
        run busqueda@ {
            (0..4).forEach {
                val offsetV = 5*it
                val a = todas.slice(0+offsetV..4+offsetV)
                val b = todas.slice(listOf(0+it,5+it,10+it,15+it,20+it))
                //println(a)
                //println(b)
                res = a.all {par -> par.second } or b.all {par -> par.second }
                if(res) return@busqueda
            }
        }
        return res
    }

    private fun ficha(it: String) {
        var x = -1
        val y = t.indexOfFirst { lista1 ->
            x = lista1.indexOfFirst { par ->
                par.first == it
            }
            x > -1
        }
        if(x > -1 && y > -1){
            //Tirar ficha
            t[y][x] = t[y][x].copy(second = true)
            tirada = it.toInt()
        }
        //println("->$y,$x<-" )

    }

    override fun toString(): String {
        return "Tablero(\n${t.map { it.map { par -> par.first }.joinToString("\t") }.joinToString("\n")})\n" +
                "Fichas(\n" +
                "${t.map { it.map { 
                        par -> if(par.second) "✘" else par.first 
                }.joinToString("\t") }.joinToString("\n")})"
    }

    private var t: MutableList<MutableList<Pair<String, Boolean>>>

    init {
        t = generador
            .map { it.trim().replace("  "," ").split(" ")
                .map { n -> Pair(n,false) }.toMutableList()
            }.toMutableList()

    }



}

object Day4 {
    var tableros: MutableList<List<String>> = mutableListOf()
    var tablerosC: MutableList<Tablero> = mutableListOf()
    val inputStream: InputStream = File("src","day4.txt").inputStream()

    @JvmStatic
    fun main(args: Array<String>) {
        val reader = inputStream.bufferedReader()
        val partida = reader.readLine()

        println(partida)

        reader.useLines { lines ->
            lines.chunked(6).map { it.takeLast(5) }.forEach { tableros.add(it) }
        }
        tableros.forEach { tablerosC.add(Tablero(it,)) }
        tablerosC.forEach { it.jugar(partida) }
        tablerosC.sortBy { it.turnos }
        println(tablerosC.first().puntos)

        //PArte 2
        println(tablerosC.last().puntos)
    }
}

object Day4Part2 {
    val inputStream: InputStream = File("src\\main\\resources\\day3.txt").inputStream()
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
        /*
        val max = digits.reduce { acc, ints ->
            ints.mapIndexed { index, i -> i + acc[index] }
        }.map{
            if(it>size-it) 1 else 0
        }

        val min = max.map { if(it == 0) 1 else 0 }
        val oxigeno = funOx(max, digits,0)
        println(max)
        println(oxigeno)

        val gamma = max.joinToString("").toInt(2)
        val epsilon = min.joinToString("").toInt(2)
        val res = gamma * epsilon


        println(size)
        println(gamma)
        println(epsilon)
        println(res)
        */
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

    /*
    private fun funOx(max: List<Int>, lineList: List<List<Int>>, i: Int): List<Int> {
        if(i>max.size) return lineList.first()
        if (lineList.size == 1) return lineList.first()
        val filtrados = lineList.filter { it[i] == max[i]}
        return funOx(max, filtrados, i+1)
    }
     */
}