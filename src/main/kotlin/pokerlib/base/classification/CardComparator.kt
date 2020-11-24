package pokerlib.base.classification

import pokerlib.base.model.Card

object CardComparator : Comparator<Card> {

    override fun compare(first: Card?, second: Card?): Int {
        val valueComparison = ValueComparator.compare(first?.value, second?.value)
        return if (valueComparison != 0) valueComparison
        else SuitComparator.compare(first?.suit, second?.suit)
    }
}
