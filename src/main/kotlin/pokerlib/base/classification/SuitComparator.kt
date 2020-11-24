package pokerlib.base.classification

import pokerlib.base.model.Suit

object SuitComparator : Comparator<Suit> {

    private const val defaultSuitValue = 0
    private val order = Suit.values().withIndex().map { (index, value) -> value to index + 1 }.toMap()

    override fun compare(instance: Suit?, other: Suit?) = suitValue(instance).compareTo(suitValue(other))

    private fun suitValue(suit: Suit?) = order.getOrDefault(suit, defaultSuitValue)
}
