package pokerlib.base.utils

import pokerlib.base.model.Card

object CardComparator : Comparator<Card> {

    override fun compare(p0: Card?, p1: Card?): Int {
        val valueComparison = ValueComparator.compare(p0?.value, p1?.value)
        return if (valueComparison != 0) {
            valueComparison
        } else {
            SuitComparator.compare(p0?.suit, p1?.suit)
        }
    }
}
