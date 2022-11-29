import java.lang.IllegalArgumentException

const val HERO_NAME = "Madrigal"
var playerLevel = 0
fun main() {
    println("$HERO_NAME announces her presence to the world.")
    println("What level is $HERO_NAME?")
    playerLevel = readLine()?.toIntOrNull() ?: 0
    println("$HERO_NAME's level is $playerLevel.")

    readBountyBoard()

    println("Time passes...")
    println("$HERO_NAME returns from her quest")
    playerLevel += 1
    println(playerLevel)
    readBountyBoard()
}
fun shouldReturnAString(): String{
    TODO("implement the fun to return a string here")
    println("This is unreachable")
}
private fun readBountyBoard() {
    val quest: String? = obtainQuest(playerLevel)

    val message: String = quest?.replace("Nogartse", "He who shall not be named")
        ?.let{ censoredQuest ->
            """
            |$HERO_NAME approaches the bounty board. It reads:
            |"$censoredQuest"
            """.trimMargin()
        } ?: "$HERO_NAME approaches the bounty board, but it is blank."
    println(message)
}

private fun obtainQuest(
    playerLevel: Int,
    playerClass: String = "paladin",
    hasBefriendedBarbarians: Boolean = true,
    hasAngeredBarbarians: Boolean = false //sets a default value false if not defined in the fun call
): String? {
    if (playerLevel <= 0) {
        throw IllegalArgumentException("The player's level must be at least 1.")
    }
    return when (playerLevel) {
        1 -> "Meet Mr. Bubbles in the land of soft things."
        in 2..5 -> {
            // Check if diplomacy is an option
            val canTalkToBarbarians = !hasAngeredBarbarians &&
                    (hasBefriendedBarbarians || playerClass == "barbarian")
            if (canTalkToBarbarians) {
                "Convince the barbarians to call off their invasion."
            } else {
                "Save the town from the barbarian invasions."
            }
        }

        6 -> "Locate the enchanted sword."
        7 -> "Recover the long-lost artifact of creation."
        8 -> "Defeat Nogartse, bringer of death and eater of worlds."
        //else -> "There are no quests right now."
        else -> null
    }
}