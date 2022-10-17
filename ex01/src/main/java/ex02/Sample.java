package ex02;

public class Sample {
	//Singleton Pattern
	//멤버 필드
	private static Sample s = null;
	
	//생성자
	private Sample() {
		System.out.println("생성자 호출");
	}
	
	public static Sample getInstance() {
		if(s == null) {
			s = new Sample();
		}
		return s;
	}
	
	public static void hello() {
		System.out.println("Hello");
	}

}
