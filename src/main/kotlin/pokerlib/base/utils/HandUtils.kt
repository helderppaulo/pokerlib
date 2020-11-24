package pokerlib.base.utils

import com.google.common.collect.Sets
import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandComparator
import pokerlib.base.classification.adhoc.AdhocHandClassificationComparator
import pokerlib.base.classification.adhoc.AdhocHandComparator
import pokerlib.base.model.Card
import pokerlib.base.model.Hand
import pokerlib.base.model.Suit
import pokerlib.base.model.Value

fun handToList(hand: Hand): List<Card> = listOf(hand.first, hand.second, hand.third, hand.fourth, hand.fifth)

fun listToHand(cards: List<Card>): Hand {
    assert(cards.size == 5)
    return Hand(cards[0], cards[1], cards[2], cards[3], cards[4])
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
        .map { listToHand(it.toList()) }
        .map(transformer)
        .toList()
}
