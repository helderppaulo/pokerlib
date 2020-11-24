package pokerlib.base.utils

import pokerlib.base.model.Card
import pokerlib.base.model.Deck
import pokerlib.base.model.Suit
import pokerlib.base.model.Value

fun build(remaining: List<Card>, removed: List<Card> = listOf()): Deck {
    assert((removed + remaining).toSet().size == 52)
    return Deck(remaining = remaining, removed = listOf())
}

fun generateDeck(shuffle: Boolean = false): Deck {
    fun shuffle(cards: Sequence<Card>) = if (shuffle) cards.shuffled() else cards

    val cards = sequence {
        for (value in Value.values())
            for (suit in Suit.values())
                yield(Card(value, suit))
    }

    return cards
        .let(::shuffle)
        .toList()
        .let(::build)
}

fun take(deck: Deck, n: Int): Pair<Deck, List<Card>> {
    val taken = deck.remaining.subList(0, n)
    return Pair(build(deck.remaining.subList(n, 52), deck.removed.plus(taken)), taken)
}
