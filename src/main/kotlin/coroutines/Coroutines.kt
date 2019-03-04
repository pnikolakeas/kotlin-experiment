package coroutines

import javaslang.Function0
import kotlinx.coroutines.*
import java.lang.StringBuilder
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals
import kotlin.test.assertTrue

interface Callback<T> {
    fun onComplete(result: T)
    fun onException(e: Exception?)
}

suspend fun <T> awaitCallback(block: (Callback<T>) -> Unit) : T =
        suspendCancellableCoroutine {cancellableContinuation: CancellableContinuation<T> ->
            block(object : Callback<T> {
                override fun onComplete(result: T) = cancellableContinuation.resume(result)
                override fun onException(e: Exception?) {
                    e?.let { cancellableContinuation.resumeWithException(it) }
                }
            })
        }

fun test1(a: Int, callback: Callback<String>) {
    Thread.sleep(300)
    callback.onComplete(a.toString())
}

fun test2(a: String, callback: Callback<String>) {
    Thread.sleep(300)
    callback.onComplete(a)
}

fun test3(a: Int, b: Int, callback: Callback<Int>) {
    Thread.sleep(300)
    callback.onComplete(a + b)
}

fun <A, T> toSuspendFunction (fn: (A, Callback<T>) -> Unit): suspend (A)-> T = {
    a: A -> awaitCallback { fn(a, it) }
}

fun <A, B, T> toSuspendFunction (fn: (A, B, Callback<T>) -> Unit): suspend (A, B)-> T = {
    a: A, b: B -> awaitCallback { fn(a, b, it) }
}

fun main(args: Array<String>) = runBlocking {

    val testResult1: String = awaitCallback { it: Callback<String> -> test1(20, it) }
    println(testResult1)

    val testResult2: String = awaitCallback { it: Callback<String> -> test2("test", it) }
    println(testResult2)

    val testResult3: Int    = awaitCallback { it: Callback<Int>    -> test3(5, 3, it) }
    println(testResult3)

    val testResult4: String = toSuspendFunction(::test2)("test")
    println(testResult4)

    val testResult5: Int    = toSuspendFunction(::test3) (1,2)
    println(testResult5)

    runBlocking<Unit> {
        // given
        val counter = AtomicInteger(0)
        val numberOfCoroutines = 100_000

        // when
        val jobs = List(numberOfCoroutines) {
            launch () {
                delay(10L)
                counter.incrementAndGet()
            }
        }
        jobs.forEach { it.join() }

        // then
        assertEquals(counter.get(), numberOfCoroutines)
        println("Number of coroutines executed: $counter")
    }

    runBlocking<Unit> {
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("Some expensive computation $i ...")
                delay(500L)
            }
        }
    }

    runBlocking<Unit> {
        val delay = 2000L
        val time = measureTimeMillis {
            // given
            val one = async() {
                someExpensiveComputation(delay)
            }
            val two = async() {
                someExpensiveComputation(delay)
            }

            // when
            runBlocking {
                one.await()
                two.await()
            }
        }

        // then
        assertTrue (time < delay * 2)
    }


    val listOfStrings = listOf("Alpha", "Beta")
    println(listOfStrings.joinToString2(separator = "#", prefix = "|", postfix = "|", transform = {it.toString()}))
    println(listOfStrings.joinToString2())
    println(listOfStrings.joinToString2 { it.toUpperCase() })

    val callback: (() -> Unit)? = { println("No parameter lambda") }
    val function0: Function0<Unit>? = Function0 { println("Function0 instantiation") }
    foo(callback)
    foo(function0)

    val calculatorEx =getShippingCostCalculator(Delivery.EXPEDITED)       // Stores the returned function in a variable
    val calculatorSt =getShippingCostCalculator(Delivery.STANDARD)        // Stores the returned function in a variable
    println("Shipping Cost - EXP: ${calculatorEx(Order(3))}")   // Invokes the returned function
    println("Shipping Cost - STAND: ${calculatorSt(Order(3))}") // Invokes the returned function

    var contacts = listOf(Person("Dmitry", "Jemerov", "123-4567"), Person("Svetlana", "Isakova", null))
    val contactListFilters: ContactListFilters = ContactListFilters()
    with(contactListFilters) {
        this.prefix = "Dm"
        this.onlyWithPhoneNumber = true
    }
    println(contacts.filter(contactListFilters.getPredicate()))

    val log = listOf(
            SiteVisit("/", 34.0, OS.WINDOWS),
            SiteVisit("/", 22.0, OS.MAC),
            SiteVisit("/login", 12.0, OS.WINDOWS),
            SiteVisit("/signup", 8.0, OS.IOS),
            SiteVisit("/", 16.3, OS.ANDROID)
    )

    val atwu = log.averageDurationFor({it.os == OS.WINDOWS})
    println("Average time of Windows users: $atwu")
    val atmu = log.averageDurationFor({it.os == OS.MAC})
    println("Average time of MAC users: $atmu")
    val averageMobileDuration_ = log.averageDurationFor({it.os in setOf(OS.ANDROID, OS.IOS)})
    val averageMobileDuration = log.filter { it.os in setOf(OS.ANDROID, OS.IOS) }.map(SiteVisit::duration).average() // Method reference
    val averageMobileDuration1 = log.filter { it.os in setOf(OS.ANDROID, OS.IOS) }.map{it.duration}.average()        // Normal map filtering with lambda
    println("Average time of Mobile users: $averageMobileDuration_")
    println("Average time of Mobile users: $averageMobileDuration")
    println("Average time of Mobile users: $averageMobileDuration1")
}

fun someExpensiveComputation(delay: Long): Unit {
    println("InsideSomeExpensiveComputation")
    Thread.sleep(delay)
}

/**
 * You now know that you can pass a lambda to specify how values are converted into strings.
 * Nullable Function Type as Parameter
 */
fun <T> Collection<T>.joinToString1(separator: String = ", ", prefix: String = "", postfix: String = "", transform: ((T) -> String)? = null) : String { // 1. Declares a nullable parameter of a function type
    val sb = StringBuilder(prefix)
    for((index, element) in this.withIndex()) {
        if(index > 0)
            sb.append(prefix)
        sb.append(transform?.invoke(element) ?: element.toString()) // 2. Uses the safe-call syntax to call the function
    }                                                               // 3. Uses the Elvis operator to handle the case when a callback wasn’t specified
    sb.append(postfix)
    return sb.toString()
}

/**
 * You now know that you can pass a lambda to specify how values are converted into strings.
 * Function Type as Parameter
 */
fun <T> Collection<T>.joinToString2(separator: String = ", ", prefix: String = "", postfix: String = "", transform: (T) -> String = {it.toString()} ) : String {
    val sb = StringBuilder(prefix)
    for((index, element) in this.withIndex()) {
        if(index > 0)
            sb.append(prefix)
        sb.append(transform(element))
    }
    sb.append(postfix)
    return sb.toString()
}

// Nullable Function Type
fun foo(callback: (() -> Unit)?) {
    callback?.invoke()
}

// Use of special Function0 type
fun foo(callback: Function0<Unit>?) {
    callback?.apply()
}

enum class Delivery {
    STANDARD,
    EXPEDITED
}

class Order(val itemCount: Int)

fun getShippingCostCalculator(delivery: Delivery) : (Order) -> Double {
    when(delivery) {
        Delivery.EXPEDITED -> return { order -> 6 + order.itemCount * 2.1 }
        Delivery.STANDARD -> return { order -> order.itemCount * 1.2 }
    }
}

data class Person(val firstName: String, val lastName: String, var phoneNumber: String?)

class ContactListFilters {

    var prefix: String = ""
    var onlyWithPhoneNumber: Boolean = false

    fun getPredicate(): (Person) -> Boolean {
        val startsWithPrefix = { p: Person -> p.firstName.startsWith(prefix) || p.lastName.startsWith(prefix) }
        if (!onlyWithPhoneNumber) {
            return startsWithPrefix
        }
        return { startsWithPrefix(it) && it.phoneNumber != null }
    }
}

data class
SiteVisit( val
           path: String,
           val duration: Double,
           val os: OS
)
enum class OS { WINDOWS, LINUX, MAC, IOS, ANDROID }

fun List<SiteVisit>.averageDurationFor(predicate: (SiteVisit) -> Boolean) =
        this.filter(predicate)
                .map(SiteVisit::duration)
                .average()
