package address.utils;

public class MyStringParser {
    
//	@Test
//	public void getid() {
//		int memberId = Integer.parseInt("200.ȫ�浿".split("[.]")[0]);  
//		System.out.println(memberId);
//	}
	
	//.�� �Ľ��� �ȵ� //. , [.] ������
	public static int getid(String selectedList) {
		return Integer.parseInt(selectedList.split("\\.")[0]);
		
	}
}
