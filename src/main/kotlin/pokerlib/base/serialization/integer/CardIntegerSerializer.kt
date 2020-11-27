package pokerlib.base.serialization.integer

import pokerlib.base.model.Card
import pokerlib.base.model.Suit
import pokerlib.base.model.Value.ACE
import pokerlib.base.model.Value.EIGHT
import pokerlib.base.model.Value.FIVE
import pokerlib.base.model.Value.FOUR
import pokerlib.base.model.Value.JACK
import pokerlib.base.model.Value.KING
import pokerlib.base.model.Value.NINE
import pokerlib.base.model.Value.QUEEN
import pokerlib.base.model.Value.SEVEN
import pokerlib.base.model.Value.SIX
import pokerlib.base.model.Value.TEN
import pokerlib.base.model.Value.THREE
import pokerlib.base.model.Value.TWO
import pokerlib.base.serialization.Serializer
import pokerlib.base.utils.mapToIndex
import pokerlib.base.utils.reversed

object CardIntegerSerializer : Serializer<Card, Int> {

    private val valueMap = listOf(ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING)
        .let(::mapToIndex)

    private val valueReverseMap = reversed(valueMap)

    private val suitMap = listOf(Suit.HEARTS, Suit.SPADES, Suit.CLUBS, Suit.DIAMONDS).let(::mapToIndex)

    private val suitReverseMap = reversed(suitMap)

    override fun serialize(instance: Card): Int {
        return valueMap.getValue(instance.value) * 4 + suitMap.getValue(instance.suit)
    }

    override fun parse(serialized: Int): Card {
        val suit = suitReverseMap.getValue(serialized % 4)
        val value = valueReverseMap.getValue(serialized shr 2)
        return Card(value, suit)
    }
}
