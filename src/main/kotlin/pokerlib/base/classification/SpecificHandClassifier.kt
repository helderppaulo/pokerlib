package pokerlib.base.classification

import pokerlib.base.model.Hand

interface SpecificHandClassifier {

    fun applies(hand: Hand): Boolean

    fun classify(hand: Hand): HandClassification
}
