package project.shop.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.shop.domain.member.Member;
import project.shop.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    void join() {
        Member member = Member.createMember("memberId", "test!", "kang", "11", "22", "33");
        Long saveId = memberService.join(member);
        Member findMember = memberRepository.findById(saveId).orElse(null);
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void member_validation() {
        Member member1 = Member.createMember("memberId", "test!", "kang", "11", "22", "33");
        Member member2 = Member.createMember("memberId", "test!", "kang", "11", "22", "33");
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

}