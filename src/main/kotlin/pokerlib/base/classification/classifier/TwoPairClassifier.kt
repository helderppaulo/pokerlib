package pokerlib.base.classification.classifier

import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandClassificationType.TWO_PAIR
import pokerlib.base.classification.ValueComparator
import pokerlib.base.model.Hand
import pokerlib.base.utils.handToList

object TwoPairClassifier : ValueGroupClassifier(1, 2, 2) {

    override fun classify(hand: Hand): HandClassification {
        val grouped = handToList(hand)
            .groupBy { it.value }
        val values = grouped
            .filter { it.value.size == 2 }
            .map { it.key }
            .sortedWith(ValueComparator.reversed())
        val kickers = grouped
            .filter { it.value.size == 1 }
            .map { it.key }
        return HandClassification(hand, TWO_PAIR, values, kickers)
    }
}
