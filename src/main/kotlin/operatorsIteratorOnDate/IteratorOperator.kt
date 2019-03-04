package operatorsIteratorOnDate

import java.time.LocalDate

// Overloading the iterator (responsible for calling from in) operator with LocalDate
operator fun ClosedRange<LocalDate>.iterator() : Iterator<LocalDate> =
    // Creates and return a Singleton object
     object : Iterator<LocalDate> {

        var current =  start

        override fun hasNext(): Boolean {
            return current <= endInclusive
        }

        override fun next(): LocalDate {
            return current.apply {
                current = plusDays(1)
            }
        }
    }

fun main(args: Array<String>) {

    val newYear = LocalDate.ofYearDay(2019, 1)
    var period  = newYear.minusDays(3)..newYear
    for(x in period) // The in operator calls the iterator implemented above under the hood
        println(x)

}