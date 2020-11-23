package pokerlib.serialization

import pokerlib.model.Card
import pokerlib.model.Hand
import pokerlib.model.Suit
import pokerlib.model.Value

object HandSymbolSerializer {

    private val suitMap = Suit.values().map { it.representation() to it }.toMap()

    private val valueMap = Value.values().map { it.representation() to it }.toMap()

    fun serialize(hand: Hand): String = hand.representation()

    fun parse(serialized: String): Hand {
        val cards = serialized.trim('[', ']').split(",").map(this::parseCard)
        return Hand(cards[0], cards[1], cards[2], cards[3], cards[4])
    }

    private fun parseCard(card: String): Card {
        val value = card[0].let { valueMap.getValue(it.toString()) }
        val suit = card[1].let { suitMap.getValue(it.toString()) }
        return Card(value, suit)
    }
}
