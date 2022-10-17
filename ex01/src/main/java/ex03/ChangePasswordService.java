package ex03;

import org.springframework.beans.factory.annotation.Autowired;

public class ChangePasswordService {
	@Autowired
	private MemberDao memberDao;
	
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if (member == null) {
			throw new MemberNotFoundException();
		}
		
		member.changePassword(oldPwd, newPwd);
		
		memberDao.update(member);
	}
	
	// 세터(setter)를 통해서 의존 객체를 주입
//	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao;
//	}	
}
