package ex03.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ex03.ChangePasswordService;
import ex03.DuplicateMemberException;
import ex03.MemberNotFoundException;
import ex03.MemberRegisterService;
import ex03.RegisterRequest;
import ex03.WrongIdPasswordException;
import ex03.assembler.Assembler;

public class MainForAssembler {
	
	public static void main(String[] args) throws Exception {
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
			
			printHelp();
		}
	}

	private static void processChangeCommand(String[] args) {
		if (args.length != 4) {
			printHelp();
			return;
		}
		
		ChangePasswordService changePwdSvc = assembler.getChangePasswordService();
		
		try {
			changePwdSvc.changePassword(args[1], args[2], args[3]);
			System.out.println("암호가 변경되었습니다.");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.");
		}
	}

	private static Assembler assembler = new Assembler();
	
	private static void processNewCommand(String[] args) {
		if (args.length != 5) {
			printHelp();
			return;
		}
		
//		MemberDao memberDao = new MemberDao();
//		MemberRegisterService regSvc = new MemberRegisterService(memberDao);
		MemberRegisterService regSvc = assembler.getMemberRegisterService();
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
		System.out.println();
	}
}
