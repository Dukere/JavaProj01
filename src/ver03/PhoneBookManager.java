package ver03;

import java.util.Scanner;
import ver03.PhoneInfo;

public class PhoneBookManager {

	Scanner sc = new Scanner(System.in);
	PhoneInfo[] pi = new PhoneInfo[100];
	int stack = 0;

	public void printMenu() {
		while(true) {
			System.out.println("선택하세요...");
			System.out.println("1.데이터 입력");
			System.out.println("2.데이터 검색");
			System.out.println("3.데이터 삭제");
			System.out.println("4.주소록 출력");
			System.out.println("5.프로그램 종료");
			System.out.print("선택:");
			String check = sc.nextLine();
			System.out.println();
			switch(check) {
			case "1":
				dataInput();
				break;
			case "2":
				dataSearch();
				break;
			case "3":
				dataDelete();
				break;
			case "4":
				dataAllShow();
				break;
			case "5":
				System.out.println("프로그램 종료 . . ");
				System.exit(0);
			default:
				break;
			
			}
		}
	}

	public void dataInput() {
		System.out.println("데이터 입력을 시작합니다..");
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("전화번호 : ");
		String number = sc.nextLine();
		System.out.print("생년월일 : ");
		String birth = sc.nextLine();
		if (birth.isEmpty()) {
			pi[stack] = new PhoneInfo(name, number);
		} else {
			pi[stack] = new PhoneInfo(name, number, birth);
		}
		System.out.println("데이터 입력이 완료되었습니다.");
		stack++;
	}

	public void dataSearch() {

		int check = 0;
		System.out.println("데이터 검색을 시작합니다.");
		System.out.print("이름 : ");
		String name = sc.nextLine();
		if (stack == 0) {
			System.out.println("데이터가 없습니다.");
		} else {
			for (int i = 0; i < stack; i++) {
				if (name.equals(pi[i].name)) {
					pi[i].showPhoneInfo();
					check = 1;
					System.out.println("데이터 검색이 완료되었습니다.");
					break;
				}
			}
			if (check == 0) {
				System.out.println("데이터가 없습니다.");
			}
		}
	}

	public void dataDelete() {
		System.out.println("데이터 삭제를 시작합니다.");
		System.out.print("이름 : ");
		String name = sc.nextLine();
		int check = 0;
		if(stack ==0) {
			System.out.println("데이터가 없습니다.");
		}
		else {
		for (int i = 0; i < stack; i++) {
			if (name.equals(pi[i].name)) {
				for (int j = i; j <= stack-1; j++) {
					pi[j] = pi[j+1];
				}
				stack--;
				check = 1;
			}
		}
		if (check == 0) {
			System.out.println("데이터를 찾을 수 없습니다.");
		} else {
			System.out.println("데이터 삭제가 완료되었습니다.");
		}
		}

	}

	public void dataAllShow() {
		System.out.println("데이터 출력을 시작하겠습니다.");
		if(stack==0) {
			System.out.println("데이터가 없습니다");
		}
		else {
		for (int i = 0; i < stack; i++) {
			pi[i].showPhoneInfo();
		}
		System.out.println("데이터 출력이 완료되었습니다.");
		}
	}
}
