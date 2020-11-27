package pokerlib.base.serialization.symbol

import pokerlib.base.model.Card
import pokerlib.base.model.Suit
import pokerlib.base.model.Suit.CLUBS
import pokerlib.base.model.Suit.DIAMONDS
import pokerlib.base.model.Suit.HEARTS
import pokerlib.base.model.Suit.SPADES
import pokerlib.base.model.Value
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
import pokerlib.base.utils.reversed

object CardSymbolSerializer : Serializer<Card, String> {

    private val suitMap: Map<Suit, String> = mapOf(HEARTS to "h", SPADES to "s", CLUBS to "c", DIAMONDS to "d")

    private val valueMap: Map<Value, String> = mapOf(
        ACE to "A",
        TWO to "2",
        THREE to "3",
        FOUR to "4",
        FIVE to "5",
        SIX to "6",
        SEVEN to "7",
        EIGHT to "8",
        NINE to "9",
        TEN to "T",
        JACK to "J",
        QUEEN to "Q",
        KING to "K"
    )

    private val reversedSuitMap: Map<String, Suit> = reversed(suitMap)

    private val reversedValueMap = reversed(valueMap)

    override fun serialize(instance: Card): String {
        return "${valueMap.getValue(instance.value)}${suitMap.getValue(instance.suit)}"
    }

    override fun parse(serialized: String): Card {
        val value = serialized[0].let { reversedValueMap.getValue(it.toString()) }
        val suit = serialized[1].let { reversedSuitMap.getValue(it.toString()) }
        return Card(value, suit)
    }
}
