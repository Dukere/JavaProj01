package ver05;

public class PhoneCompanyInfo extends PhoneInfo {
	String company;

	public PhoneCompanyInfo(String name, String phoneNumber, String company) {
		super(name, phoneNumber);
		this.company = company;
	}
	
	@Override
	public void showPhoneInfo() {
		System.out.println("-------------------------");
		super.showPhoneInfo();
		System.out.println("회사 : " + company);
		System.out.println("-------------------------");
		
	}
	

}
