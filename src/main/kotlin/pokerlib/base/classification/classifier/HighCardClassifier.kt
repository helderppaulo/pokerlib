package pokerlib.base.classification.classifier

import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandClassificationType.HIGH_CARD
import pokerlib.base.classification.ValueComparator
import pokerlib.base.model.Hand
import pokerlib.base.model.Value
import pokerlib.base.utils.handToList

object HighCardClassifier : ValueGroupClassifier(1, 1, 1, 1, 1) {

    override fun classify(hand: Hand): HandClassification {
        val values = orderedValues(hand)
        return HandClassification(hand, HIGH_CARD, values.subList(0, 1), values.subList(1, 5))
    }

    private fun orderedValues(hand: Hand): List<Value> {
        return handToList(hand).map { it.value }.sortedWith(ValueComparator.reversed())
    }
}
