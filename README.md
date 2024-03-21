# DSP332-PR1-K17

## Basic game overview:
* String of numbers from 1-9 with a user defined length of 15-25 is generated
* There are 2 point tallies -> Bank and Total
* Moves are turn-based
* In any given move, the player chooses two adjacent numbers and sums them up, replacing them with with 1 number depending on the sum
* If the number sum is:
    * greater than 7 -> the numbers are replaced with a 1 and the **total** points tally = +1
    * less than 7 -> the numbers are replaced with a 3 and the **total** points tally = -1
    * equal to 7 -> the numbers are replaced with a 2 and the **bank** point tally = +1
* The game ends when the string contains **1** number
    * If the bank and total point tallies are **even** -> starting player wins
    * If the bank and total poin ttallies are **odd** -> other player wins
    * Else -> tie
