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
				  Object object = parser.parse(new FileReader("memberList.json"));
				  
				  // Object에서 JSONArray로 변경
				  array = (JSONArray) object;
				  
				// JSONObject 생성
		        JSONObject userInfo = new JSONObject();
		
		       
		     // 회원가입 메소드
				String id;
				
	        
	       	System.out.println("아이디 : "); 
				
				id = scanner.next();
				
				if (!isUniqueID(id)) {// 아이디가 중복됐으면 
					System.out.println("이미 사용중인 아이디 입니다. \n");
					return;
				}
				

				userInfo.put("id", id);
				System.out.println("암호: ");
				userInfo.put("pwd", scanner.next());
				System.out.println("이름: ");
				userInfo.put("name", scanner.next());
				  try {
	           FileWriter file = new FileWriter("memberList.json", false);
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
			
	

	private static boolean isUniqueID(String id) {
		// TODO Auto-generated method stub
		return false;
	}
}
		
		
	
