package dzone

import kotlin.test.assertEquals


public fun main(args: Array<String>) {

    ifDemo1("Panagiotis")
    ifDemo1(null)

    ifDemo2("Panagiotis")
    ifDemo2(null)

    // Defines a Tuple2
    val tup2 = Tuple2<String, String>("elem1", "elem2")
    assertEquals(tup2._1, "elem1")
    assertEquals(tup2._2, "elem2")

    // Defines a Tuple3
    val tup3 = Tuple("elem1", "elem2", "elem3")
    val (e1,e2,e3) = tup3
    assertEquals(e1, "elem1")
    assertEquals(e2, "elem2")
    assertEquals(e3, "elem3")

    val tup4 = Tuple("elem1", "elem2", "elem3", "elem4")
    val(_e1,_e2,_e3,_e4) = tup4
    assertEquals(_e1, "elem1")
    assertEquals(_e2, "elem2")
    assertEquals(_e3, "elem3")
    assertEquals(_e4, "elem4")
}

fun ifDemo1(name:String?) {
    val displayName = if(name != null) name else "Mysterious Stranger"
    println("Hi $displayName")
}

fun ifDemo2(name:String?) {
    val displayName = name ?: "Mysterious Stranger"
    println("Hi $displayName")
}

data class Tuple2<out A, out B>(val _1: A, val _2: B)
data class Tuple3<out A, out B, out C>(val _1: A, val _2: B, val _3: C)
data class Tuple4<out A, out B, out C, out D>(val _1: A, val _2: B, val _3: C, val _4:D)
data class Tuple5<out A, out B, out C, out D, out E>(val _1: A, val _2: B, val _3: C, val _4:D, val _5:E)

object Tuple {
    operator fun <A, B> invoke(_1: A,_2: B): Tuple2<A,B> = Tuple2(_1, _2)
    operator fun <A, B, C> invoke(_1: A,_2: B,_3:C): Tuple3<A,B,C> = Tuple3(_1, _2, _3)
    operator fun <A, B, C, D> invoke(_1: A,_2: B,_3:C, _4:D): Tuple4<A,B,C,D> = Tuple4(_1, _2, _3,_4)
    operator fun <A, B, C, D, E> invoke(_1: A,_2: B,_3:C, _4:D, _5:E): Tuple5<A,B,C,D,E> = Tuple5(_1, _2, _3,_4,_5)
}