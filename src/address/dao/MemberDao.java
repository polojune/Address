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

	// DML�� return���� int �̴�. ���ϵǴ� ���� ����� ���� �����̴�.
	public int �߰�(Member member) {
		final String SQL = "INSERT INTO member(id,name,phone,address,groupType) VALUES(member_seq.nextval,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 1.��Ʈ�� ����
			conn = DBConnection.getConnection();
			// 2.���۴ޱ�(?�� ���� �ִ� ����)
			pstmt = conn.prepareStatement(SQL);
			// 3.����ǥ �ϼ�
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getGroupType().toString());
			// 4.���� ����(flush + commit)

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			System.out.println("�߰� ����:" + e.getMessage());
		} finally {// ������ ����
            DBUtils.close(conn, pstmt);
		}
		return -1;
	}

	public int ����(Member m) {
		final String SQL = "UPDATE member set name = ? ,phone = ? , address = ?, groupType = ? WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1.��Ʈ�� ����
			conn = DBConnection.getConnection();
			// 2.���۴ޱ�(?�� ���� �ִ� ����)
			pstmt = conn.prepareStatement(SQL);
			// 3.����ǥ �ϼ�
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getAddress());
			pstmt.setString(4, m.getGroupType().toString());
			pstmt.setInt(5, m.getId());
		  
			// 4.���� ����(flush + rs�ޱ�)

			int result = pstmt.executeUpdate();
			
			
			return result;
		} catch (Exception e) {
			System.out.println(TAG+"���� ����:" + e.getMessage());
		} finally {// ������ ����
            DBUtils.close(conn, pstmt);
		}
		
		return -1;
		
		
	}

	public int ����(int memberId) {
		final String SQL = "DELETE  FROM member WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
	    Member member = null;
		try {
			// 1.��Ʈ�� ����
			conn = DBConnection.getConnection();
			// 2.���۴ޱ�(?�� ���� �ִ� ����)
			pstmt = conn.prepareStatement(SQL);
			// 3.����ǥ �ϼ�
		    pstmt.setInt(1,memberId);
			// 4.���� ����(flush + rs�ޱ�)

			int result = pstmt.executeUpdate();
			
			
			return result;
		} catch (Exception e) {
			System.out.println(TAG+"���� ����:" + e.getMessage());
		} finally {// ������ ����
            DBUtils.close(conn, pstmt);
		}
		
		return -1;
	}

	// DQL �� return ���� ResultSet == Cursor
	public Member �󼼺���(int id) {
		final String SQL = "SELECT * FROM member WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
	    Member member = null;
		try {
			// 1.��Ʈ�� ����
			conn = DBConnection.getConnection();
			// 2.���۴ޱ�(?�� ���� �ִ� ����)
			pstmt = conn.prepareStatement(SQL);
			// 3.����ǥ �ϼ�
		    pstmt.setInt(1, id);
			// 4.���� ����(flush + rs�ޱ�)

			rs = pstmt.executeQuery();
			if(rs.next()) { //return ���� true,false
			     member = Member.builder()
							.id(rs.getInt("id"))
							.name(rs.getString("name"))
							.phone(rs.getString("phone"))
							.address(rs.getString("address"))
							.groupType(GroupType.valueOf(rs.getString("groupType")))
							.build();
			}
			return member;
		} catch (Exception e) {
			System.out.println(TAG+"�󼼺��� ����:" + e.getMessage());
		} finally {// ������ ����
            DBUtils.close(conn, pstmt,rs);
		}
		return null;
	}

	public List<Member> ��ü���() {
		final String SQL = "SELECT * FROM member ORDER BY id";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		List<Member>members = new ArrayList<>();
		try {
			// 1.��Ʈ�� ����
			conn = DBConnection.getConnection();
			// 2.���۴ޱ�(?�� ���� �ִ� ����)
			pstmt = conn.prepareStatement(SQL);
			// 3.����ǥ �ϼ�
		    
			// 4.���� ����(flush + rs�ޱ�)

			rs = pstmt.executeQuery();
			while(rs.next()) { //return ���� true,false
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
			System.out.println(TAG+"��ü��� ����:" + e.getMessage());
		} finally {// ������ ����
            DBUtils.close(conn, pstmt,rs);
		}
		return null;
	
	}

	public List<Member> �׷���(GroupType groupType) {
		final String SQL = "SELECT * FROM member WHERE groupType = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		List<Member>members = new ArrayList<>();
		try {
			// 1.��Ʈ�� ����
			conn = DBConnection.getConnection();
			// 2.���۴ޱ�(?�� ���� �ִ� ����)
			pstmt = conn.prepareStatement(SQL);
			// 3.����ǥ �ϼ�
		    pstmt.setString(1,groupType.toString());
			// 4.���� ����(flush + rs�ޱ�)

			rs = pstmt.executeQuery();
			while(rs.next()) { //return ���� true,false
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
			System.out.println(TAG+"�׷��� ����:" + e.getMessage());
		} finally {// ������ ����
            DBUtils.close(conn, pstmt,rs);
		}
		return null;
	}
}
