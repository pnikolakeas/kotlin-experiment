@file:JvmName("JMVJoinName")

package strings

import kotlin.collections.Collection

var opCount = 0
const val UNIX_LINE_SEPARATOR = "\n"

fun String.lastChar() : Char {
    return this.get(this.length - 1);
}

fun String.lastChar1() : Char = this.get(this.length - 1);
fun String.lastChar2() : Char = get(length - 1);

fun performOperation() {
    opCount++;
}

fun reportOperation() {
    println("Operation performed $opCount times")
}

// Extension Property
var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length -1, value)
    }

val String.lastChar : Char
    get() = this.get(length -1)

@JvmOverloads
fun <T> joinToString(collection: Collection<T>, prefix: String = "-#-", postfix: String = "-#-", separ: String =UNIX_LINE_SEPARATOR): String {

    val result = StringBuffer(prefix)

    for((index, element) in collection.withIndex()) {
        if(index > 0)
            result.append(separ)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

// Extension Function
@JvmOverloads
fun <T> Collection<T>.joinToStrings(prefix: String = "-#-", postfix: String = "-#-", separ: String = strings.UNIX_LINE_SEPARATOR): String {

    var result = StringBuffer(prefix)

    for((index, element) in this.withIndex()) {
        if(index > 0)
            result.append(separ)
        result.append(element)
    }

    result.append(postfix)
    return result.toString();
}