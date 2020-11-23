package pokerlib.utils

import com.google.common.collect.Sets
import pokerlib.classification.HandClassification
import pokerlib.classification.HandComparator
import pokerlib.classification.adhoc.AdhocHandClassificationComparator
import pokerlib.classification.adhoc.AdhocHandComparator
import pokerlib.model.Card
import pokerlib.model.Hand
import pokerlib.model.Suit
import pokerlib.model.Value

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

fun checkGroups(hand: Hand, pattern: List<Int>): Boolean {
    val groups = handToList(hand)
        .groupBy { it.value }
        .map { (_, v) -> v.size }
        .sorted()
    return pattern.sorted() == groups
}

fun <T> possibleHands(transformer: (Hand) -> T): List<T> {
    val cards = generateDeck().remaining.toSet()
    return Sets.combinations(cards, 5)
        .map { listToHand(it.toList()) }
        .map(transformer)
        .toList()
}
