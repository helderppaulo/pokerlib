package pokerlib.base.utils

import com.google.common.collect.Sets
import pokerlib.base.classification.CardComparator
import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandComparator
import pokerlib.base.classification.adhoc.AdhocHandClassificationComparator
import pokerlib.base.classification.adhoc.AdhocHandComparator
import pokerlib.base.model.Card
import pokerlib.base.model.Hand
import pokerlib.base.model.Suit
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

fun handToList(hand: Hand): List<Card> = hand.cards.toList().sortedWith(CardComparator)

fun cardsToHand(vararg cards: Card): Hand {
    return Hand(cards.toSet())
}

fun cardsToHand(cards: Collection<Card>): Hand {
    return Hand(cards.toSet())
}

fun <T : Any?> printIntercept(o: T): T {
    println(o)
    return o
}

fun equivalent(first: Hand, second: Hand, comparator: HandComparator = AdhocHandComparator): Boolean =
    compareHands(first, second, comparator) { it == 0 }

fun equivalent(first: HandClassification, second: HandClassification): Boolean = compare(first, second) { it == 0 }

fun stronger(first: Hand, second: Hand, comparator: HandComparator = AdhocHandComparator): Boolean =
    compareHands(first, second, comparator) { it > 0 }

fun weaker(first: Hand, second: Hand, comparator: HandComparator = AdhocHandComparator): Boolean =
    compareHands(first, second, comparator) { it < 0 }

private fun compare(first: HandClassification, second: HandClassification, fn: (Int) -> Boolean): Boolean =
    AdhocHandClassificationComparator.compare(first, second).let(fn)

private fun compareHands(
    first: Hand,
    second: Hand,
    comparator: HandComparator,
    fn: (Int) -> Boolean
): Boolean =
    comparator.compare(first, second).let(fn)

fun valueSet(hand: Hand): Set<Value> = handSet(hand, Card::value)

fun suitSet(hand: Hand): Set<Suit> = handSet(hand, Card::suit)

private fun <T> handSet(hand: Hand, fn: (Card) -> T): Set<T> = handToList(hand).map(fn).toSet()

fun suited(hand: Hand): Boolean = suitSet(hand).size == 1

private fun fullDeckCards(): Set<Card> = generateDeck().remaining.toSet()

fun <T> possibleHands(transformer: (Hand) -> T, cards: Set<Card> = fullDeckCards()): List<T> {
    return Sets.combinations(cards, 5)
        .map(::cardsToHand)
        .map(transformer)
        .toList()
}

private val sequenceOptions = listOf(ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE)
    .windowed(5)
    .map { it.toSet() }
    .toSet()

fun sequenced(hand: Hand) = sequenceOptions.contains(valueSet(hand))

fun <T, U> reversed(map: Map<T, U>): Map<U, T> = map.entries.associate { (k, v) -> v to k }

fun <T> mapToIndex(list: List<T>): Map<T, Int> = list.withIndex().map { (index, item) -> item to index }.toMap()
