package ex01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		// DI(의존성 주입) 방식
		Greeter g = ctx.getBean("greeter", Greeter.class);
		String msg = g.greet("스프링");
		System.out.println(msg);
		ctx.close();
		
		// 객체선언 방식
		Greeter g2 = new Greeter();
		g2.setFormat("abcd %s");
		System.out.println(g2.greet("xyz"));
	}
}
