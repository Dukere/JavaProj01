package ver08;

import java.io.Serializable;

public class PhoneInfo implements Serializable {
	//멤버변수
	String name;
	String phoneNumber;

	//생성자
	public PhoneInfo(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public void showPhoneInfo() {
		System.out.println("이름 : " + name);
		System.out.println("전화번호 : " + phoneNumber);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		PhoneInfo comparePhoneInfo = (PhoneInfo) obj;
		if (comparePhoneInfo.name.equals(this.name)) {
			return true;
		} else {
			return false;
		}
	}
}


