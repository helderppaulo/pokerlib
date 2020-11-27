package pokerlib.base.serialization

interface Serializer<T, U> {
    fun serialize(instance: T): U

    fun parse(serialized: U): T
}
