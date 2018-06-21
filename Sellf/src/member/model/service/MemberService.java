package member.model.service;


import java.sql.Connection;

import common.JDBCTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {

	public int checkId(String id, String fullPath) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = JDBCTemplate.getConnection();
		int result = new MemberDao().checkId(conn, id,fullPath);
		JDBCTemplate.close(conn);
		return result;
	}

	public int memberJoin(Member m, String fullPath) {
		Connection conn = null;
		conn = JDBCTemplate.getConnection();
		int result = new MemberDao().memberJoin(conn, m,fullPath);
		JDBCTemplate.close(conn);
		return result;
	}

}
