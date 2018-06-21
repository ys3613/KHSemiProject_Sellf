package member.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import common.JDBCTemplate;
import member.model.vo.Member;


public class MemberDao {

	public int checkId(Connection conn, String id, String fullPath) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result=0;
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream(fullPath));
			String query = prop.getProperty("checkId");
//			System.out.println(id);
//			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if(rset.next())
			{
				result=1;
			}
			else
			{
				result=0;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int memberJoin(Connection conn, Member m, String fullPath) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result=0;
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream(fullPath));
			String query = prop.getProperty("memberJoin");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getUser_id());
			pstmt.setString(2, m.getUser_name());
			pstmt.setString(3, m.getUser_pwd());
			pstmt.setString(4, m.getUser_phone());
			pstmt.setString(5, m.getUser_birth());
			pstmt.setString(6, m.getUser_gender());
			pstmt.setString(7, m.getUser_addr());
			pstmt.setString(8, m.getUser_email());
			pstmt.setString(9, m.getUser_interest());
			
			result = pstmt.executeUpdate();
			if(result>0)
			{
				result=1;
				conn.commit();
			}
			else
			{
				result=0;
				conn.rollback();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
