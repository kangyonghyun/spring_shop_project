package project.shop.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.shop.domain.address.Address;
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
        Address address = new Address("11", "22", "33");
        Member member = Member.createMember("memberId", "test!", "kang", address);
        Long saveId = memberService.join(member);
        Member findMember = memberRepository.findById(saveId).orElse(null);
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void member_validation() {
        Address address = new Address("11", "22", "33");
        Member member1 = Member.createMember("memberId", "test!", "kang", address);
        Member member2 = Member.createMember("memberId", "test!", "kang", address);
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

    @Test
    void member_update() {
        Address address = new Address("11", "22", "33");
        Member member = Member.createMember("memberId", "test!", "kang", address);
        memberService.join(member);
        memberService.update(member.getId(), "test!!", "2222", "3333", "4444");
        Member updatedMember = memberService.findOneById(member.getId()).orElse(null);
        assertThat(updatedMember.getPassword()).isEqualTo("test!!");
    }

    @Test
    void member_delete() {
        Address address = new Address("11", "22", "33");
        Member member = Member.createMember("memberId", "test!", "kang", address);
        memberService.join(member);
        memberService.delete(member);

        assertThat(memberService.findMembers().size()).isEqualTo(0);
    }

}