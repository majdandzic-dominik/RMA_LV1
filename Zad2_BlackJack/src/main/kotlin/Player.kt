class Player
{
    var hand = mutableListOf<Card>()

    fun addCard(card: Card)
    {
        hand.add(card)
    }

    fun clearHand()
    {
        hand.clear()
    }

    fun printHand()
    {
        for(card in hand)
            print("${card.type} ")
    }


    fun calculateSum(): Int
    {
        var sum = 0
        for(card in hand)
        {
            sum += card.points
        }
        if(doesAceExist())
        {
            if(sum > 21)
                return sum - 10
        }
        return sum
    }

    private fun doesAceExist(): Boolean
    {
        for(card in hand)
        {
            if(card.type == "Ace")
                return true
        }
        return false
    }

    fun isBust(): Boolean
    {
        return calculateSum() > 21
    }

}