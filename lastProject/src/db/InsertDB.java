package db;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import oracle.jdbc.OracleDriver;
import util.DBUtil;

public class InsertDB {
	public static void main(String[] args){
		JSONParser parser = new JSONParser();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			//json파일 
			Object obj = parser.parse(new FileReader("I:\\lastProject\\lastProject\\jsondata\\fish.txt"));
			JSONArray jsonArray = (JSONArray) ((JSONObject) obj).get("records");
			
			Iterator<JSONObject> iter = jsonArray.iterator();
			DriverManager.registerDriver(new OracleDriver());
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "FISH", "1234");
			//fishing_name,fishing_type,fishing_address,fishing_lat(number),fishing_lng(number),fishing_number,fishing_species,fishing_price
			pstmt = con.prepareStatement("insert into fishing values(?,?,?,?,?,?,?,?)");
			while(iter.hasNext()) {
				JSONObject jsondata = iter.next();
				pstmt.setString(1, (String) jsondata.get("낚시터명"));
				
				System.out.println("낚시터 명 : " +(String) jsondata.get("낚시터명"));
				
				pstmt.setString(2, (String) jsondata.get("낚시터유형"));
				
				System.out.println("낚시터유형 : " +(String) jsondata.get("낚시터유형"));
				
				if(jsondata.containsKey("소재지지번주소")) {
					if (jsondata.get("소재지지번주소").equals("")) {
						System.out.println("비어있습니다.");
						pstmt.setString(3, "not");
					} else {
						pstmt.setString(3, (String) jsondata.get("소재지지번주소"));
						System.out.println("소재지지번주소 : " + (String) jsondata.get("소재지지번주소"));
					}
				}else {
					if(jsondata.get("소재지도로명주소").equals("")) {
						System.out.println("비어있습니다.");
						pstmt.setString(3, "not");
					}else {
						pstmt.setString(3, (String) jsondata.get("소재지도로명주소"));
						System.out.println("소재지지번주소 : " + (String) jsondata.get("소재지도로명주소"));
					}
				}

				
				pstmt.setDouble(4, Double.parseDouble((String) jsondata.get("위도")));
				
				System.out.println("위도 : " +Double.parseDouble((String) jsondata.get("위도")));
				
				pstmt.setDouble(5, Double.parseDouble((String) jsondata.get("경도")));
				
				System.out.println("경도 : " + Double.parseDouble((String) jsondata.get("경도")));
				if(jsondata.containsKey("낚시터전화번호")) {
					if (jsondata.get("낚시터전화번호").equals("")) {
						System.out.println("비어있습니다.");
						pstmt.setString(6, "not");
					} else {
						pstmt.setString(6, (String) jsondata.get("낚시터전화번호"));
						System.out.println("낚시터전화번호 : " + (String) jsondata.get("낚시터전화번호"));
					}
				}else {
					pstmt.setString(6, "not");
				}
				
				
				pstmt.setString(7, (String) jsondata.get("주요어종"));
				
				System.out.println("주요어종 : " +(String) jsondata.get("주요어종"));
				
				pstmt.setString(8, (String) jsondata.get("이용요금"));
				
				System.out.println("이용요금 : " +(String) jsondata.get("이용요금"));
				pstmt.executeUpdate();
			}
			System.out.println("DB저장 완료");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
