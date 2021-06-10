/**
	@name Main.java
	@title 메인 메뉴
	@detail 각 클래스의 메소드에 접근할 수 있는 메뉴 클래스
	@author 민정현
	@since 2021.06.03
	@version 1.0
	============edit log============
	Date - Author - Note
	2021.06.03 - 민정현 - 초안 작성
 */

package shop;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// Scanner 객체 생성
		Scanner scanner = new Scanner(System.in);
		
		// 메뉴 변수 초기화
		int menu = 0;
		int adminMenu = 0;
		int cartMenu = 0;
		
		// 사용자 메뉴 while문
		while(true) {
			System.out.println("--------------------");
			System.out.println("쇼핑몰 (사용자)");
			System.out.println("1. 상품 출력");
			System.out.println("2. 장바구니");
			System.out.println("3. 관리자 모드");
			System.out.println("--------------------");
			System.out.print("번호를 입력하세요 : ");
			
			// 메뉴 입력 후 switch문으로 해당 메소드 실행
			menu = scanner.nextInt();
			switch (menu) { // 메뉴
			
				case 1: // 상품 출력
					List.output();
					break;
					
				case 2: // 장바구니
					cartMenu = 0; // 장바구니 메뉴 변수 초기화
					
					// 장바구니 메뉴 while문
					while(cartMenu != 4) {
						System.out.println("--------------------");
						System.out.println("장바구니");
						System.out.println("1. 상품 추가");
						System.out.println("2. 상품 출력");
						System.out.println("3. 상품 삭제");
						System.out.println("4. 사용자 모드");
						System.out.println("--------------------");
						System.out.print("번호를 입력하세요 : ");
						
						// 메뉴 입력 후 switch문으로 해당 메소드 실행
						cartMenu = scanner.nextInt();
						switch (cartMenu) {
						
						case 1: // 장바구니 상품 목록
							Cart.input();
							break;
							
						case 2: // 장바구니 상품 제거
							Cart.output();
							break;
						
						case 3: // 장바구니 상품 제거
							Cart.delete();
							break;
							
						case 4: // while문에서 조건으로 빠져나옴
							break;
						}
					}
					break;
					
				case 3: // 관리자 모드
					adminMenu = 0; // 관리자 메뉴 변수 초기화
					
					// 관리자 메뉴 while문
					while(adminMenu != 4) {
						System.out.println("--------------------");
						System.out.println("쇼핑몰 (관리자)");
						System.out.println("1. 상품 추가");
						System.out.println("2. 상품 목록");
						System.out.println("3. 상품 삭제");
						System.out.println("4. 사용자 모드");
						System.out.println("--------------------");
						System.out.print("번호를 입력하세요 : ");
						
						// 메뉴 입력 후 switch문으로 해당 메소드 실행
						adminMenu = scanner.nextInt();
						switch (adminMenu) {
						
						case 1: // 상품 추가
							List.input();
							break;
							
						case 2: // 상품 목록
							List.output();
							break;
							
						case 3: // 상품 삭제
							List.delete();
							break;
							
						case 4: // 사용자 모드
							break; // while문에서 조건으로 빠져나옴
						}
					}
					break;
					
				default: // 예외
					System.out.println("유효하지 않은 값입니다.");
					break;
			}
		
		}
		
	}

}
