package ex03;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberListPrinter {
	
	@Autowired
	private MemberDao memberDao;
	@Autowired
	//@Qualifier("printer")
	private MemberSummaryPrinter printer;
	
//	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
//		this.memberDao = memberDao;
//		this.printer = printer;
//	}
	
	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		//람다구문
		members.forEach(member -> printer.print(member));
	}
	
	
}
