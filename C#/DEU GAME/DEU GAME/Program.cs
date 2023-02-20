// MADE BY BURAK ÇOLAK
// STUDENT NO: 2020510079
using System;

namespace DEU_GAME
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] names = { "Derya", "Elife", "Fatih", "Ali", "Azra", "Sibel", "Cem", "Nazan", "Mehmet", "Nil", "Can", "Tarkan" };
            string[] finalNames = new string[names.Length + 1];
            int[] scores = { 100, 100, 95, 90, 85, 80, 80, 70, 55, 50, 30, 30 };
            int[] finalScores = new int[scores.Length + 1];
            char[] A1 = new char[15];
            char[] A2 = new char[15];
            char[] A3 = new char[15];

            int scoreOfPlayer1 = 120;
            int scoreOfPlayer2 = 120;
            int turn = 1;    // if turn is 1, its player1's turn; if turn is 2, its player2's turn.
            int A1Counter = 0;
            int A2Counter = 0;      // Counts the number of elements of the arrays.
            int A3Counter = 0;

            Random rnd = new Random();

            bool isGameOver = false;
            bool isOutOfRange;
            bool isPlayerPlaced = false;

            while (!isGameOver)
            {
                int randomValueProducer = rnd.Next(1, 4);    // produces random int between 1-3 for random char.
                char randomLetter = ' ';
                switch (randomValueProducer)
                {
                    case 1:
                        randomLetter = 'D';
                        break;
                    case 2:
                        randomLetter = 'E';
                        break;
                    case 3:
                        randomLetter = 'U';
                        break;
                    default:
                        break;
                }
                int randomArrayChooser;
                do
                {
                    isOutOfRange = false;
                    randomArrayChooser = rnd.Next(1, 4);
                    if (A1Counter == 15 && A2Counter == 15 && A3Counter == 15)
                    {
                        break;   // Tie 
                    }
                    if (A1Counter == 15 && randomArrayChooser == 1)
                    {
                        isOutOfRange = true;
                    }
                    else if (A2Counter == 15 && randomArrayChooser == 2)
                    {
                        isOutOfRange = true;
                    }
                    else if (A3Counter == 15 && randomArrayChooser == 3)
                    {
                        isOutOfRange = true;
                    }
                } while (isOutOfRange);

                // Assigning the random character to arrays
                switch (randomArrayChooser)
                {
                    case 1:
                        A1[A1Counter] = randomLetter;
                        A1Counter++;
                        break;
                    case 2:
                        A2[A2Counter] = randomLetter;
                        A2Counter++;
                        break;
                    case 3:
                        A3[A3Counter] = randomLetter;
                        A3Counter++;
                        break;
                    default:
                        break;
                }

                // Decreasing score
                if (turn == 1 && !isGameOver)
                {
                    scoreOfPlayer1 -= 5;
                }
                else if (turn == 2 && !isGameOver)
                {
                    scoreOfPlayer2 -= 5;
                }

                // Printing arrays
                Console.WriteLine("\n\nPlayer" + turn + ": \t(P1-" + scoreOfPlayer1 + " P2-" + scoreOfPlayer2 + ")");
                Console.Write("A1 ");
                for (int i = 0; i < A1.Length; i++)
                {
                    Console.Write(A1[i] + " ");
                }
                Console.Write("\nA2 ");
                for (int i = 0; i < A2.Length; i++)
                {
                    Console.Write(A2[i] + " ");
                }
                Console.Write("\nA3 ");
                for (int i = 0; i < A3.Length; i++)
                {
                    Console.Write(A3[i] + " ");
                }

  // Controlling whether game is over or not. I used switch case here, because I am processing according to the line with the last letter just added.
                switch (randomArrayChooser)
                {
                    case 1:
                        if (IsGameFinished(A1, A2, A3, randomArrayChooser, A1Counter))
                        {
                            isGameOver = true;
                            if (turn == 2)
                            {
                                // Assigning elements for high score table
                                for (int i = 0; i < finalNames.Length; i++)
                                {   
                                    if (i != scores.Length && scoreOfPlayer2 <= scores[i])
                                    {
                                        finalNames[i] = names[i];
                                        finalScores[i] = scores[i];
                                    }
                                    else if (i != scores.Length && scoreOfPlayer2 > scores[i] && !isPlayerPlaced)
                                    {
                                        finalNames[i] = "Player2";
                                        finalScores[i] = scoreOfPlayer2;
                                        isPlayerPlaced = true;
                                    }
                                    else
                                    {
                                        finalNames[i] = names[i - 1];
                                        finalScores[i] = scores[i - 1]; if (scoreOfPlayer2 < scores[scores.Length - 1])
                                        {
                                            finalNames[finalNames.Length - 1] = "Player2";
                                            finalScores[finalScores.Length - 1] = scoreOfPlayer1;
                                            isPlayerPlaced = true;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                // Assigning elements for high score table
                                for (int i = 0; i < finalNames.Length; i++)
                                {
                                    if (i != scores.Length && scoreOfPlayer1 <= scores[i])
                                    {
                                        finalNames[i] = names[i];
                                        finalScores[i] = scores[i];
                                    }
                                    else if (i != scores.Length && scoreOfPlayer1 > scores[i] && !isPlayerPlaced)
                                    {
                                        finalNames[i] = "Player1";
                                        finalScores[i] = scoreOfPlayer1;
                                        isPlayerPlaced = true;
                                    }
                                    else
                                    {
                                        finalNames[i] = names[i - 1];
                                        finalScores[i] = scores[i - 1]; if (scoreOfPlayer1 < scores[scores.Length - 1])
                                        {
                                            finalNames[finalNames.Length - 1] = "Player1";
                                            finalScores[finalScores.Length - 1] = scoreOfPlayer1;
                                            isPlayerPlaced = true;
                                        }
                                    }
                                }
                            }

                            Console.WriteLine("\n\nWinner: " + "Player" + turn + "\n");
                            Console.WriteLine("HIGH SCORE TABLE\n");
                            Console.WriteLine("Name\t Score");
                            for (int i = 0; i < finalNames.Length; i++)
                            {
                                Console.WriteLine(finalNames[i] + "\t " + finalScores[i]);
                            }
                        }
                        break;
                    case 2:
                        if (IsGameFinished(A1, A2, A3, randomArrayChooser, A2Counter))
                        {
                            isGameOver = true;
                            if (turn == 2)
                            {
                                // Assigning elements for high score table
                                for (int i = 0; i < finalNames.Length; i++)
                                {
                                    if (i != scores.Length && scoreOfPlayer2 <= scores[i])
                                    {
                                        finalNames[i] = names[i];
                                        finalScores[i] = scores[i];
                                    }
                                    else if (i != scores.Length && scoreOfPlayer2 > scores[i] && !isPlayerPlaced)
                                    {
                                        finalNames[i] = "Player2";
                                        finalScores[i] = scoreOfPlayer2;
                                        isPlayerPlaced = true;
                                    }
                                    else
                                    {
                                        finalNames[i] = names[i - 1];
                                        finalScores[i] = scores[i - 1]; if (scoreOfPlayer2 < scores[scores.Length - 1])
                                        {
                                            finalNames[finalNames.Length - 1] = "Player2";
                                            finalScores[finalScores.Length - 1] = scoreOfPlayer1;
                                            isPlayerPlaced = true;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                // Assigning elements for high score table
                                for (int i = 0; i < finalNames.Length; i++)
                                {
                                    if (i != scores.Length && scoreOfPlayer1 <= scores[i])
                                    {
                                        finalNames[i] = names[i];
                                        finalScores[i] = scores[i];
                                    }
                                    else if (i != scores.Length && scoreOfPlayer1 > scores[i] && !isPlayerPlaced)
                                    {
                                        finalNames[i] = "Player1";
                                        finalScores[i] = scoreOfPlayer1;
                                        isPlayerPlaced = true;
                                    }
                                    else
                                    {
                                        finalNames[i] = names[i - 1];
                                        finalScores[i] = scores[i - 1]; if (scoreOfPlayer1 < scores[scores.Length - 1])
                                        {
                                            finalNames[finalNames.Length - 1] = "Player1";
                                            finalScores[finalScores.Length - 1] = scoreOfPlayer1;
                                            isPlayerPlaced = true;
                                        }
                                    }
                                }
                            }
                            Console.WriteLine("\n\nWinner: " + "Player" + turn + "\n");
                            Console.WriteLine("HIGH SCORE TABLE\n");
                            Console.WriteLine("Name\t Score");
                            for (int i = 0; i < finalNames.Length; i++)
                            {
                                Console.WriteLine(finalNames[i] + "\t " + finalScores[i]);
                            }
                        }
                        break;
                    case 3:
                        if (IsGameFinished(A1, A2, A3, randomArrayChooser, A3Counter))
                        {
                            isGameOver = true;
                            if (turn == 2)
                            {
                                // Assigning elements for high score table
                                for (int i = 0; i < finalNames.Length; i++)
                                {
                                    if (i != scores.Length && scoreOfPlayer2 <= scores[i])
                                    {
                                        finalNames[i] = names[i];
                                        finalScores[i] = scores[i];
                                    }
                                    else if (i != scores.Length && scoreOfPlayer2 > scores[i] && !isPlayerPlaced)
                                    {
                                        finalNames[i] = "Player2";
                                        finalScores[i] = scoreOfPlayer2;
                                        isPlayerPlaced = true;
                                    }
                                    else
                                    {
                                        finalNames[i] = names[i - 1];
                                        finalScores[i] = scores[i - 1]; if (scoreOfPlayer2 < scores[scores.Length - 1])
                                        {
                                            finalNames[finalNames.Length - 1] = "Player2";
                                            finalScores[finalScores.Length - 1] = scoreOfPlayer1;
                                            isPlayerPlaced = true;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                // Assigning elements for high score table
                                for (int i = 0; i < finalNames.Length; i++)
                                {
                                    if (i != scores.Length && scoreOfPlayer1 <= scores[i])
                                    {
                                        finalNames[i] = names[i];
                                        finalScores[i] = scores[i];
                                    }
                                    else if (i != scores.Length && scoreOfPlayer1 > scores[i] && !isPlayerPlaced)
                                    {
                                        finalNames[i] = "Player1";
                                        finalScores[i] = scoreOfPlayer1;
                                        isPlayerPlaced = true;
                                    }
                                    else
                                    {
                                        finalNames[i] = names[i - 1];
                                        finalScores[i] = scores[i - 1];
                                        if (scoreOfPlayer1 < scores[scores.Length - 1])
                                        {
                                            finalNames[finalNames.Length - 1] = "Player1";
                                            finalScores[finalScores.Length - 1] = scoreOfPlayer1;
                                            isPlayerPlaced = true;
                                        }
                                    }
                                }
                            }
                            Console.WriteLine("\n\nWinner: " + "Player" + turn + "\n");
                            Console.WriteLine("HIGH SCORE TABLE\n");
                            Console.WriteLine("Name\t Score");
                            for (int i = 0; i < finalNames.Length; i++)
                            {
                                Console.WriteLine(finalNames[i] + "\t " + finalScores[i]);
                            }
                        }
                        break;
                    default:
                        break;
                }

                // Tie. If all arrays are full(has 15 elements) 
                if (!isPlayerPlaced && (A3Counter == 15 && A1Counter == 15 && A2Counter == 15))
                {
                    Console.WriteLine("\n\nTie\n");
                    Console.WriteLine("Name\t Score");
                    for (int i = 0; i < names.Length; i++)
                    {
                        Console.WriteLine(names[i] + "\t" + scores[i]);
                    }
                    isGameOver = true;
                }

                // Switching Player
                if (turn == 2) turn--;
                else turn++;

                Console.ReadLine();
            }
        }

        // I used a function here, because I didn't want to copy paste it several times.
        private static bool IsGameFinished(char[] A1, char[] A2, char[] A3, int arrayChooser, int elementCounter)
        {// here, I used a variable called elementCounter which is equal to my array counter(A1Counter or A2Counter or A3Counter)

         /* Assume that, I just added a char to array1, and it is 4th element.
          * I don't need to control whole array, I just need to control the elements whose neighbor.
          * Therfore, I just need to control 2th to 6th elements. ( * ones)
          *                     
          *                     
          *indexes =    0 1 2 3 4 5 6 7 8 9 
          *A1 =             * * D * * 
          *A2 =             * * * * *
          *A3 =             * * * * *
          * 
          *     That is why I used such an elementCounter variable.
          */
            switch (arrayChooser)
            {
                case 1:
                    for (int i = elementCounter - 3; i < elementCounter + 3; i++)
                    {
                        if (i >= 0)
                        {   // Horizontal Check
                            if (i < 13 && ((A1[i] == 'D' && A1[i + 1] == 'E' && A1[i + 2] == 'U') || (A1[i] == 'U' && A1[i + 1] == 'E' && A1[i + 2] == 'D')))
                            {
                                return true;
                            }
                            // Vertical Check
                            else if (i < 13 && ((A1[i] == 'D' && A2[i] == 'E' && A3[i] == 'U') || (A1[i] == 'U' && A2[i] == 'E' && A3[i] == 'D')))
                            {
                                return true;
                            }
                            // Diagonal Check
                            else if (i < 13 && ((A1[i] == 'D' && A2[i + 1] == 'E' && A3[i + 2] == 'U') || (A1[i] == 'U' && A2[i + 1] == 'E' && A3[i + 2] == 'D') || (A3[i] == 'D' && A2[i + 1] == 'E' && A1[i + 2] == 'U') || (A3[i] == 'U' && A2[i + 1] == 'E' && A1[i + 2] == 'D')))
                            {
                                return true;
                            }
                        }

                    }
                    break;
                case 2:
                    for (int i = elementCounter - 3; i < elementCounter + 3; i++)
                    {
                        if (i >= 0)
                        {   // Horizontal Check
                            if (i < 13 && ((A2[i] == 'D' && A2[i + 1] == 'E' && A2[i + 2] == 'U') || (A2[i] == 'U' && A2[i + 1] == 'E' && A2[i + 2] == 'D')))
                            {
                                return true;
                            }
                            // Vertical Check
                            else if (i < 13 && ((A1[i] == 'D' && A2[i] == 'E' && A3[i] == 'U') || (A1[i] == 'U' && A2[i] == 'E' && A3[i] == 'D')))
                            {
                                return true;
                            }
                            // Diagonal Check
                            else if (i > 0 && i < 13 && ((A1[i - 1] == 'D' && A2[i] == 'E' && A3[i + 1] == 'U') || (A1[i - 1] == 'U' && A2[i] == 'E' && A3[i + 1] == 'D') || (A3[i - 1] == 'D' && A2[i] == 'E' && A1[i + 1] == 'U') || (A3[i - 1] == 'U' && A2[i] == 'E' && A1[i + 1] == 'D')))
                            {
                                return true;
                            }
                        }

                    }
                    break;
                case 3:
                    for (int i = elementCounter - 3; i < elementCounter + 3; i++)
                    {
                        if (i >= 0)
                        {   // Horizontal Check
                            if (i < 13 && ((A3[i] == 'D' && A3[i + 1] == 'E' && A3[i + 2] == 'U') || (A3[i] == 'U' && A3[i + 1] == 'E' && A3[i + 2] == 'D')))
                            {
                                return true;
                            }
                            // Vertical Check
                            else if (i < 13 && ((A1[i] == 'D' && A2[i] == 'E' && A3[i] == 'U') || (A1[i] == 'U' && A2[i] == 'E' && A3[i] == 'D')))
                            {
                                return true;
                            }
                            // Diagonal Check
                            else if (i < 13 && ((A1[i] == 'D' && A2[i + 1] == 'E' && A3[i + 2] == 'U') || (A1[i] == 'U' && A2[i + 1] == 'E' && A3[i + 2] == 'D') || (A3[i] == 'D' && A2[i + 1] == 'E' && A1[i + 2] == 'U') || (A3[i] == 'U' && A2[i + 1] == 'E' && A1[i + 2] == 'D')))
                            {
                                return true;
                            }
                        }

                    }
                    break;
                default:
                    return false;
            }
            return false;
        }
    }
}
