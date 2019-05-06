package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.domain.ResListDTO;
import util.DBUtil;

public class ResListDAO {
	public static ArrayList<ResListDTO> getall(String regin,String fishSpecies) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ResListDTO> all = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from fishing where FISHING_ADDRESS like '%' || ? || '%' and fishing_species like '%' || ? || '%'");
			pstmt.setString(1, regin);
			pstmt.setString(2, fishSpecies);
			rs = pstmt.executeQuery();
			all = new ArrayList<>();
			while(rs.next()) {
				all.add(new ResListDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
		}finally {
			DBUtil.close(con, pstmt,rs);
		}
		
		return all;
	}
}
