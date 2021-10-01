package project.shop.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.shop.domain.Address;
import project.shop.domain.Member;
import project.shop.repository.MemberRepositoryQuery;
import project.shop.service.MemberService;
import project.shop.web.dto.MemberListForm;
import project.shop.web.dto.MemberSaveForm;
import project.shop.web.dto.MemberUpdateForm;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable Long id, Model model) {
        Member member = memberService.findOneById(id).orElse(null);
        Address address = member.getAddress();
        MemberUpdateForm memberUpdateForm = new MemberUpdateForm(member.getLoginId(), member.getPassword(), member.getName(), address.getCity(), address.getStreet(), address.getZipcode());
        model.addAttribute("member", memberUpdateForm);
        return "members/updateMemberForm";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id
            , @Valid @ModelAttribute("member") MemberUpdateForm memberUpdateForm, BindingResult bindingResult) {
        Member member = memberService.findOneById(id).orElse(null);
        memberService.update(id, memberUpdateForm.getPassword(), memberUpdateForm.getCity(), memberUpdateForm.getStreet(), memberUpdateForm.getZipcode());
        return "redirect:/members";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        Member member = memberService.findOneById(id).orElse(null);
        memberService.delete(member);
        return "redirect:/members";
    }
}
