package pokerlib.base.model

import pokerlib.base.serialization.symbol.HandSymbolSerializer

data class Hand(
    val cards: Set<Card>
) {
    init {
        assert(cards.size == 5)
    }

    override fun toString(): String {
        return HandSymbolSerializer.serialize(this)
    }
}
