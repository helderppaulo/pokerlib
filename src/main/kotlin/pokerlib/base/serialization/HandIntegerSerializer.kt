package pokerlib.base.serialization

import pokerlib.base.model.Card
import pokerlib.base.model.Hand
import pokerlib.base.model.Suit
import pokerlib.base.model.Value
import pokerlib.base.utils.CardComparator
import pokerlib.base.utils.handToList
import pokerlib.base.utils.listToHand

object HandIntegerSerializer {

    private val valueMap = Value.values()
        .withIndex()
        .map { (index, value) -> value to index }
        .toMap()

    private val valueReverseMap = valueMap.entries
        .map { (key, value) -> value to key }
        .toMap()

    private val suitMap = Suit.values()
        .withIndex()
        .map { (index, suit) -> suit to index }
        .toMap()

    private val suitReverseMap = suitMap.entries
        .map { (key, value) -> value to key }
        .toMap()

    fun serialize(hand: Hand): Int {
        return handToList(hand)
            .sortedWith(CardComparator)
            .mapIndexed { index, card -> position(index, serializeCard(card)) }
            .sum()
    }

    private fun position(cardPosition: Int, serializedCard: Int): Int {
        val distance = 6 * (4 - cardPosition)
        return serializedCard shl distance
    }

    private fun serializeCard(card: Card): Int {
        return valueMap.getValue(card.value) * 4 + suitMap.getValue(card.suit)
    }

    fun parse(serialized: Int): Hand {
        return IntRange(0, 4).reversed()
            .map { (serialized shr (it * 6)) % 64 }
            .map(this::parseCard)
            .let { listToHand(it) }
    }

    private fun parseCard(card: Int): Card {
        val suit = suitReverseMap.getValue(card % 4)
        val value = valueReverseMap.getValue(card shr 2)
        return Card(value, suit)
    }
}
