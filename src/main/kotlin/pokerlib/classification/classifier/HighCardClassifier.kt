package pokerlib.classification.classifier

import pokerlib.classification.HandClassification
import pokerlib.classification.HandClassificationType.HIGH_CARD
import pokerlib.classification.SpecificHandClassifier
import pokerlib.model.Hand
import pokerlib.model.Value
import pokerlib.utils.ValueComparator
import pokerlib.utils.checkGroups
import pokerlib.utils.handToList

object HighCardClassifier : SpecificHandClassifier {

    private val pattern = listOf(1, 1, 1, 1, 1)

    override fun applies(hand: Hand) = checkGroups(hand, pattern)

    override fun classify(hand: Hand): HandClassification {
        val values = orderedValues(hand)
        return HandClassification(hand, HIGH_CARD, values.subList(0, 1), values.subList(1, 5))
    }

    private fun orderedValues(hand: Hand): List<Value> {
        return handToList(hand).map { it.value }.sortedWith(ValueComparator.reversed())
    }
}
