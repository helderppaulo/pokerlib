package pokerlib.base.classification.classifier

import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandClassificationType
import pokerlib.base.classification.SpecificHandClassifier
import pokerlib.base.classification.ValueComparator
import pokerlib.base.model.Card
import pokerlib.base.model.Hand
import pokerlib.base.utils.handToList
import pokerlib.base.utils.suited

object FlushClassifier : SpecificHandClassifier {

    override fun applies(hand: Hand) = suited(hand)

    override fun classify(hand: Hand): HandClassification {
        val values = handToList(hand).map(Card::value).sortedWith(ValueComparator.reversed())
        return HandClassification(hand, HandClassificationType.FLUSH, values, listOf())
    }
}
