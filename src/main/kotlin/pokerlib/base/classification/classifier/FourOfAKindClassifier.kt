package pokerlib.base.classification.classifier

import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandClassificationType.FOUR_OF_A_KIND
import pokerlib.base.model.Card
import pokerlib.base.model.Hand
import pokerlib.base.utils.handToList

object FourOfAKindClassifier : ValueGroupClassifier(1, 4) {

    override fun classify(hand: Hand): HandClassification {
        val grouped = handToList(hand).groupBy(Card::value)
        val values = grouped
            .filter { it.value.size == 4 }
            .map { it.key }
        val kickers = grouped
            .filter { it.value.size == 1 }
            .map { it.key }
        return HandClassification(hand, FOUR_OF_A_KIND, values, kickers)
    }
}
