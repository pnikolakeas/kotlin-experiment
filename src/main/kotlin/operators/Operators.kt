package operators

import java.time.LocalDate


data class Rectangle(var upperLeft: Point, var lowerRight: Point) {

    operator fun contains(p: Point) : Boolean {

        return p.x in upperLeft.x until lowerRight.x &&
               p.y in upperLeft.y until lowerRight.y

    }

}

// Overloading arithmetic operators
data class Point(var x: Int, var y: Int) : Comparable<Point> {

    // Overloading plus operator -> conversion member function
    operator fun plus(point: Point): Point { // Use of operator keyword
        return Point(x + point.x, y + point.y)
    }

    operator fun plusAssign(point: Point) : Unit {
        this.x += point.x
        this.y += point.y
    }

    // Overloading unary plus operator
    operator fun unaryMinus() : Point {
        return Point(-x, -y)
    }

    // Overriding equals operator
    override fun equals(other: Any?): Boolean {

        if(other === this) return true      // Checks if it is exactly the same object

        if(other !is Point) return false    // Checks the parameter type. Do a smart cast!

        return other.x == x && other.y == y // Checks actually equality
    }

    // Overriding compareTo operator
    override fun compareTo(other: Point): Int {
        return compareValuesBy(this, other, Point::x, Point::y) // Pass callbacks as lambdas or property references
    }
}

// Define get operator
operator fun Point.get(index: Int) : Int {

    return when(index) {
        0 -> x
        1 -> y
        else ->
            throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

// Define set operator
operator fun Point.set(index: Int, value: Int) : Unit {
    when(index) {

        0 -> x = value
        1 -> y = value
        else ->
            throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

// Overloading minus operator -> conversion extension function
operator fun Point.minus(point: Point): Point { // Use of operator keyword
    return Point(x - point.x, y - point.y)
}
// Overloading times operator -> conversion extension function
operator fun Point.times(times: Double) : Point {
    return Point((x * times).toInt(), (y * times).toInt())
}
// Another overloading times operator
operator fun Point.times(time: Int): String {
    return Point(x * time, y * time).toString()
}

operator fun Point.minusAssign(point: Point) : Unit {
    this.x -= point.x
    this.y -= point.y
}

operator fun Point.unaryPlus() : Point {
    return Point(+x, +y)
}

fun main(args: Array<String>) {

    val p1: Point = Point(20, 40)
    val p2: Point = Point(10, 20)
    var p3: Point = Point(5, 10)

    val z = p1 + p2                              // Calls the "plus" function using the + sign. Under the hood: p1 + p2 => p1.plus(p2)
    println("New added point: ${z.x} , ${z.y}")

    val x = p1 - p2                              // Calls the "minus" function - declared as extension function using the - sign. Under the hood: p1 - p2 => p1.minus(p2)
    println("New subtracted point: ${x.x} , ${x.y}")

    val times: Double = 1.5
    val p11 = p1 * times  // Does not support commutativity akka times * p1
    val p21 = p2 * times  // Does not support commutativity akka times * p2
    println("New multiplied p1 by 1.5 point: ${p11.x} , ${p11.y}")
    println("New multiplied p2 by 1.5 point: ${p21.x} , ${p21.y}")

    val p12 = p1 * 3
    val p22 = p2 * 3
    println("New multiplied p1 by 3 point: ${p12}")
    println("New multiplied p2 by 3 point: ${p22}")

    println(0x0F and 0xF0)  // Bitwise operators
    println(0x0F or 0xF0)   // Bitwise operators
    println(0x1 shl 4)      // Bitwise operators

    p1 += p2                                            // Returns the same reference! Not re-assignment
    println("Plus assigned point: ${p1.x} , ${p1.y}")
    p1 -= p2                                            // Returns the same reference! Not re-assignment
    println("Minus assigned point: ${p1.x} , ${p1.y}")

    println("P3 point: ${p3.x} , ${p3.y}")
    p3 *= 2.0
    println("Times assigned point: ${p3.x} , ${p3.y}")  // Returns a new reference! Performs re-assignment

    var p31 = -p3
    println("P3 unary minus point: ${p31.x} , ${p31.y}")
    var p32 = +p31
    println("P3 unary plus point: ${p32.x} , ${p32.y}")

    println(Point(30,30) == Point(30,30))
    println(Point(10,20) != Point(5,5))
    println(null == Point(1,2))

    val p4 = Point(30,40)
    val p5 = Point(40, 30)

    println(p4 >= p5)
    println(p4 <= p5)

    var myPoint: Point = Point(1,1)
    println("My Point Co-ordinates: x: ${myPoint[0]}, y: ${myPoint[1]}") // Goes to GET operator
    myPoint[0] = 2                                                       // Goes to SET operator
    myPoint[1] = 2                                                       // Goes to SET operator
    println("My Point Co-ordinates: x: ${myPoint[0]}, y: ${myPoint[1]}")

    // Check contains operator
    var rectangle: Rectangle = Rectangle(Point(10, 20), Point(50,50))
    var point: Point = Point(5,5)
    println("Is point: ${point.x},${point.y} contained in rectangle ${rectangle.upperLeft},${rectangle.lowerRight}? ${point in rectangle}")
    var pointJ: Point = Point(20,30)
    println("Is pointJ: ${pointJ.x},${pointJ.y} contained in rectangle ${rectangle.upperLeft},${rectangle.lowerRight}? ${pointJ in rectangle}")

    // Create a Date range
    var now = LocalDate.now();
    var datePeriod = now..now.plusDays(10) // => now.rangeTo(now.plusDays(10))
    println("Date Range: ${now.plusWeeks(1) in datePeriod}")

    val n = 10
    (0..n).forEach { println(it) }

    // Destructuring Declarations
    val (xr, yr) = Point(10,20)
    println("Destructuring Declarations: x value: ${xr} y value: ${yr}")
}