package pokerlib.classification.classifier

import pokerlib.classification.HandClassification
import pokerlib.classification.HandClassificationType.THREE_OF_A_KIND
import pokerlib.classification.SpecificHandClassifier
import pokerlib.model.Hand
import pokerlib.utils.ValueComparator
import pokerlib.utils.checkGroups
import pokerlib.utils.handToList

object ThreeOfAKindClassifier : SpecificHandClassifier {

    private val pattern = listOf(1, 1, 3)

    override fun applies(hand: Hand) = checkGroups(hand, pattern)

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
