public class Address {
	String street;
	String no;
	String district;
	String city;
	String country;
	String Adress;

	public Address(String _Address) {
		setAddress(_Address);
	}

	public void setAddress(String _Address) {
		String[] temp = _Address.split(";");
		/*
		 * for(String qwe : temp) { System.out.println(qwe); }
		 */
		if (temp.length == 5) {
			for (int i = 0; i < temp.length; i++)

				for (char _char : temp[i].toCharArray()) {
					if ((i == 2 || i == 3 || i == 4) && Character.isDigit(_char)) {
						Error();
						return;
					}

				}
		} else {
			Error();
			return;
		}
		street = temp[0];
		no = temp[1];
		district = temp[2];
		city = temp[3];
		country = temp[4];

		Adress = street + "-" + no + "-" + district + "-" + city + "-" + country;
	}

	public String getAddress() {
		return Adress;
	}
	
	public String getCity() {
		return city;
	}

	void Error() {
		System.out.println("Invalid values. Please check your address again!");
	}
}