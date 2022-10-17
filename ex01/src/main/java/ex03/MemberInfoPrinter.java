package ex03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberInfoPrinter {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberPrinter printer;
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	@Autowired
	@Qualifier("summaryPrinter")
	public void setPrinter(MemberSummaryPrinter printer) {
		this.printer = printer;
	}
	
	public void printMemberInfo(String email) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			System.out.println("일치하는 데이터가 없습니다.");
			 return;
		}
		printer.print(member);
	}
	public void printAll() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
