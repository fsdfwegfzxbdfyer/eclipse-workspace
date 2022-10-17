package ex03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ex03.ChangePasswordService;
import ex03.MemberDao;
import ex03.MemberInfoPrinter;
import ex03.MemberListPrinter;
import ex03.MemberPrinter;
import ex03.MemberRegisterService;
import ex03.VersionPrinter;

@Configuration
@Import(AppConf2.class)
public class AppConf1 {

	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}

	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}

}
