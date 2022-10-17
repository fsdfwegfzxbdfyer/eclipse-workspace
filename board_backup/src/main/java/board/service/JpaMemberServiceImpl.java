package board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.entity.MemberEntity;
import board.repository.JpaMemberRepository;

@Service
public class JpaMemberServiceImpl implements JpaMemberService {

	@Autowired
	private JpaMemberRepository jpaMemberRepository;

	@Override
	public List<MemberEntity> selectMemberList() throws Exception {
		return (List<MemberEntity>) jpaMemberRepository.findAll();
	}

	@Override
	public MemberEntity saveMember(MemberEntity memberEntity) throws Exception {
		return jpaMemberRepository.save(memberEntity);
	}

	@Override
	public MemberEntity selectMemberDetail(int memberSeq) throws Exception {
		Optional<MemberEntity> optional = jpaMemberRepository.findById(memberSeq);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new Exception();
		}
	}

	@Override
	public void deleteMember(int memberSeq) throws Exception {
		jpaMemberRepository.deleteById(memberSeq);		
	}

	@Override
	public List<MemberEntity> selectMemberListByName(String memberName) throws Exception {
		return jpaMemberRepository.findByMemberNameContaining(memberName);
	}
}
