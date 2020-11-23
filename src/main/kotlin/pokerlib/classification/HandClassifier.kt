package pokerlib.classification

import pokerlib.classification.classifier.FlushClassifier
import pokerlib.classification.classifier.FourOfAKindClassifier
import pokerlib.classification.classifier.FullHouseClassifier
import pokerlib.classification.classifier.HighCardClassifier
import pokerlib.classification.classifier.PairClassifier
import pokerlib.classification.classifier.RoyalFlushClassifier
import pokerlib.classification.classifier.StraightClassifier
import pokerlib.classification.classifier.StraightFlushClassifier
import pokerlib.classification.classifier.ThreeOfAKindClassifier
import pokerlib.classification.classifier.TwoPairClassifier
import pokerlib.model.Hand

object HandClassifier {

    private val classifiers = listOf(
        RoyalFlushClassifier,
        StraightFlushClassifier,
        FourOfAKindClassifier,
        FullHouseClassifier,
        FlushClassifier,
        StraightClassifier,
        ThreeOfAKindClassifier,
        TwoPairClassifier,
        PairClassifier,
        PairClassifier,
        HighCardClassifier
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
