package pokerlib.base.classification

import pokerlib.base.classification.classifier.FlushClassifier
import pokerlib.base.classification.classifier.FourOfAKindClassifier
import pokerlib.base.classification.classifier.FullHouseClassifier
import pokerlib.base.classification.classifier.HighCardClassifier
import pokerlib.base.classification.classifier.PairClassifier
import pokerlib.base.classification.classifier.RoyalFlushClassifier
import pokerlib.base.classification.classifier.StraightClassifier
import pokerlib.base.classification.classifier.StraightFlushClassifier
import pokerlib.base.classification.classifier.ThreeOfAKindClassifier
import pokerlib.base.classification.classifier.TwoPairClassifier
import pokerlib.base.model.Hand

object HandClassifier {

    private val classifiers = listOf(
        HighCardClassifier,
        PairClassifier,
        PairClassifier,
        TwoPairClassifier,
        ThreeOfAKindClassifier,
        StraightClassifier,
        FlushClassifier,
        FullHouseClassifier,
        FourOfAKindClassifier,
        StraightFlushClassifier,
        RoyalFlushClassifier
    )

    fun classify(hand: Hand): HandClassification {
        for (classifier in classifiers) {
            if (classifier.applies(hand)) {
                return classifier.classify(hand)
            }
        }
        throw RuntimeException("invalid hand")
    }
}
