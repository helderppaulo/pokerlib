package pokerlib.serialization

import pokerlib.model.Card
import pokerlib.model.Hand
import pokerlib.model.Suit
import pokerlib.model.Value
import pokerlib.utils.CardComparator
import pokerlib.utils.handToList
import pokerlib.utils.listToHand
import java.lang.Integer.toBinaryString

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
            .joinToString(transform = this::serializeCard, separator = "")
//            .let(::printIntercept)
            .toInt(2)
//            .let(::printIntercept)
    }

    private fun serializeCard(card: Card): String {
        val serializedValue = toBinaryString(valueMap.getValue(card.value)).padStart(4, '0')
        val serializedSuit = toBinaryString(suitMap.getValue(card.suit)).padStart(2, '0')
        return "${serializedValue}$serializedSuit"
    }

    fun parse(serialized: Int): Hand {
        return serialized
//            .let(::printIntercept)
            .let(::toBinaryString)
            .padStart(30, '0')
//            .let(::printIntercept)
            .chunked(6)
            .map(this::parseCard).let { listToHand(it) }
    }

    private fun parseCard(card: String): Card {
        val value = valueReverseMap.getValue(card.substring(0, 4).toInt(2))
        val suit = suitReverseMap.getValue(card.substring(4, 6).toInt(2))
        return Card(value, suit)
    }
}
