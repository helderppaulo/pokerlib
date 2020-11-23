package pokerlib.classification.cached

import pokerlib.classification.HandComparator
import pokerlib.model.Hand
import pokerlib.serialization.HandIntegerSerializer

object CachedHandComparator : HandComparator {

    private val cache: Map<Int, Int> by lazy { HandClassifierCacheFactory.create() }

    override fun compare(first: Hand?, second: Hand?): Int {
        return serialize(first).compareTo(serialize(second))
    }

    private fun serialize(hand: Hand?): Int {
        return hand?.let(HandIntegerSerializer::serialize).let(cache::get) ?: -1
    }
}