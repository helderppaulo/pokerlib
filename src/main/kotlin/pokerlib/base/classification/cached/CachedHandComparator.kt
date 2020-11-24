package pokerlib.base.classification.cached

import pokerlib.base.classification.HandComparator
import pokerlib.base.model.Hand
import pokerlib.base.serialization.HandIntegerSerializer

object CachedHandComparator : HandComparator {

    private val cache: Map<Int, Int> by lazy { FileHandScoreCacheFactory.create() }

    override fun compare(first: Hand?, second: Hand?): Int {
        return serialize(first).compareTo(serialize(second))
    }

    private fun serialize(hand: Hand?): Int {
        return hand?.let(HandIntegerSerializer::serialize).let(cache::get) ?: -1
    }
}
