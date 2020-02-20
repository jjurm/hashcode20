package com.jjurm.hashcode20

import fastjavaio.InputReader
import java.io.FileInputStream
import java.io.FileWriter
import java.io.PrintWriter

typealias Output = MutableList<Pair<Library, List<Book>>>

val files = listOf("a_example", "b_read_on", "c_incunabula", "d_tough_choices", "e_so_many_books", "f_libraries_of_the_world")


val startTime = System.currentTimeMillis()

var D: Long? = null
var libraries: List<Library>? = null

fun writeOutput(out: Output, toSolve: String) {
    //val writer = BufferedWriter(FileWriter("out.txt"))
    val writer = PrintWriter(FileWriter("out/${toSolve}_out.txt"))

    writer.println(out.size)
    out.forEach { (lib, books) ->
        writer.print(lib.id)
        writer.print(' ')
        writer.println(books.size)
        books.forEach { book ->
            writer.print(book.id)
            writer.print(' ')
        }
        writer.println()
    }

    writer.close()
}

fun main(args: Array<String>) {
    val toSolve = files[args[0].toInt()]

    val input = InputReader(FileInputStream("data/$toSolve.txt"))
    val B = input.nextInt() // books
    val L = input.nextInt() // libraries
    D = input.nextLong() // days
    val books = input.nextLongArray(B).mapIndexed { id, score -> Book(id, score) }
    libraries = (0 until L).map { id ->
        val Nj = input.nextInt()
        Library(id, input.nextLong(), input.nextInt(),
            input.nextIntArray(Nj).map { books[it] }.sorted()
        )
    }

    val out = solution1()
    writeOutput(out, toSolve)

    println("Took ${"%.1f".format((System.currentTimeMillis() - startTime) / 1000.0)} s")
}

