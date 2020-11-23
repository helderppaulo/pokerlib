package pokerlib.model

data class Deck(
    val remaining: List<Card>,
    val removed: List<Card>
)