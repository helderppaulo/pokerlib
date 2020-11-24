package pokerlib.base.classification.adhoc

import pokerlib.base.classification.HandClassification
import pokerlib.base.classification.HandClassificationType.FLUSH
import pokerlib.base.classification.HandClassificationType.FOUR_OF_A_KIND
import pokerlib.base.classification.HandClassificationType.FULL_HOUSE
import pokerlib.base.classification.HandClassificationType.HIGH_CARD
import pokerlib.base.classification.HandClassificationType.PAIR
import pokerlib.base.classification.HandClassificationType.ROYAL_FLUSH
import pokerlib.base.classification.HandClassificationType.STRAIGHT
import pokerlib.base.classification.HandClassificationType.STRAIGHT_FLUSH
import pokerlib.base.classification.HandClassificationType.THREE_OF_A_KIND
import pokerlib.base.classification.HandClassificationType.TWO_PAIR
import pokerlib.base.model.Value
import pokerlib.base.utils.ValueComparator

object AdhocHandClassificationComparator : Comparator<HandClassification> {

    private const val defaultTypeValue = 0
    private val typeValues = mapOf(
        HIGH_CARD to 1,
        PAIR to 2,
        TWO_PAIR to 3,
        THREE_OF_A_KIND to 4,
        STRAIGHT to 5,
        FLUSH to 6,
        FULL_HOUSE to 7,
        FOUR_OF_A_KIND to 8,
        STRAIGHT_FLUSH to 9,
        ROYAL_FLUSH to 10
    )

    override fun compare(instance: HandClassification?, other: HandClassification?): Int {
        val typeComparison = typeValue(instance).compareTo(typeValue(other))
        if (typeComparison != 0) {
            return typeComparison
        } else {
            for ((a, b) in zipValues(instance, other)) {
                val valueComparison = ValueComparator.compare(a, b)
                if (valueComparison != 0) {
                    return valueComparison
                }
            }
            for ((a, b) in zipKickers(instance, other)) {
                val kickerComparison = ValueComparator.compare(a, b)
                if (kickerComparison != 0) {
                    return kickerComparison
                }
            }
            return 0
        }
    }

    private fun typeValue(classification: HandClassification?): Int {
        return typeValues.getOrDefault(classification?.type, defaultTypeValue)
    }

    private fun zipValues(instance: HandClassification?, other: HandClassification?): List<Pair<Value, Value>> {
        return zipClassifications(instance, other, HandClassification::values)
    }

    private fun zipKickers(instance: HandClassification?, other: HandClassification?): List<Pair<Value, Value>> {
        return zipClassifications(instance, other, HandClassification::kickers)
    }

    private fun <T> zipClassifications(
        instance: HandClassification?,
        other: HandClassification?,
        accessor: (HandClassification) -> List<T>
    ): List<Pair<T, T>> {
        return if (instance != null && other != null) accessor(instance).zip(accessor(other))
        else listOf()
    }
}
