package reflections

import annotations.*
import kotlinx.io.Reader
import org.jetbrains.kotlin.container.ClassInfo
import java.lang.IllegalArgumentException
import java.time.Instant
import java.util.*
import kotlin.reflect.KAnnotatedElement
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties

data class Soldier(
        @JsonName("alias") val firstName: String,
        @JsonExclude val age : Int? = null,
        @DeserializeInterface(CompanyImpl::class) val company: Company, // Controls the deserialization of properties that have an interface type
        @CustomSerializer(DateSerializer::class) val birthDate: Date) {

    var counter = 0
    val counterIm = 0

    fun printAge() = println(this.age)

    fun printInput(input: Int) = println(input)
}

data class Author(val name: String)
data class Book(val title: String, val author: Author)

// Basic reflection concepts
fun main(args: Array<String>) {

    val soldierOfFortune : Soldier = Soldier("Panos", 35, CompanyImpl("unisystems"), Date.from(Instant.now()))

    var soldier : KClass<Soldier>     = Soldier::class;
    var javaClass : Class<Soldier>    = Soldier::class.java;
    val javaClass1: Class<Soldier>    = soldierOfFortune.javaClass
    val kotlinClass : KClass<Soldier> = javaClass1.kotlin // Returns an instance of KClass<Soldier>

    println(soldier)
    println(javaClass)
    println(javaClass1)
    println(kotlinClass)
    kotlinClass.memberProperties.forEach { println(it.name) }

    val callableFunc = Soldier::printAge     // ::printAge syntax is an instance of the KFunction class from the reflection API
    callableFunc.call(soldierOfFortune)                             // demonstrates how you can use CALL to call a function through reflection
    callableFunc.invoke(soldierOfFortune)

    val callablePrintFunc  = Soldier::printInput
    callablePrintFunc.call(soldierOfFortune, 44)                    // demonstrates how you can use CALL to call a function through reflection
    callablePrintFunc.invoke(soldierOfFortune, 44)

    val callableGlobalFunc  = ::printGlobal
    callableGlobalFunc.call(45)                             // demonstrates how you can use CALL to call a function through reflection
    callableGlobalFunc.invoke(45)

    val sumFunc = ::sum
    println(sumFunc(1,3) + sumFunc.call(1, 3) + sumFunc.invoke(1,3))

    var counterGlobal = ::counter
    val counterGlobalIm = ::counterIm

    var solCounter = Soldier::counter
    val solCounterIm = Soldier::counterIm

    println(counterGlobal.invoke())
    println(counterGlobal.call())
    println(counterGlobal.get())
    counterGlobal.setter.call(21) // Calls a setter through reflection, passing 21 as an argument
    println(counterGlobal.invoke())
    println(counterGlobal.call())
    println(counterGlobal.get())         // Obtains a property value by calling 'get'

    println(counterGlobalIm.invoke())
    println(counterGlobalIm.call())
    println(counterGlobalIm.get())

    println("---------------------------------")
    println(solCounter.invoke(soldierOfFortune))
    println(solCounter.call(soldierOfFortune))
    println(solCounter.get(soldierOfFortune))
    solCounter.set(soldierOfFortune, 12)
    println(solCounter.invoke(soldierOfFortune))
    println(solCounter.call(soldierOfFortune))
    println(solCounter.get(soldierOfFortune))

    println(solCounterIm.invoke(soldierOfFortune))
    println(solCounterIm.call(soldierOfFortune))
    println(solCounterIm.get(soldierOfFortune))

    val bookJson = """{"title":"Catch","author":{"name":"Panos"}}"""
}

fun printGlobal(input: Int) = println(input)

fun sum(x1:Int,x2:Int) = x1 + x2

var counter = 0
val counterIm = 0


private fun StringBuilder.serializeObject(obj: Any)  {
    val kClass = obj.javaClass.kotlin
    val properties = kClass.memberProperties.filter { it.findAnnotation<JsonExclude>() == null }
    properties.joinToStringBuilder(this,
            prefix = "{", postfix = "}") {
      serializeProperty(it, obj)
    }
}

fun StringBuilder.serializeProperty(prop: KProperty1<Any, *>, obj: Any) {
    val name = prop.findAnnotation<JsonName>()?.name ?: prop.name
    serializeString(name)
    append(":")
    val value = prop.get(obj)
    val jsonValue = prop.getSerializer()?.toJsonValue(value) ?: value
    serializePropertyValue(jsonValue)
}

fun serialize(obj: Any) : String = buildString { serializeObject(obj) } // buildString creates a StringBuilder and lets us fill it with content in a lambda.


// (setup: DrawerBuilderKt.() -> Unit = {}) In this statement we can see the power of kotlin higher order functions.
// Small intro of Higher order functions :- It is a function that takes functions as parameters, or returns a function.
// So here setup param is a function which return Nothing or Unit(Same as Void in java).
// DrawerBuilderKt.() means that the function can be invoked using object of DrawerBuilderKt class.
// = {} means that setup parameter is optional. So the function takes no parameters and return nothing.

inline fun <reified T> KAnnotatedElement.findAnnotation() : T? = annotations.filterIsInstance<T>().firstOrNull()

fun KProperty<*>.getSerializer() : ValueSerializer<Any?>? {
    val customSerializerAnn = findAnnotation<CustomSerializer>() ?: return null
    val serializerClass=customSerializerAnn.serializerClass
    val valueSerializer = serializerClass.objectInstance ?: serializerClass.createInstance()
    return valueSerializer as ValueSerializer<Any?>
}

private fun StringBuilder.serializeString(s: String) {
    append('\"')
    s.forEach { append(it.escape()) }
    append('\"')
}

private fun StringBuilder.serializeList(data: List<Any?>) {
    data.joinToStringBuilder(this, prefix = "[", postfix = "]") {
        serializePropertyValue(it)
    }
}

private fun StringBuilder.serializePropertyValue(value: Any?) {
    when (value) {
        null -> append("null")
        is String -> serializeString(value)
        is Number, is Boolean -> append(value.toString())
        is List<*> -> serializeList(value)
        else -> serializeObject(value)
    }
}

private fun Char.escape(): Any =
        when (this) {
            '\\' -> "\\\\"
            '\"' -> "\\\""
            '\b' -> "\\b"
            '\u000C' -> "\\f"
            '\n' -> "\\n"
            '\r' -> "\\r"
            '\t' -> "\\t"
            else -> this
        }

fun <T> Iterable<T>.joinToStringBuilder(stringBuilder: StringBuilder, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((T) -> Unit)? = null): StringBuilder {
    return joinTo(stringBuilder, separator, prefix, postfix, limit, truncated) {
        if (transform == null) return@joinTo it.toString()
        transform(it)
        ""
    }
}

object BooleanSerializer : ValueSerializer<Boolean> {
    override fun fromJsonValue(jsonValue: Any?): Boolean {
        if(jsonValue !is Boolean) throw IllegalArgumentException("Illegal")
        return jsonValue
    }

    override fun toJsonValue(value: Boolean) : Boolean = value
}
