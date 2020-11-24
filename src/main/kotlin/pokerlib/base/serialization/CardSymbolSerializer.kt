package pokerlib.base.serialization

import pokerlib.base.model.Card
import pokerlib.base.model.Suit
import pokerlib.base.model.Value

object CardSymbolSerializer {

    private val suitMap = Suit.values().map { it.representation() to it }.toMap()

    private val valueMap = Value.values().map { it.representation() to it }.toMap()

    fun serialize(card: Card): String = card.representation()

    fun parse(serialized: String): Card {
        val value = serialized[0].let { valueMap.getValue(it.toString()) }
        val suit = serialized[1].let { suitMap.getValue(it.toString()) }
        return Card(value, suit)
    }
}
