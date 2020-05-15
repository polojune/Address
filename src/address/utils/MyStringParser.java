package address.utils;

public class MyStringParser {
    
//	@Test
//	public void getid() {
//		int memberId = Integer.parseInt("200.È«±æµ¿".split("[.]")[0]);  
//		System.out.println(memberId);
//	}
	
	//.Àº ÆÄ½ÌÀÌ ¾ÈµÊ //. , [.] °¡´ÉÇÔ
	public static int getid(String selectedList) {
		return Integer.parseInt(selectedList.split("\\.")[0]);
		
	}
}
