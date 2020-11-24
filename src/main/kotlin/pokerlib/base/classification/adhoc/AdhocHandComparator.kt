package pokerlib.base.classification.adhoc

import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandClassifier
import pokerlib.base.classification.HandComparator
import pokerlib.base.model.Hand

object AdhocHandComparator : HandComparator {

    override fun compare(instance: Hand?, other: Hand?): Int {
        return AdhocHandClassificationComparator.compare(classify(instance), classify(other))
    }

    private fun classify(hand: Hand?): HandClassification? {
        return hand?.let { HandClassifier.classify(it) }
    }
}
