package pokerlib.base.classification.classifier

import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandClassificationType.PAIR
import pokerlib.base.model.Hand
import pokerlib.base.utils.ValueComparator
import pokerlib.base.utils.handToList

object PairClassifier : ValueGroupClassifier(1, 1, 1, 2) {

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
            .sortedWith(ValueComparator.reversed())
        return HandClassification(hand, PAIR, values, kickers)
    }
}
