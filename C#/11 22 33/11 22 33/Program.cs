
/*                                                          MADE BY
 *                                                          2020510137 EMRE ÖZKAYA
                                                            2020510079 BURAK ÇOLAK
                                                            2020510011 ARDA AYDIN
 */






using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Media;

namespace asd
{
    class Program
    {
        static void Main(string[] args)
        {



            bool menu = true;
            ConsoleKeyInfo choice;
            string Music_Mod = "ON";
            bool isMusicPlaying = false;


            while (menu == true)
            {
                //MENU PART
                Console.Clear();
                Console.SetCursorPosition(52, 1);
                System.Console.WriteLine("*** MENU ***");
                Console.SetCursorPosition(52, 3);
                System.Console.WriteLine("--------------");
                Console.SetCursorPosition(52, 4);
                System.Console.WriteLine(" 1 Start Game");
                Console.SetCursorPosition(52, 5);
                System.Console.WriteLine("--------------");

                Console.SetCursorPosition(52, 7);
                System.Console.WriteLine("---------------");
                Console.SetCursorPosition(52, 8);
                System.Console.WriteLine(" 2 How To Play");
                Console.SetCursorPosition(52, 9);
                System.Console.WriteLine("---------------");

                Console.SetCursorPosition(52, 11);
                System.Console.WriteLine("---------------");
                Console.SetCursorPosition(52, 12);
                System.Console.WriteLine(" 3 Settings");
                Console.SetCursorPosition(52, 13);
                System.Console.WriteLine("---------------");

                Console.SetCursorPosition(52, 15);
                System.Console.WriteLine("--------------");
                Console.SetCursorPosition(52, 16);
                System.Console.WriteLine(" 4 Credits By");
                Console.SetCursorPosition(52, 17);
                System.Console.WriteLine("--------------");

                Console.SetCursorPosition(54, 19);
                System.Console.WriteLine("---------");
                Console.SetCursorPosition(54, 20);
                System.Console.WriteLine(" 5 Exit");
                Console.SetCursorPosition(54, 21);
                System.Console.WriteLine("---------");

                //Sound Codes

                //SoundPlayer menu_music = new SoundPlayer();
                //string yol;
                //yol = @" C:\Users\Burak\Desktop\oyunmusic.wav";
                //menu_music.SoundLocation = yol;
                //if (!isMusicPlaying)
                //{
                //    menu_music.Play();
                //    isMusicPlaying = true;
                //}
                //SoundPlayer eslesmüz = new SoundPlayer();
                //string yol2;
                //yol2 = @" C:\Users\Burak\Desktop\eslesmüz.wav";
                //eslesmüz.SoundLocation = yol2;


                choice = Console.ReadKey(true);



                switch (choice.Key)
                {
                    case ConsoleKey.D1:
                        {

                            bool isThereAnyMatchedNumber;

                            Random random = new Random();
                            int[] row1 = new int[32];
                            int[] row2 = new int[32];
                            int[] row3 = new int[32];
                            row1[0] = 0;
                            row2[0] = 0;
                            row3[0] = 0;

                            ConsoleKeyInfo command;
                            int x = 0;
                            int constant = 0;
                            int cursorX = 17;
                            int cursorY = 6;
                            int leftSide = cursorX - 18;
                            int rightSide = cursorX - 16;
                            int indexOfNum = cursorX - 17;
                            int score = 0;
                            //Generating random number elements for arrays
                            int firstRowElements;
                            int secondRowElements;
                            int thirdRowElements;
                            int total;
                            // The sum of the number of elements in each row must be 30
                            do
                            {
                                firstRowElements = random.Next(8, 13);
                                secondRowElements = random.Next(8, 13);
                                thirdRowElements = random.Next(8, 13);
                                total = firstRowElements + secondRowElements + thirdRowElements;
                            } while (total != 30);


                            //if (Music_Mod == "ON")
                            //{
                            //    menu_music.PlayLooping();
                            //}

                            Console.Clear();
                            //game area
                            Console.SetCursorPosition(16, 5);
                            Console.WriteLine("+------------------------------+");
                            for (int i = 0; i < 3; i++)
                            {
                                Console.SetCursorPosition(16, 5 + i + 1);
                                Console.WriteLine("|                              |");
                            }
                            Console.SetCursorPosition(16, 9);
                            Console.WriteLine("+------------------------------+");

                            int[] row1Temp = new int[1000];
                            for (int i = 0; i < firstRowElements; i++)
                            {
                                constant = 0;

                                x = random.Next(17, 47);
                                while (constant < i)
                                {

                                    if (row1Temp[constant] == x)
                                    {
                                        x = random.Next(17, 47);
                                        constant = -1;
                                    }
                                    constant = constant + 1;
                                }
                                row1Temp[i] = x;
                            }

                            int[] row2Temp = new int[1000];

                            for (int i = 0; i < secondRowElements; i++)
                            {
                                constant = 0;
                                x = random.Next(17, 47);
                                while (constant < i)
                                {

                                    if (row2Temp[constant] == x)
                                    {
                                        x = random.Next(17, 47);
                                        constant = -1;
                                    }
                                    constant = constant + 1;
                                }
                                row2Temp[i] = x;
                            }

                            int[] row3Temp = new int[1000];
                            for (int i = 0; i < thirdRowElements; i++)
                            {
                                constant = 0;

                                x = random.Next(17, 47);
                                while (constant < i)
                                {
                                    if (row3Temp[constant] == x)
                                    {
                                        x = random.Next(17, 47);
                                        constant = -1;
                                    }
                                    constant = constant + 1;
                                }
                                row3Temp[i] = x;
                            }

                            //Writing element of first row
                            for (int i = 0; i < row1.Length; i++)
                            {
                                for (int j = 0; j < firstRowElements; j++)
                                {
                                    if (i == j)
                                    {
                                        int index = row1Temp[i] - 17;
                                        row1[index + 1] = random.Next(1, 4);
                                    }
                                }
                            }
                            Console.SetCursorPosition(17, 6);
                            for (int i = 1; i < row1.Length - 1; i++)
                            {
                                if (row1[i] != 0)
                                {
                                    Console.Write(row1[i]);
                                }
                                else
                                {
                                    System.Console.Write(" ");
                                }
                            }

                            //Writing element of second row
                            for (int i = 0; i < row2.Length; i++)
                            {
                                for (int j = 0; j < secondRowElements; j++)
                                {
                                    if (i == j)
                                    {
                                        int index = row2Temp[i] - 17;
                                        row2[index + 1] = random.Next(1, 4);
                                    }
                                }
                            }

                            Console.SetCursorPosition(17, 7);
                            for (int i = 1; i < row2.Length - 1; i++)
                            {
                                if (row2[i] != 0)
                                {
                                    Console.Write(row2[i]);
                                }
                                else
                                {
                                    System.Console.Write(" ");
                                }
                            }

                            //Writing element of third row
                            for (int i = 0; i < row3.Length; i++)
                            {
                                for (int j = 0; j < thirdRowElements; j++)
                                {
                                    if (i == j)
                                    {
                                        int index = row3Temp[i] - 17;
                                        row3[index + 1] = random.Next(1, 4);
                                    }
                                }
                            }
                            Console.SetCursorPosition(17, 8);
                            for (int i = 1; i < row3.Length - 1; i++)
                            {
                                if (row3[i] != 0)
                                {
                                    Console.Write(row3[i]);
                                }
                                else
                                {
                                    System.Console.Write(" ");
                                }
                            }


                            // Matching Control

                            do
                            {
                                isThereAnyMatchedNumber = false;
                                for (int i = 0; i < row1.Length - 1; i++)
                                {
                                    if (row1[i] != 0 && row1[i] == row1[i + 1])
                                    {
                                        isThereAnyMatchedNumber = true;
                                        score += 10;
                                        Console.SetCursorPosition(50, 7);
                                        Console.WriteLine("Score : " + score);
                                        row1[i] = 0;
                                        row1[i + 1] = 0;
                                        int randomRowChooser = random.Next(1, 4);
                                        switch (randomRowChooser)
                                        {
                                            case 1:
                                                for (int j = 0; j < 2; j++)
                                                {
                                                    int randomPosChooser = random.Next(1, 31);
                                                    if (row1[randomPosChooser] == 0)
                                                    {
                                                        row1[randomPosChooser] = random.Next(1, 4);
                                                    }
                                                    else j--;
                                                }
                                                break;
                                            case 2:
                                                for (int j = 0; j < 2; j++)
                                                {
                                                    int randomPosChooser = random.Next(1, 31);
                                                    if (row2[randomPosChooser] == 0)
                                                    {
                                                        row2[randomPosChooser] = random.Next(1, 4);
                                                    }
                                                    else j--;
                                                }
                                                break;
                                            case 3:
                                                for (int j = 0; j < 2; j++)
                                                {
                                                    int randomPosChooser = random.Next(1, 31);
                                                    if (row3[randomPosChooser] == 0)
                                                    {
                                                        row3[randomPosChooser] = random.Next(1, 4);
                                                    }
                                                    else j--;
                                                }
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                                for (int i = 0; i < row2.Length - 1; i++)
                                {
                                    if (row2[i] != 0 && row2[i] == row2[i + 1])
                                    {
                                        isThereAnyMatchedNumber = true;
                                        score += 10;
                                        Console.SetCursorPosition(50, 7);
                                        Console.WriteLine("Score : " + score);
                                        row2[i] = 0;
                                        row2[i + 1] = 0;
                                        int randomRowChooser = random.Next(1, 4);
                                        switch (randomRowChooser)
                                        {
                                            case 1:
                                                for (int j = 0; j < 2; j++)
                                                {
                                                    int randomPosChooser = random.Next(1, 31);
                                                    if (row1[randomPosChooser] == 0)
                                                    {
                                                        row1[randomPosChooser] = random.Next(1, 4);
                                                    }
                                                    else j--;

                                                }
                                                break;
                                            case 2:
                                                for (int j = 0; j < 2; j++)
                                                {
                                                    int randomPosChooser = random.Next(1, 31);
                                                    if (row2[randomPosChooser] == 0)
                                                    {
                                                        row2[randomPosChooser] = random.Next(1, 4);
                                                    }
                                                    else j--;
                                                }
                                                break;
                                            case 3:
                                                for (int j = 0; j < 2; j++)
                                                {
                                                    int randomPosChooser = random.Next(1, 31);
                                                    if (row3[randomPosChooser] == 0)
                                                    {
                                                        row3[randomPosChooser] = random.Next(1, 4);
                                                    }
                                                    else j--;
                                                }
                                                break;
                                            default:
                                                break;
                                        }
                                    }


                                }
                                for (int i = 0; i < row3.Length - 1; i++)
                                {
                                    if (row3[i] != 0 && row3[i] == row3[i + 1])
                                    {
                                        isThereAnyMatchedNumber = true;
                                        score += 10;
                                        Console.SetCursorPosition(50, 7);
                                        Console.WriteLine("Score : " + score);
                                        row3[i] = 0;
                                        row3[i + 1] = 0;
                                        int randomRowChooser = random.Next(1, 4);
                                        switch (randomRowChooser)
                                        {
                                            case 1:
                                                for (int j = 0; j < 2; j++)
                                                {
                                                    int randomPosChooser = random.Next(1, 31);
                                                    if (row1[randomPosChooser] == 0)
                                                    {
                                                        row1[randomPosChooser] = random.Next(1, 4);
                                                    }
                                                    else j--;
                                                }
                                                break;
                                            case 2:
                                                for (int j = 0; j < 2; j++)
                                                {
                                                    int randomPosChooser = random.Next(1, 31);
                                                    if (row2[randomPosChooser] == 0)
                                                    {
                                                        row2[randomPosChooser] = random.Next(1, 4);
                                                    }
                                                    else j--;
                                                }
                                                break;
                                            case 3:
                                                for (int j = 0; j < 2; j++)
                                                {
                                                    int randomPosChooser = random.Next(1, 31);
                                                    if (row3[randomPosChooser] == 0)
                                                    {
                                                        row3[randomPosChooser] = random.Next(1, 4);
                                                    }
                                                    else j--;
                                                }
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            } while (isThereAnyMatchedNumber);


                            Console.SetCursorPosition(17, 6);
                            for (int i = 1; i < row1.Length - 1; i++)
                            {
                                if (row1[i] != 0)
                                {
                                    Console.Write(row1[i]);
                                }
                                else
                                {
                                    System.Console.Write(" ");
                                }
                            }

                            Console.SetCursorPosition(17, 7);
                            for (int i = 1; i < row2.Length - 1; i++)
                            {
                                if (row2[i] != 0)
                                {
                                    Console.Write(row2[i]);
                                }
                                else
                                {
                                    System.Console.Write(" ");
                                }
                            }
                            Console.SetCursorPosition(17, 8);
                            for (int i = 1; i < row3.Length - 1; i++)
                            {
                                if (row3[i] != 0)
                                {
                                    Console.Write(row3[i]);
                                }
                                else
                                {
                                    System.Console.Write(" ");
                                }
                            }



                            // Exit
                            Console.SetCursorPosition(15, 12);
                            Console.WriteLine("Press ESC button to exit.");
                            Console.SetCursorPosition(15, 14);
                            Console.WriteLine("Press M button to return to main menu.");



                            Console.SetCursorPosition(cursorX, cursorY);
                            while (true)
                            {
                                if (Console.KeyAvailable)
                                { // true: there is a key in keyboard buffer
                                    command = Console.ReadKey(true); // true: do not write character 
                                    switch (command.Key)
                                    {
                                        case ConsoleKey.RightArrow:
                                            //key control
                                            if (cursorX == 46) // boundary control
                                            {
                                                cursorX -= 29;
                                            }
                                            else
                                            {
                                                cursorX++;
                                            }
                                            break;
                                        case ConsoleKey.LeftArrow:
                                            //key control
                                            if (cursorX == 17) // boundary control
                                            {
                                                cursorX += 29;
                                            }
                                            else
                                            {
                                                cursorX--;
                                            }
                                            break;
                                        case ConsoleKey.UpArrow:
                                            //key control
                                            if (cursorY == 6) // boundary control
                                            {
                                                cursorY += 2;
                                            }
                                            else
                                            {
                                                cursorY--;
                                            }
                                            break;
                                        case ConsoleKey.DownArrow:
                                            //key control
                                            if (cursorY == 8) // boundary control
                                            {
                                                cursorY -= 2;
                                            }
                                            else
                                            {
                                                cursorY++;
                                            }
                                            break;
                                        case ConsoleKey.S:
                                            switch (cursorY)
                                            {
                                                case 6:
                                                    leftSide = cursorX - 17;
                                                    rightSide = cursorX - 15;
                                                    indexOfNum = cursorX - 16;
                                                    if ((row2[indexOfNum] == 0) && row1[indexOfNum] != 0)
                                                    {
                                                        if (row1[leftSide] == 0 && row1[rightSide] == 0)
                                                        {
                                                            int tempValue = row1[indexOfNum];
                                                            row1[indexOfNum] = 0;
                                                            row2[indexOfNum] = tempValue;
                                                            Console.Write(" ");
                                                            cursorY++;
                                                            Console.SetCursorPosition(cursorX, cursorY);
                                                            Console.Write(tempValue);
                                                        }

                                                    }
                                                    break;
                                                case 7:
                                                    leftSide = cursorX - 17;
                                                    rightSide = cursorX - 15;
                                                    indexOfNum = cursorX - 16;
                                                    if ((row3[indexOfNum] == 0) && row2[indexOfNum] != 0)
                                                    {
                                                        if (row2[leftSide] == 0 && row2[rightSide] == 0)
                                                        {

                                                            int tempValue = row2[indexOfNum];
                                                            row2[indexOfNum] = 0;
                                                            row3[indexOfNum] = tempValue;
                                                            Console.Write(" ");
                                                            cursorY++;
                                                            Console.SetCursorPosition(cursorX, cursorY);
                                                            Console.Write(tempValue);
                                                        }

                                                    }
                                                    break;
                                                default:
                                                    break;
                                            }
                                            do
                                            {
                                                isThereAnyMatchedNumber = false;
                                                for (int i = 0; i < row1.Length - 1; i++)
                                                {
                                                    if (row1[i] != 0 && row1[i] == row1[i + 1])
                                                    {
                                                        //eslesmüz.Play();
                                                        isThereAnyMatchedNumber = true;
                                                        score += 10;
                                                        Console.SetCursorPosition(50, 7);
                                                        Console.WriteLine("Score : " + score);
                                                        row1[i] = 0;
                                                        row1[i + 1] = 0;
                                                        int randomRowChooser = random.Next(1, 4);
                                                        switch (randomRowChooser)
                                                        {
                                                            case 1:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row1[randomPosChooser] == 0)
                                                                    {
                                                                        row1[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 2:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row2[randomPosChooser] == 0)
                                                                    {
                                                                        row2[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 3:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row3[randomPosChooser] == 0)
                                                                    {
                                                                        row3[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                    }
                                                }
                                                for (int i = 0; i < row2.Length - 1; i++)
                                                {
                                                    if (row2[i] != 0 && row2[i] == row2[i + 1])
                                                    {
                                                        //eslesmüz.Play();
                                                        isThereAnyMatchedNumber = true;
                                                        score += 10;
                                                        Console.SetCursorPosition(50, 7);
                                                        Console.WriteLine("Score : " + score);
                                                        row2[i] = 0;
                                                        row2[i + 1] = 0;
                                                        int randomRowChooser = random.Next(1, 4);
                                                        switch (randomRowChooser)
                                                        {
                                                            case 1:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row1[randomPosChooser] == 0)
                                                                    {
                                                                        row1[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;

                                                                }
                                                                break;
                                                            case 2:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row2[randomPosChooser] == 0)
                                                                    {
                                                                        row2[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 3:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row3[randomPosChooser] == 0)
                                                                    {
                                                                        row3[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                    }


                                                }
                                                for (int i = 0; i < row3.Length - 1; i++)
                                                {
                                                    if (row3[i] != 0 && row3[i] == row3[i + 1])
                                                    {
                                                        //eslesmüz.Play();
                                                        isThereAnyMatchedNumber = true;
                                                        score += 10;
                                                        Console.SetCursorPosition(50, 7);
                                                        Console.WriteLine("Score : " + score);
                                                        row3[i] = 0;
                                                        row3[i + 1] = 0;
                                                        int randomRowChooser = random.Next(1, 4);
                                                        switch (randomRowChooser)
                                                        {
                                                            case 1:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row1[randomPosChooser] == 0)
                                                                    {
                                                                        row1[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 2:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row2[randomPosChooser] == 0)
                                                                    {
                                                                        row2[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 3:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row3[randomPosChooser] == 0)
                                                                    {
                                                                        row3[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                    }
                                                }
                                            } while (isThereAnyMatchedNumber);
                                            Console.SetCursorPosition(17, 6);
                                            for (int i = 1; i < row1.Length - 1; i++)
                                            {
                                                if (row1[i] != 0)
                                                {
                                                    Console.Write(row1[i]);
                                                }
                                                else
                                                {
                                                    System.Console.Write(" ");
                                                }
                                            }
                                            Console.SetCursorPosition(17, 7);
                                            for (int i = 1; i < row2.Length - 1; i++)
                                            {
                                                if (row2[i] != 0)
                                                {
                                                    Console.Write(row2[i]);
                                                }
                                                else
                                                {
                                                    System.Console.Write(" ");
                                                }
                                            }
                                            Console.SetCursorPosition(17, 8);
                                            for (int i = 1; i < row3.Length - 1; i++)
                                            {
                                                if (row3[i] != 0)
                                                {
                                                    Console.Write(row3[i]);
                                                }
                                                else
                                                {
                                                    System.Console.Write(" ");
                                                }
                                            }
                                            break;
                                        case ConsoleKey.W:
                                            switch (cursorY)
                                            {
                                                case 8:
                                                    leftSide = cursorX - 17;
                                                    rightSide = cursorX - 15;
                                                    indexOfNum = cursorX - 16;
                                                    if (row2[indexOfNum] == 0 && row3[indexOfNum] != 0)
                                                    {

                                                        if ((row3[leftSide] == 0 && row3[rightSide] == 0))
                                                        {
                                                            int tempValue = row3[indexOfNum];
                                                            row3[indexOfNum] = 0;
                                                            row2[indexOfNum] = tempValue;
                                                            Console.Write(" ");
                                                            cursorY--;
                                                            Console.SetCursorPosition(cursorX, cursorY);
                                                            Console.Write(tempValue);

                                                        }

                                                    }
                                                    break;
                                                case 7:
                                                    leftSide = cursorX - 17;
                                                    rightSide = cursorX - 15;
                                                    indexOfNum = cursorX - 16;
                                                    if (row1[indexOfNum] == 0 && row2[indexOfNum] != 0)
                                                    {
                                                        if (row2[leftSide] == 0 && row2[rightSide] == 0)
                                                        {

                                                            int tempValue = row2[indexOfNum];
                                                            row2[indexOfNum] = 0;
                                                            row1[indexOfNum] = tempValue;
                                                            Console.Write(" ");
                                                            cursorY--;
                                                            Console.SetCursorPosition(cursorX, cursorY);
                                                            Console.Write(tempValue);
                                                        }

                                                    }
                                                    break;
                                                default:
                                                    break;
                                            }
                                            do
                                            {
                                                isThereAnyMatchedNumber = false;
                                                for (int i = 0; i < row1.Length - 1; i++)
                                                {
                                                    if (row1[i] != 0 && row1[i] == row1[i + 1])
                                                    {
                                                        //eslesmüz.Play();
                                                        isThereAnyMatchedNumber = true;
                                                        score += 10;
                                                        Console.SetCursorPosition(50, 7);
                                                        Console.WriteLine("Score : " + score);
                                                        row1[i] = 0;
                                                        row1[i + 1] = 0;
                                                        int randomRowChooser = random.Next(1, 4);
                                                        switch (randomRowChooser)
                                                        {
                                                            case 1:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row1[randomPosChooser] == 0)
                                                                    {
                                                                        row1[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 2:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row2[randomPosChooser] == 0)
                                                                    {
                                                                        row2[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 3:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row3[randomPosChooser] == 0)
                                                                    {
                                                                        row3[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                    }
                                                }
                                                for (int i = 0; i < row2.Length - 1; i++)
                                                {
                                                    if (row2[i] != 0 && row2[i] == row2[i + 1])
                                                    {
                                                        //eslesmüz.Play();
                                                        isThereAnyMatchedNumber = true;
                                                        score += 10;
                                                        Console.SetCursorPosition(50, 7);
                                                        Console.WriteLine("Score : " + score);
                                                        row2[i] = 0;
                                                        row2[i + 1] = 0;
                                                        int randomRowChooser = random.Next(1, 4);
                                                        switch (randomRowChooser)
                                                        {
                                                            case 1:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row1[randomPosChooser] == 0)
                                                                    {
                                                                        row1[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;

                                                                }
                                                                break;
                                                            case 2:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row2[randomPosChooser] == 0)
                                                                    {
                                                                        row2[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 3:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row3[randomPosChooser] == 0)
                                                                    {
                                                                        row3[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                    }


                                                }
                                                for (int i = 0; i < row3.Length - 1; i++)
                                                {
                                                    if (row3[i] != 0 && row3[i] == row3[i + 1])
                                                    {
                                                        //eslesmüz.Play();
                                                        isThereAnyMatchedNumber = true;
                                                        score += 10;
                                                        Console.SetCursorPosition(50, 7);
                                                        Console.WriteLine("Score : " + score);
                                                        row3[i] = 0;
                                                        row3[i + 1] = 0;
                                                        int randomRowChooser = random.Next(1, 4);
                                                        switch (randomRowChooser)
                                                        {
                                                            case 1:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row1[randomPosChooser] == 0)
                                                                    {
                                                                        row1[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 2:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row2[randomPosChooser] == 0)
                                                                    {
                                                                        row2[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 3:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row3[randomPosChooser] == 0)
                                                                    {
                                                                        row3[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                    }
                                                }
                                            } while (isThereAnyMatchedNumber);
                                            Console.SetCursorPosition(17, 6);
                                            for (int i = 1; i < row1.Length - 1; i++)
                                            {
                                                if (row1[i] != 0)
                                                {
                                                    Console.Write(row1[i]);
                                                }
                                                else
                                                {
                                                    System.Console.Write(" ");
                                                }
                                            }
                                            Console.SetCursorPosition(17, 7);
                                            for (int i = 1; i < row2.Length - 1; i++)
                                            {
                                                if (row2[i] != 0)
                                                {
                                                    Console.Write(row2[i]);
                                                }
                                                else
                                                {
                                                    System.Console.Write(" ");
                                                }
                                            }
                                            Console.SetCursorPosition(17, 8);
                                            for (int i = 1; i < row3.Length - 1; i++)
                                            {
                                                if (row3[i] != 0)
                                                {
                                                    Console.Write(row3[i]);
                                                }
                                                else
                                                {
                                                    System.Console.Write(" ");
                                                }
                                            }
                                            break;
                                        case ConsoleKey.A:
                                            switch (cursorY)
                                            {
                                                case 6:
                                                    while (true)
                                                    {
                                                        leftSide = cursorX - 17;
                                                        rightSide = cursorX - 15;
                                                        indexOfNum = cursorX - 16;
                                                        if (row1[indexOfNum] != 0 && indexOfNum != 1 && row1[leftSide] == 0 && row1[rightSide] == 0)
                                                        {
                                                            int tempValue = row1[indexOfNum];
                                                            row1[indexOfNum] = 0;
                                                            row1[leftSide] = tempValue;
                                                            cursorX--;
                                                        }
                                                        else
                                                        {
                                                            break;
                                                        }
                                                    }
                                                    break;
                                                case 7:
                                                    while (true)
                                                    {
                                                        leftSide = cursorX - 17;
                                                        rightSide = cursorX - 15;
                                                        indexOfNum = cursorX - 16;
                                                        if (row2[indexOfNum] != 0 && indexOfNum != 1 && row2[leftSide] == 0 && row2[rightSide] == 0)
                                                        {
                                                            int tempValue = row2[indexOfNum];
                                                            row2[indexOfNum] = 0;
                                                            row2[leftSide] = tempValue;
                                                            cursorX--;
                                                        }
                                                        else
                                                        {
                                                            break;
                                                        }
                                                    }
                                                    break;
                                                case 8:
                                                    while (true)
                                                    {
                                                        leftSide = cursorX - 17;
                                                        rightSide = cursorX - 15;
                                                        indexOfNum = cursorX - 16;
                                                        if (row3[indexOfNum] != 0 && indexOfNum != 1 && row3[leftSide] == 0 && row3[rightSide] == 0)
                                                        {
                                                            int tempValue = row3[indexOfNum];
                                                            row3[indexOfNum] = 0;
                                                            row3[leftSide] = tempValue;
                                                            cursorX--;
                                                        }
                                                        else
                                                        {
                                                            break;
                                                        }
                                                    }
                                                    break;
                                                default:
                                                    break;
                                            }
                                            do
                                            {
                                                isThereAnyMatchedNumber = false;
                                                for (int i = 0; i < row1.Length - 1; i++)
                                                {
                                                    if (row1[i] != 0 && row1[i] == row1[i + 1])
                                                    {
                                                        //eslesmüz.Play();
                                                        isThereAnyMatchedNumber = true;
                                                        score += 10;
                                                        Console.SetCursorPosition(50, 7);
                                                        Console.WriteLine("Score : " + score);
                                                        row1[i] = 0;
                                                        row1[i + 1] = 0;
                                                        int randomRowChooser = random.Next(1, 4);
                                                        switch (randomRowChooser)
                                                        {
                                                            case 1:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row1[randomPosChooser] == 0)
                                                                    {
                                                                        row1[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 2:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row2[randomPosChooser] == 0)
                                                                    {
                                                                        row2[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 3:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row3[randomPosChooser] == 0)
                                                                    {
                                                                        row3[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                    }
                                                }
                                                for (int i = 0; i < row2.Length - 1; i++)
                                                {
                                                    if (row2[i] != 0 && row2[i] == row2[i + 1])
                                                    {
                                                        //eslesmüz.Play();
                                                        isThereAnyMatchedNumber = true;
                                                        score += 10;
                                                        Console.SetCursorPosition(50, 7);
                                                        Console.WriteLine("Score : " + score);
                                                        row2[i] = 0;
                                                        row2[i + 1] = 0;
                                                        int randomRowChooser = random.Next(1, 4);
                                                        switch (randomRowChooser)
                                                        {
                                                            case 1:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row1[randomPosChooser] == 0)
                                                                    {
                                                                        row1[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;

                                                                }
                                                                break;
                                                            case 2:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row2[randomPosChooser] == 0)
                                                                    {
                                                                        row2[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 3:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row3[randomPosChooser] == 0)
                                                                    {
                                                                        row3[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                    }


                                                }
                                                for (int i = 0; i < row3.Length - 1; i++)
                                                {
                                                    if (row3[i] != 0 && row3[i] == row3[i + 1])
                                                    {
                                                        //eslesmüz.Play();
                                                        isThereAnyMatchedNumber = true;
                                                        score += 10;
                                                        Console.SetCursorPosition(50, 7);
                                                        Console.WriteLine("Score : " + score);
                                                        row3[i] = 0;
                                                        row3[i + 1] = 0;
                                                        int randomRowChooser = random.Next(1, 4);
                                                        switch (randomRowChooser)
                                                        {
                                                            case 1:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row1[randomPosChooser] == 0)
                                                                    {
                                                                        row1[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 2:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row2[randomPosChooser] == 0)
                                                                    {
                                                                        row2[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 3:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row3[randomPosChooser] == 0)
                                                                    {
                                                                        row3[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                    }
                                                }
                                            } while (isThereAnyMatchedNumber);
                                            Console.SetCursorPosition(17, 6);
                                            for (int i = 1; i < row1.Length - 1; i++)
                                            {
                                                if (row1[i] != 0)
                                                {
                                                    Console.Write(row1[i]);
                                                }
                                                else
                                                {
                                                    System.Console.Write(" ");
                                                }
                                            }
                                            Console.SetCursorPosition(17, 7);
                                            for (int i = 1; i < row2.Length - 1; i++)
                                            {
                                                if (row2[i] != 0)
                                                {
                                                    Console.Write(row2[i]);
                                                }
                                                else
                                                {
                                                    System.Console.Write(" ");
                                                }
                                            }
                                            Console.SetCursorPosition(17, 8);
                                            for (int i = 1; i < row3.Length - 1; i++)
                                            {
                                                if (row3[i] != 0)
                                                {
                                                    Console.Write(row3[i]);
                                                }
                                                else
                                                {
                                                    System.Console.Write(" ");
                                                }
                                            }
                                            break;
                                        case ConsoleKey.D:
                                            switch (cursorY)
                                            {
                                                case 6:
                                                    while (true)
                                                    {
                                                        leftSide = cursorX - 17;
                                                        rightSide = cursorX - 15;
                                                        indexOfNum = cursorX - 16;
                                                        if (row1[indexOfNum] != 0 && indexOfNum != 30 && row1[leftSide] == 0 && row1[rightSide] == 0)
                                                        {
                                                            int tempValue = row1[indexOfNum];
                                                            row1[indexOfNum] = 0;
                                                            row1[rightSide] = tempValue;
                                                            cursorX++;
                                                        }
                                                        else
                                                        {
                                                            break;
                                                        }
                                                    }
                                                    break;
                                                case 7:
                                                    while (true)
                                                    {
                                                        leftSide = cursorX - 17;
                                                        rightSide = cursorX - 15;
                                                        indexOfNum = cursorX - 16;
                                                        if (row2[indexOfNum] != 0 && indexOfNum != 30 && row2[leftSide] == 0 && row2[rightSide] == 0)
                                                        {
                                                            int tempValue = row2[indexOfNum];
                                                            row2[indexOfNum] = 0;
                                                            row2[rightSide] = tempValue;
                                                            cursorX++;
                                                        }
                                                        else
                                                        {
                                                            break;
                                                        }
                                                    }
                                                    break;
                                                case 8:
                                                    while (true)
                                                    {
                                                        leftSide = cursorX - 17;
                                                        rightSide = cursorX - 15;
                                                        indexOfNum = cursorX - 16;
                                                        if (row3[indexOfNum] != 0 && indexOfNum != 30 && row3[leftSide] == 0 && row3[rightSide] == 0)
                                                        {
                                                            int tempValue = row3[indexOfNum];
                                                            row3[indexOfNum] = 0;
                                                            row3[rightSide] = tempValue;
                                                            cursorX++;
                                                        }
                                                        else
                                                        {
                                                            break;
                                                        }
                                                    }
                                                    break;
                                                default:
                                                    break;
                                            }
                                            do
                                            {
                                                isThereAnyMatchedNumber = false;
                                                for (int i = 0; i < row1.Length - 1; i++)
                                                {
                                                    if (row1[i] != 0 && row1[i] == row1[i + 1])
                                                    {
                                                        //eslesmüz.Play();
                                                        isThereAnyMatchedNumber = true;
                                                        score += 10;
                                                        Console.SetCursorPosition(50, 7);
                                                        Console.WriteLine("Score : " + score);
                                                        row1[i] = 0;
                                                        row1[i + 1] = 0;
                                                        int randomRowChooser = random.Next(1, 4);
                                                        switch (randomRowChooser)
                                                        {
                                                            case 1:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row1[randomPosChooser] == 0)
                                                                    {
                                                                        row1[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 2:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row2[randomPosChooser] == 0)
                                                                    {
                                                                        row2[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 3:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row3[randomPosChooser] == 0)
                                                                    {
                                                                        row3[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                    }
                                                }
                                                for (int i = 0; i < row2.Length - 1; i++)
                                                {
                                                    if (row2[i] != 0 && row2[i] == row2[i + 1])
                                                    {
                                                        //eslesmüz.Play();
                                                        isThereAnyMatchedNumber = true;
                                                        score += 10;
                                                        Console.SetCursorPosition(50, 7);
                                                        Console.WriteLine("Score : " + score);
                                                        row2[i] = 0;
                                                        row2[i + 1] = 0;
                                                        int randomRowChooser = random.Next(1, 4);
                                                        switch (randomRowChooser)
                                                        {
                                                            case 1:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row1[randomPosChooser] == 0)
                                                                    {
                                                                        row1[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;

                                                                }
                                                                break;
                                                            case 2:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row2[randomPosChooser] == 0)
                                                                    {
                                                                        row2[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 3:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row3[randomPosChooser] == 0)
                                                                    {
                                                                        row3[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                    }


                                                }
                                                for (int i = 0; i < row3.Length - 1; i++)
                                                {
                                                    if (row3[i] != 0 && row3[i] == row3[i + 1])
                                                    {
                                                        //eslesmüz.Play();
                                                        isThereAnyMatchedNumber = true;
                                                        score += 10;
                                                        Console.SetCursorPosition(50, 7);
                                                        Console.WriteLine("Score : " + score);
                                                        row3[i] = 0;
                                                        row3[i + 1] = 0;
                                                        int randomRowChooser = random.Next(1, 4);
                                                        switch (randomRowChooser)
                                                        {
                                                            case 1:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row1[randomPosChooser] == 0)
                                                                    {
                                                                        row1[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 2:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row2[randomPosChooser] == 0)
                                                                    {
                                                                        row2[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            case 3:
                                                                for (int j = 0; j < 2; j++)
                                                                {
                                                                    int randomPosChooser = random.Next(1, 31);
                                                                    if (row3[randomPosChooser] == 0)
                                                                    {
                                                                        row3[randomPosChooser] = random.Next(1, 4);
                                                                    }
                                                                    else j--;
                                                                }
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                    }
                                                }
                                            } while (isThereAnyMatchedNumber);
                                            Console.SetCursorPosition(17, 6);
                                            for (int i = 1; i < row1.Length - 1; i++)
                                            {
                                                if (row1[i] != 0)
                                                {
                                                    Console.Write(row1[i]);
                                                }
                                                else
                                                {
                                                    System.Console.Write(" ");
                                                }
                                            }
                                            Console.SetCursorPosition(17, 7);
                                            for (int i = 1; i < row2.Length - 1; i++)
                                            {
                                                if (row2[i] != 0)
                                                {
                                                    Console.Write(row2[i]);
                                                }
                                                else
                                                {
                                                    System.Console.Write(" ");
                                                }
                                            }
                                            Console.SetCursorPosition(17, 8);
                                            for (int i = 1; i < row3.Length - 1; i++)
                                            {
                                                if (row3[i] != 0)
                                                {
                                                    Console.Write(row3[i]);
                                                }
                                                else
                                                {
                                                    System.Console.Write(" ");
                                                }
                                            }
                                            break;
                                        case ConsoleKey.Escape:
                                            Console.Clear();
                                            return;
                                        default:
                                            break;
                                    }
                                    if (command.Key == ConsoleKey.M)
                                    {
                                        Console.Clear();
                                        menu = true;
                                        break;
                                    }
                                    //else if (command.Key == ConsoleKey.P)
                                    //{
                                    //    menu_music.Stop();
                                    //    isMusicPlaying = false;
                                    //}
                                    //else if (command.Key == ConsoleKey.O && !isMusicPlaying)
                                    //{
                                    //    menu_music.Play();
                                    //    isMusicPlaying = true;
                                    //}

                                }//switch case consolekey
                                Console.SetCursorPosition(cursorX, cursorY);
                            }
                        }
                        break;//case 1 break

                    case ConsoleKey.D2:
                        {
                            Console.Clear();

                            Console.WriteLine("  The aim of the game is to develop a one-player number matching game.");
                            Console.WriteLine("  The player will try to get the highest score by matching the same numbers side by side.");
                            Console.WriteLine("  When two identical numbers come together, they disappear and the player gets 10 points.  ");
                            Console.WriteLine("");

                            Console.WriteLine("Game Playing Rules");
                            Console.WriteLine("");
                            Console.WriteLine("1. The game is played on a 3x30 board.");
                            Console.WriteLine("");
                            Console.WriteLine("2. In the beginning, the board is randomly filled with 30 random numbers which are 1, 2 and 3.");
                            Console.WriteLine("");
                            Console.WriteLine("3. The arrow keys on the keyboard are used to move the cursor and \n   WASD keys are used to move the number under the cursor.");
                            Console.WriteLine("");
                            Console.WriteLine("4. WASD keys can move only the single numbers (the left and right side of the number should be empty).");
                            Console.WriteLine("");
                            Console.WriteLine("W : Moves the number one square up.");
                            Console.WriteLine("S : Moves the number one square down. ");
                            Console.WriteLine("A : Moves the number to the left as much as it can go.");
                            Console.WriteLine("D : Moves the number to the right as much as it can go.");
                            Console.WriteLine("");
                            Console.WriteLine("");
                            Console.WriteLine("5. If two identical numbers come together on the same line (by player moves or randomly), ");
                            Console.WriteLine("  • Matching numbers are deleted from the board ");
                            Console.WriteLine("  • The player's score increases by 10 points. ");
                            Console.WriteLine("  • New two random numbers are generated and randomly placed on the board ");
                            Console.WriteLine("");


                            Console.WriteLine("PRESS ANY KEY TO RETURN TO THE MAIN MENU");
                            Console.ReadKey(true);
                            menu = true;
                        }
                        break;//case 2 break

                    case ConsoleKey.D3:
                        {
                            Console.Clear();
                            Console.SetCursorPosition(52, 1);
                            System.Console.WriteLine("*** SETTINGS ***");
                            Console.SetCursorPosition(50, 3);
                            System.Console.WriteLine("------------------");
                            Console.SetCursorPosition(50, 4);
                            System.Console.WriteLine("1 Colours Settings");
                            Console.SetCursorPosition(50, 5);
                            System.Console.WriteLine("------------------");

                            Console.SetCursorPosition(50, 7);
                            System.Console.WriteLine("-----------------");
                            Console.SetCursorPosition(50, 8);
                            System.Console.WriteLine("2 Sounds Settings");
                            Console.SetCursorPosition(50, 9);
                            System.Console.WriteLine("-----------------");

                            choice = Console.ReadKey(true);

                            switch (choice.Key)
                            {
                                case ConsoleKey.D1:
                                    {
                                        Console.Clear();
                                        Console.SetCursorPosition(52, 1);
                                        System.Console.WriteLine("*** COLOUR SETTINGS ***");
                                        Console.WriteLine("");
                                        Console.WriteLine("");
                                        Console.WriteLine("");
                                        Console.WriteLine("");
                                        Console.WriteLine("You can change the color of Foreground and Background color");
                                        Console.WriteLine("Press 1 to Foreground Cyan, Background = DarkGray ");
                                        Console.WriteLine("Press 2 to Foreground Yellow, Background = DarkGray ");
                                        Console.WriteLine("Press 3 to Foreground Blue, Background = Black ");
                                        Console.WriteLine("Press 4 to Foreground White, Background = Red ");
                                        Console.WriteLine("Press 5 to default mode ");
                                        Console.WriteLine("Press M to return to the menu ");



                                        do
                                        {
                                            choice = Console.ReadKey(true);
                                            switch (choice.Key)
                                            {
                                                case ConsoleKey.D1:
                                                    {
                                                        Console.ForegroundColor = ConsoleColor.Cyan;
                                                        Console.BackgroundColor = ConsoleColor.DarkGray;
                                                        Console.Clear();
                                                    }
                                                    break;

                                                case ConsoleKey.D2:
                                                    {
                                                        Console.ForegroundColor = ConsoleColor.Yellow;
                                                        Console.BackgroundColor = ConsoleColor.DarkGray;
                                                        Console.Clear();
                                                    }
                                                    break;
                                                case ConsoleKey.D3:
                                                    {
                                                        Console.ForegroundColor = ConsoleColor.Blue;
                                                        Console.BackgroundColor = ConsoleColor.Black;
                                                        Console.Clear();
                                                    }
                                                    break;
                                                case ConsoleKey.D4:
                                                    {
                                                        Console.ForegroundColor = ConsoleColor.White;
                                                        Console.BackgroundColor = ConsoleColor.Red;
                                                        Console.Clear();
                                                    }
                                                    break;
                                                case ConsoleKey.D5:
                                                    {
                                                        Console.ForegroundColor = ConsoleColor.White;
                                                        Console.BackgroundColor = ConsoleColor.Black;
                                                        Console.Clear();
                                                    }
                                                    break;
                                                default:
                                                    break;

                                            }//Choosing Color swtich
                                        } while (choice.Key != ConsoleKey.D1 && choice.Key != ConsoleKey.D2 && choice.Key != ConsoleKey.D3 && choice.Key != ConsoleKey.D4 && choice.Key != ConsoleKey.D5 && choice.Key != ConsoleKey.M);

                                    }
                                    break;//Colours settings

                                case ConsoleKey.D2://sounds case 
                                    {
                                        Console.Clear();

                                        Console.SetCursorPosition(52, 1);
                                        System.Console.WriteLine("*** SOUNDS SETTINGS ***");
                                        Console.SetCursorPosition(52, 4);
                                        System.Console.WriteLine("----------------");
                                        Console.SetCursorPosition(52, 5);
                                        System.Console.WriteLine(" Music Mod : " + Music_Mod);
                                        Console.SetCursorPosition(52, 6);
                                        System.Console.WriteLine("----------------");
                                        Console.SetCursorPosition(48, 10);
                                        Console.WriteLine("Press 6 to turn on the music");
                                        Console.SetCursorPosition(48, 12);
                                        Console.WriteLine("Press 7 to turn off the music");
                                        Console.SetCursorPosition(48, 14);
                                        Console.WriteLine("Press M to return to the menu ");

                                        do
                                        {
                                            choice = Console.ReadKey(true);


                                            switch (choice.Key)
                                            {
                                                case ConsoleKey.D6:
                                                    if (Music_Mod == "OFF")
                                                    {
                                                        //menu_music.Play();
                                                        Music_Mod = "ON ";
                                                        Console.SetCursorPosition(52, 5);
                                                        System.Console.WriteLine(" Music Mod : " + Music_Mod);
                                                    }
                                                    break;
                                                case ConsoleKey.D7:
                                                    //menu_music.Stop();
                                                    Music_Mod = "OFF";
                                                    Console.SetCursorPosition(52, 5);
                                                    System.Console.WriteLine(" Music Mod : " + Music_Mod);
                                                    break;
                                                default:
                                                    break;
                                            }
                                        } while (choice.Key != ConsoleKey.M);



                                    }
                                    break;//sounds settings

                            }//settings switch

                        }
                        break;//menu case 3 break

                    case ConsoleKey.D4:
                        {
                            Console.Clear();
                            Console.WriteLine("\n\n\n");
                            Console.WriteLine("                                                 .-\"\"\" -.");
                            Console.WriteLine("                                                / .===. \\");
                            Console.WriteLine("                                                \\/ 6 6 \\/");
                            Console.WriteLine("                                                ( \\___/ )");
                            Console.WriteLine("                           _________________ooo__\\_____/_____________________");
                            Console.WriteLine("                          /                                                  \\");
                            Console.WriteLine("                          |                                                  |");
                            Console.WriteLine("                          \\______________________________ooo_________________/");
                            Console.WriteLine("                                                 |  |  |");
                            Console.WriteLine("                                                 |_ | _|");
                            Console.WriteLine("                                                 |  |  |");
                            Console.WriteLine("                                                 |__|__|");
                            Console.WriteLine("                                                 /-'Y'-\\");
                            Console.WriteLine("                                                (__/ \\__)");
                            Console.SetCursorPosition(30, 10);
                            System.Console.WriteLine("Arda Aydın\tEmre Özkaya\tBurak Çolak");
                            Console.SetCursorPosition(15, 11);
                            Console.SetCursorPosition(0, 20);

                            Console.WriteLine("PRESS ANY KEY TO RETURN TO THE MAIN MENU");
                            Console.ReadKey(true);
                            Console.Clear();
                            menu = true;
                        }
                        break;//menu case 4 break

                    case ConsoleKey.D5:
                        {
                            Console.Clear();
                            Console.SetCursorPosition(50, 15);
                            Console.WriteLine("Bye!");
                            System.Threading.Thread.Sleep(1000);
                            Environment.Exit(0);
                        }
                        break;//menu case 5 break

                }//menu switch
            }//menu loop




        }
    }
}
