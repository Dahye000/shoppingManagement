/**
	@name memberHandle.java
	@title 회원 관리
	@detail 회원 가입 및 로그인하는 클래스
	@author 진다혜
	@since 2021.06.10
	@version 1.3
	============edit log============
	Date - Author - Note
	2021.06.10 - 진다혜 - 초안 작성
	2021.06.10 - 진다혜 - 회원가입 메소드 추가
	2021.06.10 - 김영임 - 로그인 메소드 추가
	2021.06.10 - 김동엽 - 아이디 중복 처리 메소드 추가
	
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

public class memberHandle { 
	static JSONObject member = new JSONObject();
	static Scanner scanner = new Scanner(System.in);
	static JSONArray array = new JSONArray();
	static JSONParser parser = new JSONParser();
	
	/**
	@name InputUserInfo
	@title 회원가입 (아이디, 비번 입력)
	@detail 메소드로 JSON파일에 (ID,PW) 입력
	@author 진다혜
	@since 2021.06.10
   	@version 1.0
   	============edit log============
   	Date - Author - Note
   	2021.06.10 - 진다혜 - 초안 작성
	*/
	public static void memberInsert() {

        // json 파일에 쓰기
        try {
        	// json 파일 읽기
			  Object object = parser.parse(new FileReader("memberlist.json"));
			  
			  // Object에서 JSONArray로 변경
			  array = (JSONArray) object;
			  
			// JSONObject 생성
	        JSONObject userInfo = new JSONObject();
	
	       
	     // 아이디 입력
       	System.out.println("아이디 : "); 
			
       	String id = scanner.next();
			
			if (!isUniqueID(id)) {// 아이디가 중복됐으면 
				System.out.println("이미 사용중인 아이디 입니다. \n");
				return;
			}
			
			// 비밀번호, 이름 입력
			userInfo.put("id", id);
			System.out.println("암호: ");
			userInfo.put("pwd", scanner.next());
			System.out.println("이름: ");
			userInfo.put("name", scanner.next());
			
			// 회원 추가
			array.add(userInfo);
			
			// json 파일에 쓰기
			  try {
           FileWriter file = new FileWriter("memberlist.json", false);
           file.write(array.toJSONString());
           file.flush();
           file.close();
        }
        catch (IOException e) {
           e.printStackTrace();
        }
        }
			
			// 예외 처리
			catch (FileNotFoundException e) { e.printStackTrace(); } 
			catch (IOException e) { e.printStackTrace(); } 
			catch (ParseException e) { e.printStackTrace(); }
				      
	}
			
	

		/**
	@name memberLogin
	@title 로그인
	@detail 로그인 메소드
	@author 김영임
	@since 2021.06.10
	@version 1.0
	============edit log============
	Date - Author - Note
	2021.06.10 - 김영임 - 초안 작성
	*/
	public static void memberLogin() {
		try {
			  // json 파일 읽기
			  Object object = parser.parse(new FileReader("memberlist.json"));
			  
			  // Object에서 JSONArray로 변경
			  array = (JSONArray) object;
			  
			  // 로그인 메소드
		      System.out.print("아이디 : "); 
		      String id = scanner.next();
		      System.out.print("패스워드 : "); 
		      String pwd = scanner.next();
			  
	          for(int i = 0 ; i < array.size() ; i++) {
	        	  JSONObject obj = (JSONObject) array.get(i);
	        	  String gid = (String) obj.get("id");
	        	  String gpwd = (String) obj.get("pwd");
	        	  
			     if(gid.equals(id) && gpwd.equals(pwd)) {
				   System.out.println("환영합니다.");
				   member = obj;
				   return;
			     } 
			  }
	          System.out.println("로그인에 실패하였습니다.");
		}
			  
				  
		  // 예외 처리
		catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); } 
		catch (ParseException e) { e.printStackTrace(); }
	}
	
	
	/**
	@name isUniqueID
	@title 아이디 중복 처리
	@detail 아이디 중복 확인 후 boolean 리턴
	@author 김동엽
	@since 2021.06.10
		@version 1.0
		============edit log============
		Date - Author - Note
		2021.06.10 - 김동엽 - 초안 작성
	*/
	private static boolean isUniqueID(String uid){ // 아이디의 중복 여부를 리턴
		try {
			// json 파일 읽기
			Object object = parser.parse(new FileReader("memberlist.json"));
  
			// Object에서 JSONArray로 변경
			array = (JSONArray) object;
			
			// 회원이 없으면 트루 리턴
			if(array.size()==0) return true;
			
			// 중복되는 아이디가 있는지 검색
			for(int i=0; i<array.size(); i++) {
				JSONObject obj = (JSONObject)array.get(i);
				String id = (String)obj.get("id");
				// 중복이 있다면 false 리턴
				if(uid.equals(id)) {
					return false;
					
				}
			}
			
			
		}
		
		// 예외 처리
		catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); } 
		catch (ParseException e) { e.printStackTrace(); }
		
		// 중복이 없다면 트루 리턴
		return true;
		
	}
}

		

	
	
