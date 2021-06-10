/**
		@name Cart.java
		@title 장바구니
		@detail json파일(장바구니)에 기존에 있는 상품을 저장하고 출력하는 클래스
		@author 민정현
		@since 2021.06.03
	   	@version 1.0
	   	============edit log============
	   	Date - Author - Note
	   	2021.06.03 - 민정현 - 초안 작성
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


public class Cart {

	static Scanner scanner = new Scanner(System.in);
	static JSONArray array = new JSONArray();
	static JSONArray cart_array = new JSONArray();
	static JSONParser parser = new JSONParser();
	
	/**
	   @name input
	   @title 장바구니 상품 추가 메소드
	   @detail 장바구니 안에 상품을 추가하는 메소드
	   @author 민정현
	   @since 2021.06.03
	   @version 1.0
	   ============edit log============
	   Date - Author - Note
	   2021.06.03 - 민정현 - 초안 작성
	*/
	public static void input() {
		try {
			  // json 파일 읽기
			  Object object = parser.parse(new FileReader("product_list.json"));
			  
			  // Object에서 JSONArray로 변경
			  array = (JSONArray) object;
			  
			  // 장바구니에 추가할 상품의 이름 입력
			  System.out.println("장바구니에 추가할 상품의 이름을 입력해주세요");
			  String name = scanner.next();
			  
			  // for문으로 전체 탐색하여 입력받은 이름의 상품 찾기
			  for (int i=0; i<array.size(); i++) {
				  JSONObject obj = (JSONObject) array.get(i);
				  
				  if(obj.get("productName").equals(name)) {
					  
					  // cart.json 파일 읽기
					  Object object2 = parser.parse(new FileReader("cart.json"));	
					  
			    	  // Object에서 JSONArray로 변경
			    	  cart_array = (JSONArray) object2;
			    	  
			    	  // JSONArray(장바구니)에 상품 추가
			    	  cart_array.add(obj);
			    	  
					  // cart.json 파일에 저장
					  try {
				           FileWriter file = new FileWriter("cart.json", false);
				           file.write(cart_array.toJSONString());
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
	
	/**
	   @name output
	   @title 장바구니 출력 메소드
	   @detail 장바구니 안에 있는 상품 목록을 출력하는 메소드
	   @author 민정현
	   @since 2021.06.03
	   @version 1.0
	   ============edit log============
	   Date - Author - Note
	   2021.06.03 - 민정현 - 초안 작성
	*/
	public static void output() {
		 try {
	    	  // json 파일 읽기
	    	  Object object = parser.parse(new FileReader("cart.json"));
	    	  
	    	  // Object에서 JSONArray로 변경
	    	  cart_array = (JSONArray) object;
	    	  
	    	  // for문으로 전체 상품 목록 출력
	    	  for (int i=0; i<cart_array.size(); i++) {
		            JSONObject obj = (JSONObject) cart_array.get(i);
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
	   @title 장바구니 상품 삭제 메소드
	   @detail 장바구니 안에 있는 상품을 삭제하는 메소드
	   @author 민정현
	   @since 2021.06.03
	   @version 1.0
	   ============edit log============
	   Date - Author - Note
	   2021.06.03 - 민정현 - 초안 작성
	*/
	public static void delete() {
		try {
			  // json 파일 읽기
			  Object object = parser.parse(new FileReader("cart.json"));
			  
			  // Object에서 JSONArray로 변경
			  cart_array = (JSONArray) object;
			  
			  // 삭제할 상품의 id 입력
			  System.out.println("삭제할 상품의 이름을 입력해주세요");
			  String name = scanner.next();
			  
			  // for문으로 전체 탐색하여 입력받은 id와 일치하는 상품 제거
			  for (int i=0; i<cart_array.size(); i++) {
				  JSONObject obj = (JSONObject) cart_array.get(i);
				  if(obj.get("productName").equals(name)) {
					  System.out.println(obj.get("productName") + " 상품이 제거되었습니다.");
					  cart_array.remove(i);
					  // json 파일에 저장
					  try {
				           FileWriter file = new FileWriter("cart.json", false);
				           file.write(cart_array.toJSONString());
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
