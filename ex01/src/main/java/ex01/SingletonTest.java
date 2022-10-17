package ex01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		//Greeter.class 타입의 "greeter"빈을 가져옴
		Greeter g1 = ctx.getBean("greeter" , Greeter.class);
		Greeter g2 = ctx.getBean("greeter" , Greeter.class);
		
		System.out.println("(g1 == g2) = " + (g1 == g2));
		
		ctx.close();
		
	}

}
