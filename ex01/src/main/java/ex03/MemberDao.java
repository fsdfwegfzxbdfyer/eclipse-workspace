package ex03;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//Dao는 DB랑 연동하는거지만 test이기 때문에 임시구현
public class MemberDao {
	
	private static long nextId = 0;
	//맵(Map)은 key로 value를 얻어낸다는 점
	//HashMap은 본인의 메소드 외에 부모인Map의 메소드들을 강제 상속
	private Map<String, Member> map = new HashMap<>();
	
	public void insert(Member member) {
		member.setId(++nextId);
		//map.put = map에 데이터를 넣을때
		map.put(member.getEmail(), member);
	}
	
	public void update(Member member) {
		map.put(member.getEmail(), member);
	}
	
	public Member selectByEmail(String email) {
		return map.get(email);
	}
	
	public Collection<Member> selectAll(){
		return map.values();
	}
	
}
