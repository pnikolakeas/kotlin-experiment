package books

data class Book(val title: String, val authors: List<String>) {}

fun main(args: Array<String>) {
    val books = listOf(Book("melda", listOf("boukovski","ntostogievski")),
                       Book("zelda", listOf("papathanasiou","reppas")),
                       Book("gelda", listOf("papathanasiou","reppas")))
    println(books.flatMap { it.authors }.toSet())

    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() })
}