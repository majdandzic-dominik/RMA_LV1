import kotlin.random.Random

class Dice(var value: Int = 1, var selected: Boolean = true)
{
    fun roll()
    {
        value = Random.nextInt(1, 7)
    }
}