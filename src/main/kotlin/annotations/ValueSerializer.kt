package annotations

interface ValueSerializer<T> {
    fun toJsonValue(value: T) : Any?
    fun fromJsonValue(jsonValue: Any?) : T
}