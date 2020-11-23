package pokerlib.model

enum class Value(
    private val symbol: String
) {

    ACE(symbol = "A"),
    TWO(symbol = "2"),
    THREE(symbol = "3"),
    FOUR(symbol = "4"),
    FIVE(symbol = "5"),
    SIX(symbol = "6"),
    SEVEN(symbol = "7"),
    EIGHT(symbol = "8"),
    NINE(symbol = "9"),
    TEN(symbol = "T"),
    JACK(symbol = "J"),
    QUEEN(symbol = "Q"),
    KING(symbol = "K");

    fun representation() = symbol

}