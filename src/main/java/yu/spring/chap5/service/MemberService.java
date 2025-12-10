package yu.spring.chap5.service;

import org.springframework.stereotype.Service;
import yu.spring.chap5.model.Member;
import yu.spring.chap5.model.MemberDTO;
import yu.spring.chap5.model.MemberRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Integer id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member savebyDTO(MemberDTO memberDTO) {
        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setPassword(memberDTO.getPassword());
        member.setEmail(memberDTO.getEmail());
        if (memberDTO.getRegdate() != null) {
            member.setRegdate(memberDTO.getRegdate());
        }
        else
            member.setRegdate(LocalDate.now());
        return memberRepository.save(member);
    }

    public void save(Member member) {
        memberRepository.save(member);
    }

    public void deleteById(Integer id) {
        memberRepository.deleteById(id);
    }
}
