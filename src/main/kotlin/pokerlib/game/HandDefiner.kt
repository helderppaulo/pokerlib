package pokerlib.game

import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandClassifier
import pokerlib.base.classification.HandComparator
import pokerlib.base.classification.cached.CachedHandComparator
import pokerlib.base.model.Card
import pokerlib.base.utils.possibleHands

object HandDefiner {
    fun execute(cards: Set<Card>, comparator: HandComparator = CachedHandComparator): HandClassification {
        val hand = possibleHands({ it }, cards).sortedWith(comparator).last()
        return HandClassifier.classify(hand)
    }
}
