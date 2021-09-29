package project.shop.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.shop.domain.member.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;

    Member saveMember;
    Long saveId;

    @BeforeEach
    void setUp() {
        saveMember = Member.createMember("memberId", "test!", "kang", "11", "22", "33");
        saveId = memberRepository.save(saveMember);
    }

    @Test
    void 회원가입() {
        Optional<Member> opMember = memberRepository.findById(saveId);
        Member findMember = opMember.orElse(null);
        assertThat(saveMember).isEqualTo(findMember);
    }

    @Test
    void 검색_by_loginId() {
        String loginId = "memberId";
        Optional<Member> opMember = memberRepository.finByLoginId(loginId);
        Member findMember = opMember.orElse(null);
        assertThat(findMember.getLoginId()).isEqualTo(loginId);
    }

    @Test
    void 검색_모든_회원() {
        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(1);
    }

    @Test
    void 검색_by_name() {
        String name = "kang";
        List<Member> members = memberRepository.findByName(name);
        assertThat(members.size()).isEqualTo(1);
    }

}