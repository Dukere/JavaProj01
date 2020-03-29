package ver09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import ver09.PhoneInfo;

public class PhoneBookManager {

	Connection con;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;

	public PhoneBookManager() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin://@localhost:1521:orcl";
			String userid = "KOSMO";
			String userpw = "1234";
			con = DriverManager.getConnection(url, userid, userpw);

			if (con != null) {
				System.out.println("Oracle DB 연결성공");
			} else {
				System.out.println("연결실패ㅠㅠ");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Scanner sc = new Scanner(System.in);
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
		String sql = "insert into phonebook_tb values(seq_phonebook.nextval, ?, ?, ?)";
		System.out.println("데이터 입력을 시작합니다..");
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("전화번호 : ");
		String number = sc.nextLine();
		System.out.print("생년월일 : ");
		String birth = sc.nextLine();
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, number);
			psmt.setString(3, birth);
			psmt.executeUpdate();
			System.out.println("데이터 입력이 완료되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void dataSearch() {
		int check = 0;
		System.out.println("데이터 검색을 시작합니다.");
		System.out.print("이름 : ");
		String search = sc.nextLine();
		try {
		stmt = con.createStatement();
		String query = "SELECT name,phoneNum,birth FROM phonebook_tb where name like '%" + search + "%'";
		rs = stmt.executeQuery(query);
		while(rs.next()) {
			String name = rs.getString("name");
			String number = rs.getString("phoneNum");
			String birth = rs.getString("birth");
			System.out.printf("이름 : %s\n 번호 : %s\n 생일 : %s\n", name, number ,birth);			
			check = 1;
		}
		if (check == 0) {
			System.out.println("결과값 없음.");
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dataDelete() {
		String sql = "delete from phonebook_tb where name = ?";
		System.out.println("데이터 삭제를 시작합니다.");
		System.out.print("이름 : ");
		String name = sc.nextLine();
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.executeUpdate();
			System.out.println("데이터 삭제가 완료되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void dataAllShow() {
		System.out.println("데이터 출력을 시작하겠습니다.");
		try {
			stmt = con.createStatement();
			String query = "SELECT name,phoneNum,birth FROM phonebook_tb ";
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				String name = rs.getString("name");
				String number = rs.getString("phoneNum");
				String birth = rs.getString("birth");
				System.out.printf("이름 : %s\n 번호 : %s\n 생일 : %s\n", name, number ,birth);			
			}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
