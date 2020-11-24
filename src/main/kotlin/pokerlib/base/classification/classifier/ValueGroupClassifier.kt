package pokerlib.base.classification.classifier

import pokerlib.base.classification.SpecificHandClassifier
import pokerlib.base.model.Hand
import pokerlib.base.utils.sequenced
import pokerlib.base.utils.suited

abstract class ValueGroupClassifier(
    vararg sizes: Int
) : SpecificHandClassifier {

    private val pattern = ValueGroupChecker.Pattern(sizes.asList())

    override fun applies(hand: Hand) =
        ValueGroupChecker.match(pattern, hand) && !sequenced(hand) && !suited(hand)
}
