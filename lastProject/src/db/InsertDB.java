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
			//json���� 
			Object obj = parser.parse(new FileReader("I:\\lastProject\\lastProject\\jsondata\\fish.txt"));
			JSONArray jsonArray = (JSONArray) ((JSONObject) obj).get("records");
			
			Iterator<JSONObject> iter = jsonArray.iterator();
			DriverManager.registerDriver(new OracleDriver());
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "FISH", "1234");
			//fishing_name,fishing_type,fishing_address,fishing_lat(number),fishing_lng(number),fishing_number,fishing_species,fishing_price
			pstmt = con.prepareStatement("insert into fishing values(?,?,?,?,?,?,?,?)");
			while(iter.hasNext()) {
				JSONObject jsondata = iter.next();
				pstmt.setString(1, (String) jsondata.get("�����͸�"));
				
				System.out.println("������ �� : " +(String) jsondata.get("�����͸�"));
				
				pstmt.setString(2, (String) jsondata.get("����������"));
				
				System.out.println("���������� : " +(String) jsondata.get("����������"));
				
				if(jsondata.containsKey("�����������ּ�")) {
					if (jsondata.get("�����������ּ�").equals("")) {
						System.out.println("����ֽ��ϴ�.");
						pstmt.setString(3, "not");
					} else {
						pstmt.setString(3, (String) jsondata.get("�����������ּ�"));
						System.out.println("�����������ּ� : " + (String) jsondata.get("�����������ּ�"));
					}
				}else {
					if(jsondata.get("���������θ��ּ�").equals("")) {
						System.out.println("����ֽ��ϴ�.");
						pstmt.setString(3, "not");
					}else {
						pstmt.setString(3, (String) jsondata.get("���������θ��ּ�"));
						System.out.println("�����������ּ� : " + (String) jsondata.get("���������θ��ּ�"));
					}
				}

				
				pstmt.setDouble(4, Double.parseDouble((String) jsondata.get("����")));
				
				System.out.println("���� : " +Double.parseDouble((String) jsondata.get("����")));
				
				pstmt.setDouble(5, Double.parseDouble((String) jsondata.get("�浵")));
				
				System.out.println("�浵 : " + Double.parseDouble((String) jsondata.get("�浵")));
				if(jsondata.containsKey("��������ȭ��ȣ")) {
					if (jsondata.get("��������ȭ��ȣ").equals("")) {
						System.out.println("����ֽ��ϴ�.");
						pstmt.setString(6, "not");
					} else {
						pstmt.setString(6, (String) jsondata.get("��������ȭ��ȣ"));
						System.out.println("��������ȭ��ȣ : " + (String) jsondata.get("��������ȭ��ȣ"));
					}
				}else {
					pstmt.setString(6, "not");
				}
				
				
				pstmt.setString(7, (String) jsondata.get("�ֿ����"));
				
				System.out.println("�ֿ���� : " +(String) jsondata.get("�ֿ����"));
				
				pstmt.setString(8, (String) jsondata.get("�̿���"));
				
				System.out.println("�̿��� : " +(String) jsondata.get("�̿���"));
				pstmt.executeUpdate();
			}
			System.out.println("DB���� �Ϸ�");
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
