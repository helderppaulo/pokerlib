package pokerlib.base.classification.classifier

import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandClassificationType.THREE_OF_A_KIND
import pokerlib.base.model.Hand
import pokerlib.base.utils.ValueComparator
import pokerlib.base.utils.handToList

object ThreeOfAKindClassifier : ValueGroupClassifier(1, 1, 3) {

    override fun classify(hand: Hand): HandClassification {
        val grouped = handToList(hand)
            .groupBy { it.value }
        val values = grouped
            .filter { it.value.size == 3 }
            .map { it.key }
        val kickers = grouped
            .filter { it.value.size == 1 }
            .map { it.key }
            .sortedWith(ValueComparator.reversed())
        return HandClassification(hand, THREE_OF_A_KIND, values, kickers)
    }
}
