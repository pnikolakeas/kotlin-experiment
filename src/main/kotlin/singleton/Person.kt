package singleton

data class Person (val name: String) {
    object NameComparator : Comparator<Person> {
        override fun compare(o1: Person?, o2: Person?): Int {
            val k: String = o1?.name ?: ""
            val z: String = o2?.name ?: ""
            return k.compareTo(z, ignoreCase = true)
        }
    }
}