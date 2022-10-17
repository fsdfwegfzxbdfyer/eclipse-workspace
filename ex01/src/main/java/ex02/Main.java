package ex02;

public class Main {

	public static void main(String[] args) {
		
		//before
//		Sample s1 = new Sample();
//		Sample s2 = new Sample();
		
		//after
		Sample s1 = Sample.getInstance();
		Sample s2 = Sample.getInstance();
		
		System.out.println(s1 ==  s2);
		s1.hello();
		s2.hello();

	}

}
