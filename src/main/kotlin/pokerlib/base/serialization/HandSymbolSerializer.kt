package pokerlib.base.serialization

import pokerlib.base.model.Hand

object HandSymbolSerializer {

    fun serialize(hand: Hand): String = hand.representation()

    fun parse(serialized: String): Hand {
        val cards = serialized.trim('[', ']').split(",").map(CardSymbolSerializer::parse)
        return Hand(cards[0], cards[1], cards[2], cards[3], cards[4])
    }
}
