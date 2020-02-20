package com.jjurm.hashcode20

import java.lang.RuntimeException

val remainingLibraries = libraries!!.toHashSet()

fun solution1(): Output {
    var remainingDays = D!!

    val out: Output = mutableListOf()
    var wasEmpty = false

    while (remainingDays > 0) {
        val nextLib = getNextLibrary(remainingDays)
        if (nextLib == null) {
            println("Used all libraries")
            break
        }
        val scanBooks = nextLib.getTopBooks(remainingDays)

        remainingLibraries.remove(nextLib)

        if (scanBooks.isEmpty()) {
            wasEmpty = true
            //print("Got $nextLib with no books to scan")
        } else {
            if (wasEmpty) throw RuntimeException("Was empty!")
            remainingDays += nextLib.signup
            scanBooks.forEach { it.used = true }
            out.add(nextLib to scanBooks)
        }

    }

    return out
}


fun getNextLibrary(remainingDays: Days): Library? {

    val library = remainingLibraries.maxBy { library ->
        library.maxAddedScore(remainingDays)
    }

    return library

}
