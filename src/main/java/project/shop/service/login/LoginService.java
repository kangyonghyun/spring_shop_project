package project.shop.service.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.shop.domain.Member;
import project.shop.service.member.MemberService;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberService memberService;

    public Member login(String loginId, String password) {
        Member member = memberService.findOneByLoginId(loginId).orElseThrow();
        member = password.equals(member.getPassword()) == true ? member : null;
        return member;
    }

}
