import java.util.Scanner;

import ver02.PhoneInfo;

public class PhoneBookVer02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int check = 1;
		while (check == 1) {
			System.out.println("선택하세요...\n1.데이터 입력\n2.프로그램 종료");
			System.out.print("선택:");
			check = sc.nextInt();
			sc.nextLine();
			if (check == 2) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			else if (check == 1) {
				System.out.print("이름:");
				String name = sc.nextLine();
				System.out.print("전화번호:");
				String number = sc.nextLine();
				System.out.print("생년월일:");
				String birth = sc.nextLine();
				if(birth.isEmpty()) {
					PhoneInfo pi = new PhoneInfo(name,number);
					pi.showPhoneInfo();
				}
				else {
					PhoneInfo pi = new PhoneInfo(name,number,birth);
					pi.showPhoneInfo();
				}
				
			}
			else {
				System.out.println("다시 입력....");
			}
		}
	}

}
