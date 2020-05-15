package address.model;

import lombok.Builder;
import lombok.Data;

@Data
public class Member {
	private int id; // PK
	private String name; // �̸�
	private String phone; // ��ȭ��ȣ
	private String address; // �ּ�
	// �׷�:ģ��,ȸ��,�б�,����
	private GroupType groupType;

	// ����� ������
	@Builder
	public Member(int id, String name, String phone, String address, GroupType groupType) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.groupType = groupType;
	}

	@Override
	public String toString() {
		return  id+"."+name;
	}

	
}
