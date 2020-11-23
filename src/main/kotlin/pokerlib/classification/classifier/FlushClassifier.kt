package pokerlib.classification.classifier

import pokerlib.classification.HandClassification
import pokerlib.classification.HandClassificationType
import pokerlib.classification.SpecificHandClassifier
import pokerlib.model.Card
import pokerlib.model.Hand
import pokerlib.utils.ValueComparator
import pokerlib.utils.handToList
import pokerlib.utils.suited

object FlushClassifier : SpecificHandClassifier {

    override fun applies(hand: Hand) = suited(hand)

    override fun classify(hand: Hand): HandClassification {
        val values = handToList(hand).map(Card::value).sortedWith(ValueComparator.reversed())
        return HandClassification(hand, HandClassificationType.FLUSH, values, listOf())
    }
}
