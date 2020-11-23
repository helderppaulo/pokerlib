package pokerlib.classification.classifier

import pokerlib.classification.HandClassification
import pokerlib.classification.HandClassificationType.FULL_HOUSE
import pokerlib.classification.SpecificHandClassifier
import pokerlib.model.Hand
import pokerlib.utils.checkGroups
import pokerlib.utils.handToList

object FullHouseClassifier : SpecificHandClassifier {

    private val pattern = listOf(2, 3)

    override fun applies(hand: Hand) = checkGroups(hand, pattern)

    override fun classify(hand: Hand): HandClassification {
        val grouped = handToList(hand).groupBy { it.value }
        val values = grouped
            .map { it.key to it.value.size }
            .sortedByDescending { it.second }
            .map { it.first }
        return HandClassification(hand, FULL_HOUSE, values, listOf())
    }
}
