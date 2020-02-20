package com.jjurm.hashcode20

typealias Id = Int
typealias Days = Long
typealias Score = Long

class Book(
    val id: Id,
    val score: Score
) : Comparable<Book> {
    //val inLibraries = HashSet<Library>()
    var used = false

    override operator fun compareTo(other: Book) = (score - other.score).toInt()

    override fun equals(other: Any?) = id == (other as Book).id
    override fun hashCode() = id
    override fun toString(): String {
        return "Book(id=$id, score=$score, used=$used)"
    }
}

class Library(
    val id: Id,
    val signup: Days,
    val rate: Int,
    val books: List<Book>
) {
    override fun equals(other: Any?) = id == (other as Library).id
    override fun hashCode() = id


    fun maxAddedScore(remainingDays: Days): Score {
        var remainingBooks = (remainingDays - signup) * rate

        var sumScore: Score = 0L
        val iterator = books.iterator()
        while (remainingBooks > 0 && iterator.hasNext()) {
            val book = iterator.next()
            if (book.used) continue
            sumScore += book.score
            remainingBooks -= 1
        }
        return sumScore
    }

    fun getTopBooks(remainingDays: Days): List<Book> {
        val remainingBooks = (remainingDays - signup) * rate
        val toScan = mutableListOf<Book>()

        val iterator = books.iterator()
        while (toScan.size < remainingBooks && iterator.hasNext()) {
            val book = iterator.next()
            if (book.used) continue
            toScan.add(book)
        }
        return toScan
    }

    override fun toString(): String {
        return "Library(id=$id, signup=$signup, rate=$rate)"
    }
}


