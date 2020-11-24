package pokerlib.base.classification.classifier

import pokerlib.base.classification.SpecificHandClassifier
import pokerlib.base.model.Hand
import pokerlib.base.utils.ValueGroupChecker

abstract class ValueGroupClassifier(
    vararg sizes: Int
) : SpecificHandClassifier {

    private val pattern = ValueGroupChecker.Pattern(sizes.asList())

    override fun applies(hand: Hand) = ValueGroupChecker.match(pattern, hand)

}