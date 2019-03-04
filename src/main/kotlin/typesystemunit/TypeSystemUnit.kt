package typesystemunit

import typesystemprimitive.Person

fun main(args: Array<String>) {

    val p : Person = Person("panos", 35)
    val p1 : Person = Person("korina")

    val age = p.age ?: fail("No age!")
    println(age)
    val age1 = p1.age ?: fail("No age!")
    println(age1)
}

fun fail(message: String) : Nothing {
    throw IllegalArgumentException(message)
}

interface Processor<T> {
    fun process() : T
}

class NoResultProcessor : Processor<Unit> { // As you can see the UNIT type is a full-fledged type and can be used as a type argument
    override fun process() {
        println("Do stuff")
    }
}