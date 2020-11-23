package pokerlib.classification.classifier

import pokerlib.classification.HandClassification
import pokerlib.classification.HandClassificationType.TWO_PAIR
import pokerlib.classification.SpecificHandClassifier
import pokerlib.model.Hand
import pokerlib.utils.ValueComparator
import pokerlib.utils.checkGroups
import pokerlib.utils.handToList

object TwoPairClassifier : SpecificHandClassifier {

    private val pattern = listOf(1, 2, 2)

    override fun applies(hand: Hand) = checkGroups(hand, pattern)

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