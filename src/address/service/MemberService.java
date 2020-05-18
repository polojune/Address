package address.service;

import java.util.List;

import javax.swing.JTextField;

import address.dao.MemberDao;
import address.model.GroupType;
import address.model.Member;

public class MemberService {

	private MemberService() {
	}

	private static MemberService instance = new MemberService();

	public static MemberService getInstance() {
		return instance;
	}

	private MemberDao memberDao = MemberDao.getInstance();

	public int �ּҷ��߰�(Member member) {
		// 3.Dao�� �����ؼ� �߰��Լ� ȣ��(Member)
	
		return memberDao.�߰�(member);

	}
	public List<Member>��ü���(){
		return memberDao.��ü���();
		
	}
	public Member �󼼺���(int memberId) {
		return memberDao.�󼼺���(memberId);
	}
	public int �����ϱ�(int memberId){
		 return memberDao.����(memberId);
	}
	public int �����ϱ�(Member m) {
		 return memberDao.����(m);
	}
    public List<Member>�׷���(GroupType groupType){
    	return memberDao.�׷���(groupType);
    }
}
