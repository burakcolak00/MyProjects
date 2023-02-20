

public class PhoneNumber {
	private String Text;
	
	public PhoneNumber(String _PhoneNumber) {
		setNumber(_PhoneNumber);
	}
	
	public void setNumber(String _PhoneNumber) {

		_PhoneNumber = _PhoneNumber.replaceAll("( )+", "");
		
		if (_PhoneNumber.length() == 13 && _PhoneNumber.charAt(0) == '+') {

			for (int i = 1; i < _PhoneNumber.length(); i++) {
				if (!Character.isDigit(_PhoneNumber.charAt(i))) {
					Error();
					return;
				}
			}
		}
		else {
			Error();
			return;
		}
		Text = _PhoneNumber;
	}
	
	
	public String getNumber() {
		return Text;
	}
	
	void Error() {
		System.out.println(">Error: Wrong Phone number!");
	}
}
