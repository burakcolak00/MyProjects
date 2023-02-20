using System;
// MADE BY BURAK ÇOLAK
// STUDENT NO: 2020510079
namespace Calendar
{
    class Program
    {
        static void Main(string[] args)
        {
            // Defining variables
            int n = 0;
            int day1 = 0, year1 = 0, day2 = 0, year2 = 0;
            string month1 = "", month2 = "";
            bool isSeasonChanged = false, isInputsHaveException;

            // Getting the first date from the user
            do
            {
                isInputsHaveException = false;
                try
                {
                    Console.Write("day1: ");
                    day1 = Convert.ToInt32(Console.ReadLine());
                    Console.Write("month1: ");
                    month1 = Console.ReadLine();
                    Console.Write("year1: ");
                    year1 = Convert.ToInt32(Console.ReadLine());
                }
                catch
                {
                    Console.WriteLine("Please enter a valid date values");
                    isInputsHaveException = true;
                }
                month1 = month1.ToLower(new System.Globalization.CultureInfo("en-US", false));
            } while (isInputsHaveException || !DateExistenceControl(day1, month1, year1));

            // Getting the second date from the user
            do
            {
                isInputsHaveException = false;
                try
                {
                    Console.Write("day2: ");
                    day2 = Convert.ToInt32(Console.ReadLine());
                    Console.Write("month2: ");
                    month2 = Console.ReadLine();
                    Console.Write("year2: ");
                    year2 = Convert.ToInt32(Console.ReadLine());
                }
                catch
                {
                    Console.WriteLine("Please enter a valid date values");
                    isInputsHaveException = true;
                }

                month2 = month2.ToLower(new System.Globalization.CultureInfo("en-US", false));
            } while (isInputsHaveException || !DateExistenceControl(day2, month2, year2)); // Date existence function controls whether the given date is valid

            // Switching the dates if it is necessary
            if (year2 < year1)
            {
                int tempYear = year2;
                year2 = year1;
                year1 = tempYear;

                string tempMonth = month2;
                month2 = month1;
                month1 = tempMonth;

                int tempDay = day2;
                day2 = day1;
                day1 = tempDay;
            }
            else if (year1 == year2 && ((ConvertingMonthToNumber(month2) < ConvertingMonthToNumber(month1)) || (ConvertingMonthToNumber(month2) == ConvertingMonthToNumber(month1) && day2 < day1)))
            {
                string tempMonth = month2;
                month2 = month1;
                month1 = tempMonth;

                int tempDay = day2;
                day2 = day1;
                day1 = tempDay;
            }

            // Getting n from the user
            do
            {
                try
                {
                    Console.Write("n: ");
                    n = Convert.ToInt32(Console.ReadLine());
                }
                catch
                {

                }
                if (n <= 0)
                {
                    Console.WriteLine("Please enter an integer greater than zero");
                }

            } while (n <= 0);

            // Finding and printing the first season 
            if (month1 == "march" || month1 == "april" || month1 == "may")
            {
                Console.WriteLine("\nSPRING");
            }
            else if (month1 == "june" || month1 == "july" || month1 == "august")
            {
                Console.WriteLine("\nSUMMER");
            }
            else if (month1 == "september" || month1 == "october" || month1 == "november")
            {
                Console.WriteLine("\nAUTUMN");
            }
            else
            {
                Console.WriteLine("\nWINTER");
            }

            // Increasing the date
            while (year1 != year2 || (year1 == year2 && month1 != month2) || (year1 == year2 && month1 == month2 && day1 <= day2))
            {

                while ((day1 > 30 && (month1 == "april" || month1 == "june" || month1 == "september" || month1 == "november")) || (day1 > 31 && (month1 == "january" || month1 == "march" || month1 == "may" || month1 == "july" || month1 == "august" || month1 == "october" || month1 == "december")) || (((day1 > 28 && (year1 % 4 != 0 || (year1 % 4 == 0 && year1 % 100 == 0))) || (day1 > 29 && (year1 % 4 == 0 && year1 % 100 != 0) || (year1 % 100 == 0 && year1 % 400 == 0))) && month1 == "february"))
                {
                    if (year1 != year2 || (year1 == year2 && month1 != month2) || (year1 == year2 && month1 == month2 && day1 <= day2))
                    {
                        if (day1 > 30 && month1 != "february")
                        {
                            switch (month1)
                            {
                                case "april":
                                    month1 = "may";
                                    day1 -= 30;
                                    break;
                                case "june":
                                    month1 = "july";
                                    day1 -= 30;
                                    break;
                                case "september":
                                    month1 = "october";
                                    day1 -= 30;
                                    break;
                                case "november":
                                    isSeasonChanged = true;
                                    month1 = "december";
                                    day1 -= 30;
                                    break;
                                default:
                                    break;
                            }
                        }

                        if (day1 > 31 && month1 != "february")
                        {
                            switch (month1)
                            {
                                case "january":
                                    month1 = "february";
                                    day1 -= 31;
                                    break;
                                case "march":
                                    month1 = "april";
                                    day1 -= 31;
                                    break;
                                case "may":
                                    isSeasonChanged = true;
                                    month1 = "june";
                                    day1 -= 31;
                                    break;
                                case "july":
                                    month1 = "august";
                                    day1 -= 31;
                                    break;
                                case "august":
                                    isSeasonChanged = true;
                                    month1 = "september";
                                    day1 -= 31;
                                    break;
                                case "october":
                                    month1 = "november";
                                    day1 -= 31;
                                    break;
                                case "december":
                                    month1 = "january";
                                    day1 -= 31;
                                    year1++;
                                    break;
                                default:
                                    break;
                            }
                        }

                        if (month1 == "february" && ((((year1 % 4 == 0 && year1 % 100 != 0) || (year1 % 100 == 0 && year1 % 400 == 0)) && day1 > 29) || ((year1 % 4 != 0 || (year1 % 4 == 0 && year1 % 100 == 0)) && day1 > 28)))
                        {
                            isSeasonChanged = true;
                            month1 = "march";
                            if ((year1 % 4 == 0 && year1 % 100 != 0) || (year1 % 100 == 0 && year1 % 400 == 0))
                            {
                                day1 -= 29;
                            }
                            else
                            {
                                day1 -= 28;
                            }
                        }
                    }
                    else
                    {
                        Console.Write("\nOperation has done! Please enter any key to exit...");
                        Console.ReadKey();
                        Environment.Exit(1);
                    }

                    if (year1 > year2)
                    {
                        Console.Write("\nOperation has done! Please enter any key to exit...");
                        Console.ReadKey();
                        Environment.Exit(1);
                    }

                }

                // Checking and printing the season if it is necessary
                if (isSeasonChanged)
                {
                    Console.WriteLine(ChangingSeason(month1));
                    isSeasonChanged = false;
                }

                Console.WriteLine(day1 + " " + month1 + " " + year1 + " " + FindingDayOfWeek(day1, month1, year1));
                day1 += n;
            }
            Console.Write("\nOperation has done! Please enter any key to exit...");
            Console.ReadKey();

        }
        // Functions. I created 4 functions named: "DateExistenceControl", "ConvertingMonthToNumber", "FindingDayOfWeek", "ChangingSeason".
        public static bool DateExistenceControl(int day, string month, int year)
        {
            if (day <= 0 || day > 31)
            {
                Console.WriteLine("day is wrong");
                Console.WriteLine("Please enter a valid date");
                return false;
            }
            else if (month != "january" && month != "february" && month != "march" && month != "april" && month != "may" && month != "june" && month != "july" && month != "august" && month != "september" && month != "october" && month != "november" && month != "december")
            {
                Console.WriteLine("month is wrong");
                Console.WriteLine("Please enter a valid date");
                return false;
            }
            else if (year < 2015)
            {
                Console.WriteLine("Please enter a date later than 01.01.2015");
                Console.WriteLine("Please enter a valid date");
                return false;
            }
            else if (month == "february" && (((year % 4 != 0 || (year % 4 == 0 && year % 100 == 0)) && day == 29) || day >= 30))
            {
                Console.WriteLine("day is wrong");
                Console.WriteLine("Please enter a valid date");
                return false;
            }
            else if ((month == "april" || month == "june" || month == "september" || month == "november") && day == 31)
            {
                Console.WriteLine("day is wrong");
                Console.WriteLine("Please enter a valid date");
                return false;
            }
            else return true;
        }
        public static int ConvertingMonthToNumber(string month)
        {
            switch (month)
            {
                case "january":
                    return 1;
                case "february":
                    return 2;
                case "march":
                    return 3;
                case "april":
                    return 4;
                case "may":
                    return 5;
                case "june":
                    return 6;
                case "july":
                    return 7;
                case "august":
                    return 8;
                case "september":
                    return 9;
                case "october":
                    return 10;
                case "november":
                    return 11;
                case "december":
                    return 12;
                default:
                    return 0;
            }
        }
        public static string FindingDayOfWeek(int day, string month, int year)
        {
            int m = 0;
            switch (month)
            {
                case "march":
                    m = 3;
                    break;
                case "april":
                    m = 4;
                    break;
                case "may":
                    m = 5;
                    break;
                case "june":
                    m = 6;
                    break;
                case "july":
                    m = 7;
                    break;
                case "august":
                    m = 8;
                    break;
                case "september":
                    m = 9;
                    break;
                case "october":
                    m = 10;
                    break;
                case "november":
                    m = 11;
                    break;
                case "december":
                    m = 12;
                    break;
                case "january":
                    m = 13;
                    break;
                case "february":
                    m = 14;
                    break;
                default:
                    break;
            }
            int h;
            if (month == "january" || month == "february")
            {
                h = (day + (13 * (m + 1) / 5) + (year - 1) + ((year - 1) / 4) - ((year - 1) / 100) + ((year - 1) / 400)) % 7;
            }
            else
            {
                h = (day + (13 * (m + 1) / 5) + year + (year / 4) - (year / 100) + (year / 400)) % 7;
            }
            if (h < 0)
            {
                h += 7;
            }
            switch (h)
            {
                case 0:
                    return "Saturday";
                case 1:
                    return "Sunday";
                case 2:
                    return "Monday";
                case 3:
                    return "Tuesday";
                case 4:
                    return "Wednesday";
                case 5:
                    return "Thursday";
                case 6:
                    return "Friday";
                default:
                    return "Error";
            }
        }
        public static string ChangingSeason(string month)
        {
            switch (month)
            {
                case "march":
                    return "\nSPRING";
                case "april":
                    return "\nSPRING";
                case "may":
                    return "\nSPRING";
                case "june":
                    return "\nSUMMER";
                case "july":
                    return "\nSUMMER";
                case "august":
                    return "\nSUMMER";
                case "september":
                    return "\nAUTUMN";
                case "october":
                    return "\nAUTUMN";
                case "november":
                    return "\nAUTUMN";
                case "december":
                    return "\nWINTER";
                case "january":
                    return "\nWINTER";
                case "february":
                    return "\nWINTER";
                default:
                    return "Error";
            }
        }
    }
}
