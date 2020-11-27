package pokerlib.base.serialization.integer

import pokerlib.base.classification.CardComparator
import pokerlib.base.model.Hand
import pokerlib.base.serialization.Serializer
import pokerlib.base.utils.cardsToHand
import pokerlib.base.utils.handToList

object HandIntegerSerializer : Serializer<Hand, Int> {

    override fun serialize(instance: Hand): Int =
        handToList(instance)
            .sortedWith(CardComparator)
            .mapIndexed { index, card -> position(index, CardIntegerSerializer.serialize(card)) }
            .sum()

    private fun position(cardPosition: Int, serializedCard: Int): Int {
        val distance = 6 * (4 - cardPosition)
        return serializedCard shl distance
    }

    override fun parse(serialized: Int): Hand =
        IntRange(0, 4).reversed()
            .map { (serialized shr (it * 6)) % 64 }
            .map(CardIntegerSerializer::parse)
            .let(::cardsToHand)
}
