package project.shop.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.shop.domain.address.Address;
import project.shop.domain.member.Member;
import project.shop.repository.MemberRepository;
import project.shop.repository.MemberRepositoryQuery;
import project.shop.service.MemberService;
import project.shop.web.dto.MemberListForm;
import project.shop.web.dto.MemberSaveForm;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepositoryQuery memberRepositoryQuery;

    @GetMapping("/add")
    public String createForm(Model model) {
        model.addAttribute("memberSaveForm", new MemberSaveForm());
        return "members/createMemberForm";
    }

    @PostMapping("/add")
    public String create(@Valid @ModelAttribute MemberSaveForm memberSaveForm, BindingResult bindingResult) {
        Address address = new Address(memberSaveForm.getCity(), memberSaveForm.getStreet(), memberSaveForm.getZipcode());
        if (bindingResult.hasErrors()) {
            return "members/createMemberForm";
        }
        Member member = Member.createMember(memberSaveForm.getLoginId(), memberSaveForm.getPassword(), memberSaveForm.getName(), address);
        memberService.join(member);
        return "redirect:/members";
    }

    @GetMapping
    public String list(Model model) {
        List<MemberListForm> members = memberRepositoryQuery.findAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
