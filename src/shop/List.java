/**
		@name List.java
		@title 상품 목록 및 관련 메소드
		@detail 상품을 json 형태로 저장하며 관련 메소드를 취급하는 클래스
		@author 김동엽
		@since 2021.06.03
	   	@version 1.0
	   	============edit log============
	   	Date - Author - Note
	   	2021.06.03 - 김동엽 - 초안 작성
*/

package shop;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class List {

	static Scanner scanner = new Scanner(System.in);
	static JSONArray array = new JSONArray();
	static JSONParser parser = new JSONParser();
	
	/**
		@name Input
		@title 상품 입력 메소드
		@detail 메소드로 JSON파일에 상품(ID,상품명,가격,수량) 입력
		@author 진다혜
		@since 2021.06.03
	   	@version 1.0
	   	============edit log============
	   	Date - Author - Note
	   	2021.06.03 - 진다혜 - 초안 작성
	*/
	public static void input() {
	   
		// JSONObject 생성
        JSONObject product = new JSONObject();
        
        // 상품 정보 입력
        System.out.println("id를 입력해주세요");
        product.put("id", scanner.next());
        
        System.out.println("상품명을 입력해주세요");
        product.put("productName", scanner.next());
        
        System.out.println("가격을 입력해주세요");
        product.put("price", scanner.next());
        
        System.out.println("수량을 입력해주세요");
        product.put("amount", scanner.next());
        
        // JSONArray에 상품 정보가 입력된 JSONObject 입력
        array.add(product);

        // json 파일에 쓰기
        try {
           FileWriter file = new FileWriter("product_list.json", false);
           file.write(array.toJSONString());
           file.flush();
           file.close();
        }
        catch (IOException e) {
           e.printStackTrace();
        }
	   
	}

	/**
	   @name output
	   @title 상품 출력
	   @detail 상품을 출력하는 메소드
	   @author 김영임
	   @since 2021.06.03
	   @version 1.0
	   ============edit log============
	   Date - Author - Note
	   2021.06.03 - 김영임 - 초안 작성
	 */
	public static void output() {
	      try {
	    	  // json 파일 읽기
	    	  Object object = parser.parse(new FileReader("product_list.json"));
	    	  
	    	  // Object에서 JSONArray로 변경
	    	  array = (JSONArray) object;
	    	  
	    	  // 상품 전체 출력
	    	  for (int i=0; i<array.size(); i++) {
	    		  
		            JSONObject obj = (JSONObject) array.get(i);
			            System.out.print("아이디: " + obj.get("id") + "\t");
			            System.out.print("이름: " + obj.get("productName") + "\t");
			            System.out.print("가격: " + obj.get("price") + "\t");
			            System.out.println("수량: " + obj.get("amount") + "\t");
		         }
		         
		      }
	      // 예외 처리
	      catch (FileNotFoundException e) { e.printStackTrace(); } 
	      catch (IOException e) { e.printStackTrace(); } 
	      catch (ParseException e) { e.printStackTrace(); }
	      
	   }

	/**
	   @name search
	   @title 상품 검색
	   @detail 상품을 검색하는 메소드
	   @author 민정현
	   @since 2021.06.10
	   @version 1.0
	   ============edit log============
	   Date - Author - Note
	   2021.06.10 - 민정현 - 초안 작성
	 */
	public static void search() {
		try {
			// json 파일 읽기
			Object object = parser.parse(new FileReader("product_list.json"));
  
			// Object에서 JSONArray로 변경
			array = (JSONArray) object;
  
			// 검색 키워드 입력
			Scanner scanner = new Scanner(System.in);
			System.out.println("검색 키워드를 입력해주세요");
			String word = scanner.next();
  
			// 검색 목록 생성
			JSONArray list = new JSONArray();
  
			// for문으로 문자열 비교 및 목록에 저장
			for (int i=0; i<array.size(); i++) {
				JSONObject obj = (JSONObject) array.get(i);
				String name = (String)obj.get("productName");
				if(name.contains(word)) {
			        list.add(obj);
				}
			}
			
			double totalPage = Math.ceil((double)list.size()/5);
			// 목록을 5개씩 출력
			for (int i=0; i<totalPage; i++) {
				System.out.println("");
				int page = i+1;
				System.out.println("(" + word + ")" + " 검색 결과 " + page + "번째 페이지");
				
				for(int j=0; j<5; j++) {
					if(j>=list.size()) break;
					JSONObject obj = (JSONObject) list.get(j);
					System.out.print(j + " - 이름: " + obj.get("productName") + "\t");
					System.out.println("가격: " + obj.get("price") + "\t");
				}
				
				System.out.println("상품 번호를 입력해주세요 (다음 페이지: 5, 돌아가기: 6)");
				int number = scanner.nextInt();
				switch(number) {
					case 0: List.detail((JSONObject)list.get(0)); break;
					case 1: List.detail((JSONObject)list.get(1)); break;
					case 2: List.detail((JSONObject)list.get(2)); break;
					case 3: List.detail((JSONObject)list.get(3)); break;
					case 4: List.detail((JSONObject)list.get(4)); break;
					case 5:
						for(int j=0; j<5; j++)
							list.remove(0);
						break;
					case 6:
						i = list.size();
						break;
					
				}
			}
		}
		
		// 예외 처리
		catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); } 
		catch (ParseException e) { e.printStackTrace(); }
			      
		}
	
	/**
	   @name detail
	   @title 상품 상세페이지 메소드
	   @detail 상품 상세 정보를 출력하는 메소드
	   @author 민정현
	   @since 2021.06.10
	   @version 1.0
	   ============edit log============
	   Date - Author - Note
	   2021.06.10 - 민정현 - 초안 작성
	*/
	public static void detail(JSONObject product) {
		System.out.println("이름: " + product.get("productName"));
		System.out.println("가격: " + product.get("price"));
		System.out.println("수량: " + product.get("amount"));
		System.out.println("1. 돌아가기 2. 장바구니에 담기");
		int number = scanner.nextInt();
		switch(number) {
			case 1:
				break;
			case 2:
				Cart.input(product);
				break;
			default:
				System.out.println("유효하지 않은 값입니다. 목록으로 돌아갑니다.");
				break;
		}
		
	}
	
	/**
	   @name delete
	   @title 상품 삭제 메소드
	   @detail 상품을 삭제하는 메소드
	   @author 김동엽
	   @since 2021.06.03
	   @version 1.2
	   ============edit log============
	   Date - Author - Note
	   2021.06.03 - 김동엽 - 초안 작성
	   2021.06.03 - 민정현 - productId type 변경 (integer=>String)
	   2021.06.03 - 민정현 - for문 조건 변경 및 json 파일 쓰기 추가
	*/
	public static void delete() {
		try {
			  // json 파일 읽기
			  Object object = parser.parse(new FileReader("product_list.json"));
			  
			  // Object에서 JSONArray로 변경
			  array = (JSONArray) object;
			  
			  // 삭제할 상품의 id 입력
			  System.out.println("삭제할 상품의 id를 입력해주세요");
			  String productId = scanner.next();
			  
			  // for문으로 전체 탐색하여 입력받은 id와 일치하는 상품 제거
			  for (int i=0; i<array.size(); i++) {
				  JSONObject obj = (JSONObject) array.get(i);
				  if(obj.get("id").equals(productId)) {
					  System.out.println(obj.get("productName") + " 상품이 제거되었습니다.");
					  array.remove(i);
					  // json 파일에 저장
					  try {
				           FileWriter file = new FileWriter("product_list.json", false);
				           file.write(array.toJSONString());
				           file.flush();
				           file.close();
				        }
				        catch (IOException e) {
				           e.printStackTrace();
				        }
				  }
			  }
		  }
		  // 예외 처리
		catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); } 
		catch (ParseException e) { e.printStackTrace(); }
	}
}
