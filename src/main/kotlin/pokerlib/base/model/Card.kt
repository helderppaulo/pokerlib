package pokerlib.base.model

import pokerlib.base.serialization.symbol.CardSymbolSerializer

data class Card(
    val value: Value,
    val suit: Suit
) {
    override fun toString(): String {
        return CardSymbolSerializer.serialize(this)
    }
}
