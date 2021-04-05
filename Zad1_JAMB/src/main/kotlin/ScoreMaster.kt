class ScoreMaster()
{
    fun isThreeOfAKind(cup: CupOfDice): Boolean     //three same numbers
    {
        val dice = cup.listOfDice.toMutableList()

        for(num in 1 until 7)
        {
            var count = 0
            for(d in dice)
            {
                if(d.value == num)
                {
                    count++
                }
            }

            if(count == 3)
            {
                return true
            }
        }
        return false
    }

    fun isFourOfAKind(cup: CupOfDice): Boolean     //four same numbers
    {
        val dice = cup.listOfDice.toMutableList()

        for(num in 1 until 7)
        {
            var count = 0
            for(d in dice)
            {
                if(d.value == num)
                {
                    count++
                }
            }

            if(count == 4)
            {
                return true
            }
        }
        return false
    }

    fun isFiveOfAKind(cup: CupOfDice): Boolean     //five same numbers (YAHTZEE)
    {
        val dice = cup.listOfDice.toMutableList()

        for(num in 1 until 7)
        {
            var count = 0
            for(d in dice)
            {
                if(d.value == num)
                {
                    count++
                }
            }

            if(count == 5)
            {
                return true
            }
        }
        return false
    }

    fun isFullHouse(cup: CupOfDice): Boolean    //three same numbers, and two same numbers
    {
        val dice = cup.listOfDice.toMutableList()
        dice.sortBy { d: Dice -> d.value }

        if(dice[0].value == dice[1].value
            && dice[0].value == dice[2].value
            && dice[3].value == dice[4].value
            || dice[0].value == dice[1].value
            && dice[2].value == dice[3].value
            && dice[2].value == dice[4].value)
        {
            return true
        }

        return false
    }

    fun isLargeStraight(cup: CupOfDice): Boolean    //any five consecutive numbers
    {
        val dice = cup.listOfDice.toMutableList()
        dice.sortBy { d: Dice -> d.value }

        if(dice[0].value == 1 && dice[1].value == 2 && dice[2].value == 3 && dice[3].value == 4 && dice[4].value == 5
            || dice[0].value == 2 && dice[1].value == 3 && dice[2].value == 4 && dice[3].value == 5 && dice[4].value == 6)
        {
            return true
        }

        return false
    }

    fun isSmallStraight(cup: CupOfDice): Boolean    //any four consecutive numbers
    {
        val dice = cup.listOfDice.toMutableList()
        dice.sortBy { d: Dice -> d.value }

        //used for moving duplicates to the end
        for(i in 0 until 4)
        {
            var temp: Dice
            if(dice[i].value == dice[i+1].value)
            {
                temp = dice[i]

                for(j in i until 4)
                {
                    dice[j] = dice[j+1]
                }

                dice[4] = temp
            }
        }

        if(dice[0].value == 1 && dice[1].value == 2 && dice[2].value == 3 && dice[3].value == 4             //1234
            || dice[0].value == 2 && dice[1].value == 3 && dice[2].value == 4 && dice[3].value == 5         //2345
            || dice[0].value == 3 && dice[1].value == 4 && dice[2].value == 5 && dice[3].value == 6         //3456
            || dice[1].value == 1 && dice[2].value == 2 && dice[3].value == 3 && dice[4].value == 4         //1234  when duplicate wasn't moved to the end
            || dice[1].value == 2 && dice[2].value == 3 && dice[3].value == 4 && dice[4].value == 5         //2345  when duplicate wasn't moved to the end
            || dice[1].value == 3 && dice[2].value == 4 && dice[3].value == 5 && dice[4].value == 6)        //3456  when duplicate wasn't moved to the end
        {
            return true
        }

        return false
    }


    fun calculateScore(cup: CupOfDice): Int
    {
        when {
            isFiveOfAKind(cup) -> {
                return 50
            }
            isLargeStraight(cup) -> {
                return 40
            }
            isSmallStraight(cup) -> {
                return 30
            }
            isFullHouse(cup) -> {
                return 25
            }
        }

        var score = 0

        for(d in cup.listOfDice)
        {
            score += d.value
        }

        return score
    }
}