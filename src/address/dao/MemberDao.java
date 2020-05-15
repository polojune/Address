package address.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import address.db.DBConnection;
import address.db.DBUtils;
import address.model.GroupType;
import address.model.Member;

public class MemberDao {
	private final static String TAG = "MemberDao:";
	private MemberDao() {}
	

	private static MemberDao instance = new MemberDao();

	public static MemberDao getInstance() {
		return instance;
	}

	// DML은 return값이 int 이다. 리턴되는 값은 변경된 행의 개수이다.
	public int 추가(Member member) {
		final String SQL = "INSERT INTO member(id,name,phone,address,groupType) VALUES(member_seq.nextval,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 1.스트림 연결
			conn = DBConnection.getConnection();
			// 2.버퍼달기(?를 쓸수 있는 버퍼)
			pstmt = conn.prepareStatement(SQL);
			// 3.물음표 완성
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getGroupType().toString());
			// 4.쿼리 전송(flush + commit)

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			System.out.println("추가 오류:" + e.getMessage());
		} finally {// 무조건 실행
            DBUtils.close(conn, pstmt);
		}
		return -1;
	}

	public int 삭제(int id) {
		return -1;
	}

	public int 수정(Member m) {
		return -1;
	}

	// DQL 은 return 값이 ResultSet == Cursor
	public Member 상세보기(int id) {
		return null;
	}

	public List<Member> 전체목록() {
		final String SQL = "SELECT * FROM member ORDER BY id";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		List<Member>members = new ArrayList<>();
		try {
			// 1.스트림 연결
			conn = DBConnection.getConnection();
			// 2.버퍼달기(?를 쓸수 있는 버퍼)
			pstmt = conn.prepareStatement(SQL);
			// 3.물음표 완성
		    
			// 4.쿼리 전송(flush + rs받기)

			rs = pstmt.executeQuery();
			while(rs.next()) { //return 값이 true,false
				members.add(Member.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.phone(rs.getString("phone"))
						.address(rs.getString("address"))
						.groupType(GroupType.valueOf(rs.getString("groupType")))
						.build());
			}
			return members;
		} catch (Exception e) {
			System.out.println(TAG+"전체목록 오류:" + e.getMessage());
		} finally {// 무조건 실행
            DBUtils.close(conn, pstmt,rs);
		}
		return null;
	
	}

	public List<Member> 그룹목록(GroupType group) {
		return null;
	}
}
