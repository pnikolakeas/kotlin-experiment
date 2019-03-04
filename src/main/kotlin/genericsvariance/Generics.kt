package genericsvariance

import com.sun.xml.internal.bind.v2.schemagen.episode.Klass
import java.lang.IllegalArgumentException
import kotlin.random.Random
import kotlin.reflect.KClass


// MutableList<in T>  => MutableList<? super T>
// MutableList<out T> => MutableList<? extends T>

fun main(args: Array<String>) {

    var list1: List<String> = listOf("Panagiotis", "Giorgos", "Mixahl")
    var list2: List<Int>    = listOf(1, 2, 3)
    var set1: Set<String>   = setOf("Panagiotis", "Giorgos", "Mixahl")
    val strings: MutableList<String> = mutableListOf("Panagiotis", "Giorgos", "Mixahl")

    printContents(list1)
    printContents(list2)
    printContents(strings)

    addName(strings)

    val ints = mutableListOf(1, 2, 3)
    val anyItems = mutableListOf<Any>()

    val result1: MutableList<Any> = copyData2(ints, anyItems)
    printContents(result1)
    printContents1(result1)
    printContents2(result1)

    val result2: MutableList<Any> = copyData3(ints, anyItems)
    printContents(result2)
    printContents1(result2)
    printContents2(result2)

    val result3 = copyData4(ints, anyItems)
    printContents(result3)
    printContents1(result3)
    printContents2(result3)

    // MutableList<in T>  => MutableList<? super T>
    // MutableList<out T> => MutableList<? extends T>

    val list: MutableList<Any?> = mutableListOf('a', 1, "qwe")
    val chars = mutableListOf('a','b','c')
    val startProjList: MutableList<*> = if(Random.nextBoolean()) list else chars // Star projection
                                                                                 // You can use the star-projection syntax when the information about type arguments isn’t important:

    println(startProjList.first()) // It's safe to get the element
    // startProjList.add(42)       // It's not sage to add any element

    Validators.registerValidator(String::class, DefaultStringValidator)
    Validators.registerValidator(Int::class, DefaultIntValidator)

    println(Validators[String::class].validate("Panos"))
    println(Validators[String::class].validate(""))

    println(Validators[Int::class].validate(42))
    println(Validators[Int::class].validate(-1))

}

fun printContents(list: List<*>) {
    println(list.joinToString())
}

fun printContents1(list: List<Any?>) {
    println(list.joinToString())
}

fun <T> printContents2(list: List<T>) {
    println(list.joinToString())
}

fun addAnwser(list: MutableList<Any>) {
    list.add(42)
}

fun addName(list: MutableList<String>) {
    list.add("Korina")
}

open class Animal {
     open fun feed() {
        println("Animal is fed..")
    }
}

class Cat : Animal() {
    override fun feed() {
        println("Cat is fed..")
    }
    fun cleanLitter() {
        println("Cat is clear..")
    }
}

class Herd<T: Animal> { // The type parameter isn’t declared as covariant, with out
    val size: Int get() = TODO()
    operator fun get(i: Int): T {
        TODO()
    }
}

class HerdCovariant<out T: Animal> { // The type parameter is declared as covariant, Cat extends Animal, Herd<Cat> extends Herd<Animal> and can be used interchangeably
    val size: Int get() = TODO()
    operator fun get(i: Int): T {
        TODO()
    }
}

fun takeCareOfCats(cats: Herd<Cat>) {
    for(i in 0 until cats.size) {
        cats[i].cleanLitter()
    }
    feedAll(cats as Herd<Animal>) // Legal due to up casting
}

fun takeCareOfCats(cats: HerdCovariant<Cat>) {
    for(i in 0 until cats.size) {
        cats[i].cleanLitter()
    }
    feedAll(cats)               // Perfectly legal due to Type Covariant
}

fun feedAll(animals: Herd<Animal>) {
    for(i in 0 until animals.size) {
        animals[i].feed()
    }
}

fun feedAll(animals: HerdCovariant<Animal>) {
    for(i in 0 until animals.size) {
        animals[i].feed()
    }
}

fun <T> copyData1(source: MutableList<T>, destination: MutableList<T>): MutableList<T> {
    for(element in source) {
        destination.add(element)
    }
    return destination
}

fun <T : R, R> copyData2(source: MutableList<T>, destination: MutableList<R>): MutableList<R> {
    for(element in source) {
        destination.add(element)
    }
    return destination
}

fun <T> copyData3(source: MutableList<out T>, destination: MutableList<T>): MutableList<T> {
    for(element in source) {
        destination.add(element)
    }
    return destination
}

// MutableList<in T>  => MutableList<? super T>
// MutableList<out T> => MutableList<? extends T>
fun <T> copyData4(source: MutableList<T>, destination: MutableList<in T>): MutableList<in T> {
    for(element in source) {
        destination.add(element)
    }
    return destination
}



object Validators {

    private val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()

    fun <T: Any> registerValidator(kClass: KClass<T>, validator: FieldValidator<T>) {
        validators[kClass] = validator
    }

    operator fun <T: Any> get(kClass: KClass<T>) : FieldValidator<T> =
        validators[kClass] as? FieldValidator<T> ?: throw IllegalArgumentException("No validator for class: ${kClass.simpleName}")
}

object DefaultStringValidator : FieldValidator<String> {
    override fun validate(input: String): Boolean = input.isNotEmpty()
}

object DefaultIntValidator : FieldValidator<Int> {
    override fun validate(input: Int): Boolean = input >= 0
}

interface FieldValidator<in T> {
    fun validate(input: T) : Boolean
}