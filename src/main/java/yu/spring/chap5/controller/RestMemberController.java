// 21912083 김대영
package yu.spring.chap5.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yu.spring.chap5.model.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestMemberController {
    private final MemberRepository memberRepository;

    public RestMemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @RequestMapping("/query")
    public List<Member> queryMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String site ) {
        if (name != null) {
            if (email != null)
                return memberRepository.findByNameAndEmail(name, email);
            else
                return memberRepository.findByName(name);
        }
        else if (firstname != null)
            return memberRepository.findByNameStartingWith(firstname);
        else if (email != null)
            return memberRepository.findByEmail(email);
        else if (site != null)
            return memberRepository.findByEmailContaining(site);
        else
            return memberRepository.findAll();
    }

    @RequestMapping("/list")
    public List<Member> listMembers(Sort sort) {
        return memberRepository.findAll(sort);
    }

    @RequestMapping("/paging")
    public Page<Member> pagingMembers(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @RequestMapping("/summary1")
    public List<SummaryRecord1> querySummary1(
            @RequestParam(required = false) String site) {
        return memberRepository.findSummaryDTO1ByEmailContaining(site);
    }

    @RequestMapping("/summary2")
    public List<SummaryRecord2> querySummary2() {
        return memberRepository.findSummary2_JPQL();
        // return memberRepository.findSummary2_Native();
    }

    @RequestMapping("/summary3")
    public List<SummaryRecord3> querySummary3() {
        return memberRepository.findSummary3_JPQL();
        // return memberRepository.findSummary3_Native();
    }
}
