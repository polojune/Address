package address.service;

import java.util.List;

import address.dao.MemberDao;
import address.model.Member;

public class MemberService {

	private MemberService() {
	}

	private static MemberService instance = new MemberService();

	public static MemberService getInstance() {
		return instance;
	}

	private MemberDao memberDao = MemberDao.getInstance();

	public int 주소록추가(Member member) {
		// 3.Dao에 접근해서 추가함수 호출(Member)
	
		return memberDao.추가(member);

	}
	public List<Member>전체목록(){
		return memberDao.전체목록();
		
	}
}
