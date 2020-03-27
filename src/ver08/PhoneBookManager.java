package ver08;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import ver08.PhoneInfo;

public class PhoneBookManager {

	Scanner sc = new Scanner(System.in);
	HashSet<PhoneInfo> pi = new HashSet<PhoneInfo>(100);;
	int check = 0;

	public PhoneBookManager() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/ver08/PhoneBook.obj"));
			while(true) {
			PhoneInfo inData = (PhoneInfo)in.readObject();
			if(inData == null) break;
			pi.add(inData);
			}
			in.close();
		} catch (IOException e) {
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 

	}

	public void printMenu() {

		while (true) {
			try {
				System.out.println("선택하세요...");
				System.out.println("1.데이터 입력");
				System.out.println("2.데이터 검색");
				System.out.println("3.데이터 삭제");
				System.out.println("4.주소록 출력");
				System.out.println("5.프로그램 종료");
				System.out.print("선택:");
				check = sc.nextInt();
				sc.nextLine();
				if (check < 1 || check > 5) {
					MenuSelectException ex = new MenuSelectException();
					throw ex;
				}

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
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/ver08/PhoneBook.obj"));
					for (PhoneInfo info : pi) {
						out.writeObject(info);
					}
					System.exit(0);
				}
			} catch (InputMismatchException e) {
				System.out.println("문자 입력 발생 .. 숫자 입력하세용");
				sc.nextLine();
			} catch (MenuSelectException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("저장 실패 . . .");
			}
		}
	}

	public void adding(PhoneInfo pInfo) {
		if (pInfo instanceof PhoneSchoolInfo) {
			pInfo = (PhoneSchoolInfo) pInfo;
		}
		if (pInfo instanceof PhoneCompanyInfo) {
			pInfo = (PhoneCompanyInfo) pInfo;
		}
		if (!pi.add(pInfo)) {
			System.out.println("이름 중복 . . .");
			System.out.println("1.덮어 쓰기, 2.다시 입력");
			int check = sc.nextInt();
			sc.nextLine();
			if (check == 1) {
				pi.remove(pInfo);
				pi.add(pInfo);
				System.out.println("데이터 입력이 완료되었습니다.");
				return;
			} else if (check == 2) {
				return;
			}
		}
		System.out.println("데이터 입력이 완료되었습니다.");
	}

	public void dataInput() {
		try {
			System.out.println("데이터 입력을 시작합니다..");
			System.out.println("1.일반 2.동창 3.회사");
			int check = sc.nextInt();
			if (check < 1 || check > 3) {
				MenuSelectException ex = new MenuSelectException();
				throw ex;
			}

			sc.nextLine();
			System.out.print("이름 : ");
			String name = sc.nextLine();
			System.out.print("전화번호 : ");
			String number = sc.nextLine();
			switch (check) {
			case SubMenuItem.NORMAL:
				adding(new PhoneInfo(name, number));
				break;
			case SubMenuItem.SCHOOL:
				System.out.print("전공 : ");
				String major = sc.nextLine();
				System.out.print("학년 : ");
				int grade = sc.nextInt();
				sc.nextLine();
				adding(new PhoneSchoolInfo(name, number, major, grade));
				break;
			case SubMenuItem.COMPANY:
				System.out.print("회사 : ");
				String company = sc.nextLine();
				adding(new PhoneCompanyInfo(name, number, company));
				break;
			default:
				break;
			}
		} catch (MenuSelectException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("문자 입력 발생 .. 숫자 입력하세용");
			sc.nextLine();
		}

	}

	public void dataSearch() {

		int check = 0;
		System.out.println("데이터 검색을 시작합니다.");
		System.out.print("이름 : ");
		String name = sc.nextLine();
		Iterator<PhoneInfo> itr = pi.iterator();
		try {
			while (itr.hasNext()) {
				PhoneInfo info = itr.next();
				if (info.name.contains(name)) {
					System.out.println("데이터를 찾았습니다.");
					info.showPhoneInfo();
					check = 1;
				}
			}
			if (check == 0) {
				NullPointerException ex = new NullPointerException();
				throw ex;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("찾는 데이터가 없습니다.");
		}
	}

	public void dataDelete() {

		Iterator<PhoneInfo> itr = pi.iterator();
		System.out.println("데이터 삭제를 시작합니다.");
		System.out.print("이름 : ");
		String name = sc.nextLine();
		int check = 0;
		try {
			while (itr.hasNext()) {
				PhoneInfo info = itr.next();
				if (info.name.equals(name)) {
					System.out.println("데이터를 찾았습니다.");
					pi.remove(info);
					check = 1;
				}
			}
			if (check == 0) {
				NullPointerException ex = new NullPointerException();
				throw ex;
			}
			System.out.println("삭제 완료");
		} catch (NullPointerException e) {
			System.out.println("찾는 데이터가 없습니다.");
		}
	}

	public void dataAllShow() {
		try {
			System.out.println("데이터 출력을 시작하겠습니다.");
			for (PhoneInfo info : pi) {
				info.showPhoneInfo();
			}
			System.out.println("데이터 출력이 완료되었습니다.");
		} catch (NullPointerException e) {
			System.out.println("데이터가 없습니다.");
		}
	}
}
