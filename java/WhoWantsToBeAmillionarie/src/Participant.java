
public class Participant {

	private int ID;
	private String Name;
	private Date BirthDate;
	private PhoneNumber PhoneNumber;
	private Address Address;
	
	private int Age;
	
	private int Success;
	
	private String City;

	public Participant(int ID, String _Name, String _BirthDate, String _PhoneNumber, String _Adress) {
		
		this.ID = ID;
		this.Name = _Name;
		this.BirthDate = new Date(_BirthDate);
		this.PhoneNumber = new PhoneNumber(_PhoneNumber);
		this.Address = new Address(_Adress);
		Success = 0;
		setAge(2022-BirthDate.year);
		setCity(Address.getCity());
	}
	
	public int getSuccess() {
		return Success;
	}
	
	public void increaseSuccess() {
		Success++;
	}
	
	public int getID() {
		return ID;
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String _Name) {
		Name = _Name;
	}

	public String getBirthDate() {
		return BirthDate.getDate();
	}

	public void setBirthDate(String _BirthDate) {
		BirthDate.setDate(_BirthDate);
	}

	public String getPhoneNumber() {
		return PhoneNumber.getNumber();
	}

	public void setPhoneNumber(String _PhoneNumber) {
		PhoneNumber.setNumber(_PhoneNumber);
	}

	public Address getAdress() {
		return Address;
	}

	public void setAdress(Address _Address) {
		Address = _Address;
	}

	public void setAdress(String _Address) {
		Address.setAddress(_Address);
	}
	public void printParticipantInfo() {
		System.out.println(getName() +"	--	" +getBirthDate()+"	--	"+ getPhoneNumber() + "	--	"+ getAdress());
	}
	
	public static int idFinder(Participant p,Participant[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == p) {
				return i;
			}
		}
		return -1;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}
}
