package pokerlib.base.classification.classifier

import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandClassificationType.FULL_HOUSE
import pokerlib.base.model.Hand
import pokerlib.base.utils.handToList

object FullHouseClassifier : ValueGroupClassifier(2, 3) {

    override fun classify(hand: Hand): HandClassification {
        val grouped = handToList(hand).groupBy { it.value }
        val values = grouped
            .map { it.key to it.value.size }
            .sortedByDescending { it.second }
            .map { it.first }
        return HandClassification(hand, FULL_HOUSE, values, listOf())
    }
}
