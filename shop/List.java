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
        product.put("price", scanner.nextInt());
        
        System.out.println("수량을 입력해주세요");
        product.put("amount", scanner.nextInt());
        
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
	    	  
	    	  // for문으로 전체 상품 목록 출력
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
