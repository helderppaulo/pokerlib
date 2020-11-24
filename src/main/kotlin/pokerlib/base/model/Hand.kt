package pokerlib.base.model

import pokerlib.base.utils.equivalent

data class Hand(
    val first: Card,
    val second: Card,
    val third: Card,
    val fourth: Card,
    val fifth: Card
) {

    fun representation(): String {
        return listOf(first, second, third, fourth, fifth)
            .joinToString(separator = ",", prefix = "[", postfix = "]") { it.representation() }
    }

    override fun toString(): String {
        return this.representation()
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Hand -> equivalent(this, other)
            else -> false
        }
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
