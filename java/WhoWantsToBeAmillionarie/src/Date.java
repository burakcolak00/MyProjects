
public class Date {
	int day;
	int month;
	int year;
	
	public Date(String _Date) {
		setDate(_Date);
	}
	
	public void setDate(String _Date) {
		String[] temp = _Date.split("\\.");
		
		if (temp.length == 3) {
			for (String word : temp) {
				for (char _char : word.toCharArray()) {
					if (!Character.isDigit(_char)) {
						Error();
						return;
					}
				}
			}
		}
		else {
			Error();
			return;
		}
		
		int _day	= Integer.parseInt(temp[0]);
		
		int _month	= Integer.parseInt(temp[1]) -1;
		
		int _year	= Integer.parseInt(temp[2]);
		
		if (_day < 0 || _day > 31 || (_month == 1 && _day > 28 + ((4 - (_year % 4)) / 4 - ((100 - (_year % 100)) / 100) + ((400 - (_year % 400)) / 400) - ((4000 - (_year % 4000)) / 4000))) || (_month % 2 - (_month / 7) == 0 && _day > 31) || ((_month == 3 || _month == 5 || _month == 8 || _month == 10) && _day > 30)) {
			Error();
			return;
		}
		
		day = _day;
		month = _month + 1;
		year = _year;
	}
	
	public String getDate() {
		return day + "." + month + "." + year;
	}
	
	void Error() {
		System.out.println(">Error: Wrong Birth Date!");
	}
}
