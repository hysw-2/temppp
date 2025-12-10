package yu.spring.chap5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yu.spring.chap5.model.Member;
import yu.spring.chap5.model.MemberDTO;
import yu.spring.chap5.service.MemberService;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String member(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "listMember";
    }

    @GetMapping("/create")
    public String createGet(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "formMember";
    }

    @PostMapping("/create")
    public String createPost(@Validated MemberDTO memberDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "formMember";
        }
        memberService.savebyDTO(memberDTO);
        return "redirect:/member";
    }

    @GetMapping("/{id}")
    public String memberByIdGet(@PathVariable Integer id, Model model) {
        Member member = memberService.findById(id);
        model.addAttribute("member", member);
        return "detailMember";
    }

    @PostMapping("/update/{id}")
    public String updateMember(@PathVariable Integer id, Member member, Model model) {
        member.setId(id);
        if (member.getPassword() == null || member.getPassword().isEmpty()) {
            Member oldMember = memberService.findById(id);
            member.setPassword(oldMember.getPassword());
        }
        memberService.save(member);
        return "redirect:/member";
    }

    @PostMapping("/delete/{id}")
    public String deleteMember(@PathVariable Integer id) {
        memberService.deleteById(id);
        return "redirect:/member";
    }
}
