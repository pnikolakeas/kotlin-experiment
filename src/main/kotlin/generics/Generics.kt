package generics

interface ListP<T> {
    operator fun get(index: Int) : T? {
        return null
    }
}

class StringList : ListP<String> {
    override operator fun get(index: Int) : String {
        return ""
    }
}

class ArrayListP<K> : ListP<K> {
    override operator fun get(index: Int) : K? {
        return null
    }
}

fun main(args: Array<String>) {

    val authors = listOf("Panos", "Giorgos") // The compiler infers that you are creating a List<String> from the arguments
    val readers = listOf("Nikos", "Giorgos", "Mixalis")

    // Two equivalent declarations for an empty List<String>. No arguments for the type to be inferred.
    val readers1: List<String> = mutableListOf()
    val readers2 = mutableListOf<String>()

    val letters = ('a'..'z').toList()
    println(letters.slice(0..4))         // Compiler infers the type argument
    println(letters.slice<Char>(10..13)) // Specifies the type argument explicitly

    println(readers.filter { it !in authors })  // The compiler infers that the auto-generated it is String

    println(listOf(1,2,3,4).penultimate)
    println(listOf(1,2,3,4).penultimates)

    println(listOf(1, 2, 3).sum())

    println(oneHalf(3))

    println(max("Panos", "Giorgos"))

    val helloWorld = StringBuilder("Hello World")
    println(ensureTrailingPeriod(helloWorld))

    val nullableStringProcessor  = Processor<String?>()
    val nullableStringProcessor1 = Processor1<String>()

    nullableStringProcessor.process(null)
    nullableStringProcessor1.process("1")
}

// Extension Property that return the element before the last one with Type parameter => This type parameter T is inferred to be Int.
val <T> List<T>.penultimate: T
    get() = this[size-2]

// Extension Property that return the element before the last one with Type parameter => This type parameter T is inferred to be Int.
val <T> List<T>.penultimates: T
    get() = this.get(size - 2)

fun <T : Number> oneHalf(value : T) : Double { // Specifies Number as the type parameter bound
    return value.toDouble() / 2.0
}

fun <T : Comparable<T>> max(first: T, second: T) : T { // The arguments of this function must be comparable elements.
    return if (first > second) first else second
}

fun <T> ensureTrailingPeriod(seq: T) : T where T : CharSequence, T : Appendable { // List of type parameter constraints
    if (!seq.endsWith('.')) {
        seq.append('.')
    }
    return seq
}

class Processor<T> { // Can take nullable types!
    fun process(value: T) {
        value.hashCode()
    }
}

class Processor1<T : Any> { // Cannot take nullable types! Upper bound specifically defined
    fun process(value: T) {
        value.hashCode()
    }
}