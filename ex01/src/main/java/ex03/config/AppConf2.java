package ex03.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ex03.ChangePasswordService;
import ex03.MemberDao;
import ex03.MemberInfoPrinter;
import ex03.MemberListPrinter;
import ex03.MemberPrinter;
import ex03.MemberRegisterService;
import ex03.VersionPrinter;

@Configuration
public class AppConf2 {
	
	//스프링 빈에 의존하는 다른 빈을 자동으로 주입하고 싶을 때 사용
	@Autowired
	//1. 스프링 설정 클랙스의 필드에 @Autowired 어노테이션을 붙히면
	//해당 타입의 빈을 찾아서 필드에 할당
	private MemberDao memberDao; 
	@Autowired
	private MemberPrinter memberPrinter;
	
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao);
	}
	//2. 설정 메서드에서는 필드를 사용해 필요한 빈을 주입 memberDao() => memberDao

	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
//		pwdSvc.setMemberDao(memberDao);
		return pwdSvc;
	}

	@Bean
	public MemberListPrinter listPrinter() {
		//return new MemberListPrinter(memberDao, memberPrinter);
		return new MemberListPrinter();
	}

	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		//의존 주입을 위한 코드를 작성하지 않아도됨
//		infoPrinter.setMemberDao(memberDao);
//		infoPrinter.setPrinter(memberPrinter);
		return infoPrinter;
	}

	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
	
//	@Bean
//	public VersionPrinter newVersionPrinter() {
//		VersionPrinter versionPrinter = new VersionPrinter();
//		versionPrinter.setMajorVersion(6);
//		versionPrinter.setMinorVersion(0);
//		return versionPrinter;
//	}
}
