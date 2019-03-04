package operatorsDestructing

data class NameComponents(val name: String, val extension: String)

fun main(args: Array<String>) {
    val fileName = "bible.txt"

    val (name, extension)  = splitFileName(fileName) // Uses the destructuring declaration syntax to unpack the class

    val(nameP, extensionP) = splitFileNamePair(fileName)

    println("Returning encapsulating variables from a method: Split ${fileName} -> name: ${name}, extension: ${extension}")
    println("Returning a Pair variable from a method: Split ${fileName} -> name: ${nameP}, extension: ${extensionP}")

}

fun <T> List<T>.toPair() : Pair<T, T> {
    if(this.size != 2) {
        throw IllegalArgumentException("List is not of length 2!")
    }
    return Pair(this[0], this[1])
}

fun splitFileName(fileName: String) : NameComponents {

    // val result = fileName.split('.', limit = 2)
    // return NameComponents(result[0], result[1])

    // EVEN BETTER
    val (name, extension) = fileName.split('.', limit=2)
    return NameComponents(name, extension)
}


fun splitFileNamePair(fileName: String) : Pair<String, String> {

    val p: List<String> = fileName.split('.', limit = 2)
    val pair: Pair<String, String> = p.toPair()
    return pair

}