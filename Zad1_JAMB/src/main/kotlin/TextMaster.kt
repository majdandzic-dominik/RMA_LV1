class TextMaster()
{
    val welcomeText = "Welcome to Yahtzee. You have 5 dice. The first roll you are forced to roll all of them.\n" +
            "After that you get another two throws. Before those throws you can decide which dice you want to re-roll and which you want to keep.\n" +
            "After each throw you will be displayed your current score and any combinations that you currently have to help you decide on your next move.\n"


    fun getCombinationText(cup: CupOfDice, sm: ScoreMaster): String
    {
        var text = ""
        when
        {
            sm.isFiveOfAKind(cup) ->
            {
                text += "You currently have five of a kind (Yahtzee).\n"
            }
            sm.isLargeStraight(cup) ->
            {
                text += "You currently have a large straight (five consecutive numbers).\n"
            }
            sm.isSmallStraight(cup) ->
            {
                text += "You currently have a small straight (four consecutive numbers).\n"
            }
            sm.isFullHouse(cup) ->
            {
                text += "You currently have a full house (3 of a kind and 2 of a kind).\n"
            }
            sm.isFourOfAKind(cup) ->
            {
                text += "You currently have four of a kind.\n"
            }
            sm.isThreeOfAKind(cup) ->
            {
                text += "You currently have three of a kind.\n"
            }
            else ->
            {
                text += "You currently don't have any combination.\n"
            }
        }
        text += "Points: ${sm.calculateScore(cup)}\n"
        return text
    }

}