package ex03.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ex03.ChangePasswordService;
import ex03.DuplicateMemberException;
import ex03.MemberInfoPrinter;
import ex03.MemberListPrinter;
import ex03.MemberNotFoundException;
import ex03.MemberRegisterService;
import ex03.RegisterRequest;
import ex03.VersionPrinter;
import ex03.WrongIdPasswordException;
import ex03.config.AppConf1;
import ex03.config.AppConf2;
import ex03.config.AppCtx;

public class MainForSpring {

	// private static Assembler assembler = new Assembler();
	private static ApplicationContext ctx = null;
	
	
	public static void main(String[] args) throws Exception {
		
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		// AnnotationConfigApplicationContext 클래스의 생성자는 가변 인자를 가지므로 설정 클래스 목록을 콤마로 구분해서 전달
		// ctx = new AnnotationConfigApplicationContext(AppConf1.class, AppConf2.class);
		
		//ctx = new AnnotationConfigApplicationContext(AppConf1.class);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.println("명령어를 입력하세요 : ");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			
			if (command.startsWith("new")) {
				processNewCommand(command.split(" "));
				continue;
			} 
			else if (command.startsWith("change")) {
				processChangeCommand(command.split(" "));
				continue;
			}
			else if (command.equalsIgnoreCase("list")) {
				processListCommand(command.split(" "));
				continue;
			}
			else if (command.startsWith("info")) {
				processInfoCommand(command.split(" "));
				continue;
			}
			else if (command.equalsIgnoreCase("version")) {
				processVersionCommand(command.split(" "));
				continue;
			}
			
			printHelp();
		}
	}

	private static void processVersionCommand(String[] split) {
		VersionPrinter versionPrinter = ctx.getBean(VersionPrinter.class);
		versionPrinter.print();		
	}

	private static void processInfoCommand(String[] args) {
		if (args.length != 2) {
			printHelp();
			return;
		}
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(args[1]);
	}

	private static void processListCommand(String[] args) {
		MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAll();
	}
	
	private static void processChangeCommand(String[] args) {
		if (args.length != 4) {
			printHelp();
			return;
		}
		
		// ChangePasswordService changePwdSvc = assembler.getChangePasswordService();
		ChangePasswordService changePwdSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);
		
		try {
			changePwdSvc.changePassword(args[1], args[2], args[3]);
			System.out.println("암호가 변경되었습니다.");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.");
		}
	}
	
	private static void processNewCommand(String[] args) {
		if (args.length != 5) {
			printHelp();
			return;
		}
		
//		MemberDao memberDao = new MemberDao();
//		MemberRegisterService regSvc = new MemberRegisterService(memberDao);
		
		
		// MemberRegisterService regSvc = assembler.getMemberRegisterService();
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
		
				
		RegisterRequest req = new RegisterRequest();
		req.setEmail(args[1]);
		req.setName(args[2]);
		req.setPassword(args[3]);
		req.setConfirmPassword(args[4]);
		
		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호가 일치하지 않습니다.");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록되었습니다.");
		} catch (DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.");
		}
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("명령어 사용법:");
		System.out.println("> new 이메일 이름 암호 암호확인");
		System.out.println("> change 이메일 현재암호 변경암호");
		System.out.println("> list");
		System.out.println("> info 이메일");
		System.out.println();
	}
}
