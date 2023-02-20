/*     
                                            MADE BY
                                            Burak Çolak 2020510079
                                            Halil İbrahimKaan Yıldız 2020510074
                                            Fevzi Berke Göktuna 2020510037
*/
using System;

namespace TriangleBattleship
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.ForegroundColor = ConsoleColor.White;
            bool isGameRunning = true;
            bool isTriangleExist = false;
            string userName = "";
            string menuSelection;
            string typeOfTriangle = "";
            double score = 0;
            int Ax = 0, Bx = 0, Cx = 0, Ay = 0, By = 0, Cy = 0;
            double areaOfTriangle = 0;
            double AB , BC , AC;
            double centroidX = 0, centroidY = 0;
            double angleA = 0, angleB = 0, angleC = 0;
            double perimeterOfTriangle = 0;
            double medianPointABx = 0,medianPointABy = 0,medianPointACx = 0,medianPointACy = 0,medianPointBCx = 0,medianPointBCy = 0;
            double lengthOfBisectorA = 0;
            double areaOfInscribedCircle = 0, areaOfCircumscribedCircle = 0;

            while (isGameRunning)
            {
                // reading corner points of the ship
                Console.Clear();
                Console.ForegroundColor = ConsoleColor.Blue;
                Console.WriteLine("\t-MENU-");
                Console.WriteLine("1-)Enter ship location.");
                Console.WriteLine("2-)Ship info.");
                Console.WriteLine("3-)Shoot at the ship.");
                Console.WriteLine("4-)Show high score table.");
                Console.WriteLine("5-)Exit.");
                Console.ForegroundColor = ConsoleColor.White;
                Console.WriteLine("Enter the place you want to go as a number.");
                menuSelection = Console.ReadLine();
                Console.Clear();

                switch (menuSelection)
                {
                    case "1":
                        do
                        {
                            try
                            {
                                Console.WriteLine("You have to define 3 point called A, B, C in the first quadrant(x > 0, y > 0) of the Cartesian coordinate plane.\nX values must be an integer and between 0 and 30(included),\nY values must be integer and between 0 and 12(included)\n");
                                Console.Write("Please enter the x point of the A corner of the ship: ");
                                Ax = Convert.ToInt32(Console.ReadLine());
                                Console.Write("Please enter the y point of the A corner of the ship: ");
                                Ay = Convert.ToInt32(Console.ReadLine());
                                Console.Write("Please enter the x point of the B corner of the ship: ");
                                Bx = Convert.ToInt32(Console.ReadLine());
                                Console.Write("Please enter the y point of the B corner of the ship: ");
                                By = Convert.ToInt32(Console.ReadLine());
                                Console.Write("Please enter the x point of the C corner of the ship: ");
                                Cx = Convert.ToInt32(Console.ReadLine());
                                Console.Write("Please enter the y point of the C corner of the ship: ");
                                Cy = Convert.ToInt32(Console.ReadLine());
                            }
                            catch 
                            {
                                Console.WriteLine("\nPlease enter an integer\n");
                            }

                            AB = Math.Sqrt(Math.Pow(Ay - By, 2) + Math.Pow((Ax - Bx), 2));
                            BC = Math.Sqrt(Math.Pow(By - Cy, 2) + Math.Pow((Bx - Cx), 2));
                            AC = Math.Sqrt(Math.Pow(Ay - Cy, 2) + Math.Pow((Ax - Cx), 2));

                            if (Ax <= 0 || Bx <= 0 || Cx <= 0 || Ax > 30 || Bx > 30 || Cx > 30 || Ay <= 0 || By <= 0 || Cy <= 0 || Ay > 12 || By > 12 || Cy > 12)
                            {
                                isTriangleExist = false;
                            }

                            // Validity control
                            else if (Math.Abs(AC - AB) < (BC) && (BC) < (AC + AB) && Math.Abs(BC - AB) < (AC) && (AC) < (BC + AB) && Math.Abs(BC - AC) < (AB) && (AB) < (BC + AC))
                            {
                                Console.WriteLine("\nCongratulations, you have drawn a triangle.");
                                isTriangleExist = true;
                            }
                            else
                            {
                                Console.WriteLine("\nPlease enter a valid triangle\n");
                            }
                        }
                        while (!isTriangleExist);

                        // calculating lenght of the edges
                        AB = Math.Sqrt(Math.Pow(Ay - By, 2) + Math.Pow((Ax - Bx), 2));
                        BC = Math.Sqrt(Math.Pow(By - Cy, 2) + Math.Pow((Bx - Cx), 2));
                        AC = Math.Sqrt(Math.Pow(Ay - Cy, 2) + Math.Pow((Ax - Cx), 2));

                        // calculating the centroid of the ship
                        centroidX = (Ax + Bx + Cx) / 3f;
                        centroidY = (Ay + By + Cy) / 3f;

                        //calculating the perimeter of the ship
                        perimeterOfTriangle = AB + BC + AC;

                        // calculating the area of the ship
                        areaOfTriangle = 0.5 * Math.Abs(((Ax * By + Bx * Cy + Cx * Ay) - (Bx * Ay + Cx * By + Ax * Cy)));

                        // calculating the angles
                        double cosA = (AC * AC + AB * AB - BC * BC) / (2 * AC * AB);
                        double cosB = (BC * BC + AB * AB - AC * AC) / (2 * BC * AB);
                        double cosC = (AC * AC + BC * BC - AB * AB) / (2 * AC * BC);
                        angleA = Math.Acos(cosA) * 180 / Math.PI;
                        angleB = Math.Acos(cosB) * 180 / Math.PI;
                        angleC = Math.Acos(cosC) * 180 / Math.PI;

                        // calculating median points of the ship
                        medianPointABx = (Ax + Bx) / 2f;
                        medianPointABy = (Ay + By) / 2f;
                        medianPointACx = (Ax + Cx) / 2f;
                        medianPointACy = (Ay + Cy) / 2f;
                        medianPointBCx = (Cx + Bx) / 2f;
                        medianPointBCy = (Cy + By) / 2f;

                        // calculating the length of bisector of the point A
                        lengthOfBisectorA = Math.Sqrt(AB * AC - (AB * (BC / (AB + AC)) * AC * (BC / (AB + AC))));

                        // calculating 	The area of the circumscribed circle
                        double radiusOfCircumscribedCircle = AB * AC * BC / (4 * areaOfTriangle);
                        areaOfCircumscribedCircle = Math.PI * radiusOfCircumscribedCircle * radiusOfCircumscribedCircle;

                        // calculating 	The area of the inscribed circle
                        double radiusOfInscribedCircle = areaOfTriangle * 2f / perimeterOfTriangle;
                        areaOfInscribedCircle = Math.PI * radiusOfInscribedCircle * radiusOfInscribedCircle;

                        //  calculating the type of the ship
                        
                        angleA = Math.Round(angleA, 2);
                        angleB = Math.Round(angleB, 2);
                        angleC = Math.Round(angleC, 2);

                        if (AB == BC && BC == AC)
                        {
                            typeOfTriangle = "Equilateral";
                        }
                        else if (AB == BC || BC == AC || AB == AC)
                        {
                            typeOfTriangle = "Isosceles";
                        }
                        else typeOfTriangle = "Scalene";

                        if (angleA == 90 || angleB == 90 || angleC == 90)
                        {
                            typeOfTriangle += ", Right-Angled";
                        }
                        else if (angleA > 90 || angleB > 90 || angleC > 90)
                        {
                            typeOfTriangle += ", Obtuse-Angled";
                        }
                        else typeOfTriangle += ", Acute-Angled";


                        Console.Write("\nPlease press any key to return to the menu ");
                        Console.ReadKey();
                        break;
                    case "2":
                        if (!isTriangleExist)
                        {
                            Console.WriteLine("Please enter the coordinates of the ship first");
                            Console.Write("\nPlease press any key to return to the menu ");
                            Console.ReadKey();
                            break;
                        }
                        else
                        {
                            // printing the coordinate plane
                            Console.Clear();
                            Console.Write("12|\n" +
                                          "11|\n" +
                                          "10|\n" +
                                          "9 |\n" +
                                          "8 |\n" +
                                          "7 |\n" +
                                          "6 |\n" +
                                          "5 |\n" +
                                          "4 |\n" +
                                          "3 |\n" +
                                          "2 |\n" +
                                          "1 |\n" +
                                          "  +------------------------------\n" +
                                          "   123456789012345678901234567890\n");

                            Console.SetCursorPosition(Ax + 2, 12 - Ay);
                            Console.Write("A");
                            Console.SetCursorPosition(Bx + 2, 12 - By);
                            Console.Write("B");
                            Console.SetCursorPosition(Cx + 2, 12 - Cy);
                            Console.Write("C");
                            Console.SetCursorPosition(0, 14);

                            Console.WriteLine("\n");
                            // calculating lenght of the edges
                            AB = Math.Sqrt(Math.Pow(Ay - By, 2) + Math.Pow((Ax - Bx), 2));
                            BC = Math.Sqrt(Math.Pow(By - Cy, 2) + Math.Pow((Bx - Cx), 2));
                            AC = Math.Sqrt(Math.Pow(Ay - Cy, 2) + Math.Pow((Ax - Cx), 2));

                            // Printing the ship info
                            Console.ForegroundColor = ConsoleColor.Blue;
                            Console.WriteLine("SHIP INFO");
                            Console.ForegroundColor = ConsoleColor.White;
                            Console.WriteLine("The location of the ship is: A" + "(" + Ax + "," + Ay + ")" + " B" + "(" + Bx + "," + By + ")" + " C" + "(" + Cx + "," + Cy + ")");
                            centroidX = (Math.Round(centroidX, 2));
                            centroidY = (Math.Round(centroidY, 2));
                            Console.WriteLine("The centroid of the ship: (" + centroidX + " , " + centroidY + ")");

                            AB = Math.Round(AB, 2);
                            AC = Math.Round(AC, 2);
                            BC = Math.Round(BC, 2);
                            Console.WriteLine("Length of |AB|: " + AB);
                            Console.WriteLine("Length of |BC|: " + BC);
                            Console.WriteLine("Length of |AC|: " + AC);

                            perimeterOfTriangle = Math.Round(perimeterOfTriangle, 2);
                            Console.WriteLine("The perimeter of the ship: " + perimeterOfTriangle);

                            areaOfTriangle = Math.Round(areaOfTriangle, 2);
                            Console.WriteLine("The area of the ship: " + areaOfTriangle);

                            Console.WriteLine("Angle A: " + angleA);
                            Console.WriteLine("Angle B: " + angleB);
                            Console.WriteLine("Angle C: " + angleC);

                            Console.WriteLine("Median points of the ship: D(" + medianPointABx + " , " + medianPointABy + ") F(" + medianPointACx + " , " + medianPointACy + ") G(" + medianPointBCx + " , " + medianPointBCy + ")");

                            lengthOfBisectorA = Math.Round(lengthOfBisectorA, 2);
                            Console.WriteLine("The length of bisector of the point A: " + lengthOfBisectorA);

                            areaOfInscribedCircle = Math.Round(areaOfInscribedCircle, 2);
                            Console.WriteLine("The area of inscribed circle: " + areaOfInscribedCircle);

                            areaOfCircumscribedCircle = Math.Round(areaOfCircumscribedCircle, 2);
                            Console.WriteLine("The area of circumscribed circle: " + areaOfCircumscribedCircle);

                            Console.WriteLine("Type of the ship: " + typeOfTriangle);
                            Console.Write("\nPlease press any key to return to the menu ");
                            Console.ReadKey();
                            break;
                        }
                        
                    case "3":
                        if (!isTriangleExist)
                        {
                            Console.WriteLine("Please enter the coordinates of the ship first");
                            Console.Write("\nPlease press any key to return to the menu ");
                            Console.ReadKey();
                            break;
                        }
                        else
                        {
                            // Shooting system
                            Random rnd = new Random();
                            int xPointOfMissile = rnd.Next(1, 31);
                            int yPointOfMissile = rnd.Next(1, 13);

                            double areaOfControlTriangle1 = Math.Abs((Ax * By + Bx * yPointOfMissile + xPointOfMissile * Ay) - (Ay * Bx + By * xPointOfMissile + yPointOfMissile * Ax)) / 2f;
                            double areaOfControlTriangle2 = Math.Abs((Bx * yPointOfMissile + xPointOfMissile * Cy + Cx * By) - (By * xPointOfMissile + yPointOfMissile * Cx + Cy * Bx)) / 2f;
                            double areaOfControlTriangle3 = Math.Abs((Ax * yPointOfMissile + xPointOfMissile * Cy + Cx * Ay) - (Ay * xPointOfMissile + yPointOfMissile * Cx + Cy * Ax)) / 2f;

                            Console.Clear();
                            Console.ForegroundColor = ConsoleColor.Blue;
                            Console.WriteLine("Shoot: (" + xPointOfMissile + "," + yPointOfMissile + ")");
                            Console.ForegroundColor = ConsoleColor.White;
                            Console.Write("12|\n" +
                                          "11|\n" +
                                          "10|\n" +
                                          "9 |\n" +
                                          "8 |\n" +
                                          "7 |\n" +
                                          "6 |\n" +
                                          "5 |\n" +
                                          "4 |\n" +
                                          "3 |\n" +
                                          "2 |\n" +
                                          "1 |\n" +
                                          "  +------------------------------\n" +
                                          "   123456789012345678901234567890\n");

                            Console.SetCursorPosition(Ax + 2, 13 - Ay);
                            Console.Write("A");
                            Console.SetCursorPosition(Bx + 2, 13 - By);
                            Console.Write("B");
                            Console.SetCursorPosition(Cx + 2, 13 - Cy);
                            Console.Write("C");
                            Console.SetCursorPosition(xPointOfMissile + 2, 13 - yPointOfMissile);
                            Console.ForegroundColor = ConsoleColor.Red;
                            Console.Write("X");
                            Console.ForegroundColor = ConsoleColor.White;
                            Console.SetCursorPosition(0, 14);

                            Console.WriteLine("\n");

                            if (areaOfTriangle == (areaOfControlTriangle2 + areaOfControlTriangle3 + areaOfControlTriangle1))
                            {
                                Console.WriteLine("Your ship sank! Total score is 0");
                                Console.ForegroundColor = ConsoleColor.Blue;
                                Console.WriteLine("\nHIGH SCORE TABLE");
                                Console.WriteLine("Name\t\t\tScore");
                                Console.ForegroundColor = ConsoleColor.White;
                                Console.WriteLine("Nazan Kaya\t\t60");
                                Console.WriteLine("Ali Kurt  \t\t30");
                                Console.WriteLine("Sibel Arslan\t\t10");
                            }
                            else if (areaOfTriangle < (areaOfControlTriangle2 + areaOfControlTriangle3 + areaOfControlTriangle1))
                            {
                                score = areaOfTriangle;
                                Console.WriteLine("Your ship survived! Total score is " + score);
                                Console.Write("Please enter your name: ");
                                userName = Console.ReadLine();
                                //printing high score table
                                Console.ForegroundColor = ConsoleColor.Blue;
                                Console.WriteLine("\nHIGH SCORE TABLE");
                                Console.WriteLine("Name\t\t\tScore");
                                Console.ForegroundColor = ConsoleColor.White;
                                if (score <= 10)
                                {
                                    Console.WriteLine("Nazan Kaya\t\t60");
                                    Console.WriteLine("Ali Kurt  \t\t30");
                                    Console.WriteLine("Sibel Arslan\t\t10");
                                }
                                else if (score > 60)
                                {
                                    Console.Write(userName);
                                    Console.SetCursorPosition(24, 21);
                                    Console.WriteLine(score);
                                    Console.WriteLine("Nazan Kaya\t\t60");
                                    Console.WriteLine("Ali Kurt  \t\t30");
                                }
                                else if (score > 30)
                                {
                                    Console.WriteLine("Nazan Kaya\t\t60");
                                    Console.Write(userName);
                                    Console.SetCursorPosition(24, 22);
                                    Console.WriteLine(score);
                                    Console.WriteLine("Ali Kurt  \t\t30");
                                }
                                else
                                {
                                    Console.WriteLine("Nazan Kaya\t\t60");
                                    Console.WriteLine("Ali Kurt  \t\t30");
                                    Console.Write(userName);
                                    Console.SetCursorPosition(24, 23);
                                    Console.WriteLine(score);
                                }
                            }
                            Console.Write("\nPlease press any key to return to the menu ");
                            Console.ReadKey();
                            break;
                        }
                        
                    case "4":
                        Console.ForegroundColor = ConsoleColor.Blue;
                        Console.WriteLine("HIGH SCORE TABLE");
                        Console.WriteLine("Name\t\t\tScore");
                        Console.ForegroundColor = ConsoleColor.White;
                        if (!isTriangleExist || score <= 10)
                        {
                            Console.WriteLine("Nazan Kaya\t\t60");
                            Console.WriteLine("Ali Kurt  \t\t30");
                            Console.WriteLine("Sibel Arslan\t\t10");
                        }
                        else if (isTriangleExist && score > 60)
                        {
                            Console.Write(userName);
                            Console.SetCursorPosition(24, 2);
                            Console.WriteLine(score);
                            Console.WriteLine("Nazan Kaya\t\t60");
                            Console.WriteLine("Ali Kurt  \t\t30");
                        }
                        else if (isTriangleExist && score > 30)
                        {
                            Console.WriteLine("Nazan Kaya\t\t60");
                            Console.Write(userName);
                            Console.SetCursorPosition(24, 3);
                            Console.WriteLine(score);
                            Console.WriteLine("Ali Kurt  \t\t30");
                        }
                        else
                        {
                            Console.WriteLine("Nazan Kaya\t\t60");
                            Console.WriteLine("Ali Kurt  \t\t30");
                            Console.Write(userName);
                            Console.SetCursorPosition(24, 4);
                            Console.WriteLine(score);
                        }
                        Console.Write("\nPlease press any key to return to the menu ");
                        Console.ReadKey();
                        break;
                    case "5":
                        isGameRunning = false;
                        break;
                    default:
                        isGameRunning = false;
                        break;
                }
            }
            
        }
    }
}
