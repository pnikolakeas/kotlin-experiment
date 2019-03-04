package annotations

import kotlin.reflect.KClass

annotation class CustomSerializer(
    val serializerClass : KClass<out ValueSerializer<*>>
)