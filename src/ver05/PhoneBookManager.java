package ver05;

import java.util.Scanner;
import ver05.PhoneInfo;



public class PhoneBookManager {

	Scanner sc = new Scanner(System.in);
	PhoneInfo[] pi = new PhoneInfo[100];
	int stack = 0;

	public void printMenu() {
		while (true) {
			System.out.println("선택하세요...");
			System.out.println("1.데이터 입력");
			System.out.println("2.데이터 검색");
			System.out.println("3.데이터 삭제");
			System.out.println("4.주소록 출력");
			System.out.println("5.프로그램 종료");
			System.out.print("선택:");
			String check = sc.nextLine();
			System.out.println();
			
			switch (check) {
			case MenuItem.INPUT:
				dataInput();
				break;
			case MenuItem.SEARCH:
				dataSearch();
				break;
			case MenuItem.DELETE:
				dataDelete();
				break;
			case MenuItem.SHOW:
				dataAllShow();
				break;
			case MenuItem.EXIT:
				System.out.println("프로그램 종료 . . ");
				System.exit(0);
			default:
				break;

			}
		}
	}

	public void dataInput() {
		System.out.println("데이터 입력을 시작합니다..");
		System.out.println("1.일반 2.동창 3.회사");
		int check = sc.nextInt();
		sc.nextLine();
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("전화번호 : ");
		String number = sc.nextLine();
		switch(check){
		case SubMenuItem.NORMAL:
			pi[stack] = new PhoneInfo(name, number);
			System.out.println("데이터 입력이 완료되었습니다.");
			stack++;
			break;
		case SubMenuItem.SCHOOL:
			System.out.print("전공 : ");
			String major = sc.nextLine();
			System.out.print("학년 : ");
			int grade = sc.nextInt();
			sc.nextLine();
			pi[stack] = new PhoneSchoolInfo(name, number, major, grade);
			System.out.println("데이터 입력이 완료되었습니다.");
			stack++;
			break;
		case SubMenuItem.COMPANY:
			System.out.print("회사 : ");
			String company = sc.nextLine();
			pi[stack] = new PhoneCompanyInfo(name, number, company);
			System.out.println("데이터 입력이 완료되었습니다.");
			stack++;
			break;
		default:
			break;
		}
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
					System.out.println("-------------");
					pi[i].showPhoneInfo();
					check = 1;
				}
			}
			if (check == 0) {
				System.out.println("데이터가 없습니다.");
				return;
			}
			System.out.println("-------------");
			System.out.println("데이터 검색이 완료되었습니다.");
		}
	}

	public void dataDelete() {
		System.out.println("데이터 삭제를 시작합니다.");
		System.out.print("이름 : ");
		String name = sc.nextLine();
		int check = 0;
		if (stack == 0) {
			System.out.println("데이터가 없습니다.");
		} else {
			for (int i = 0; i < stack; i++) {
				if (name.equals(pi[i].name)) {
					for (int j = i; j <= stack - 1; j++) {
						pi[j] = pi[j + 1];
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
		if (stack == 0) {
			System.out.println("데이터가 없습니다");
		} else {
			for (int i = 0; i < stack; i++) {
				pi[i].showPhoneInfo();
			}
			System.out.println("데이터 출력이 완료되었습니다.");
		}
	}
}
