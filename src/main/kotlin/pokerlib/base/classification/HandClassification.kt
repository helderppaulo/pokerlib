package pokerlib.base.classification

import pokerlib.base.model.Hand
import pokerlib.base.model.Value

data class HandClassification(
    val hand: Hand,
    val type: HandClassificationType,
    val values: List<Value>,
    val kickers: List<Value>
) {
    override fun toString(): String {
        return "{ hand: $hand, type: $type, values: $values , kickers: $kickers }"
    }
}
