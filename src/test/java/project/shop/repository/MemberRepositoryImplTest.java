package project.shop.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.shop.domain.Address;
import project.shop.domain.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;

    Member createMember;
    Long saveId;

    @BeforeEach
    void setUp() {
        Address address = new Address("11", "22", "33");
        createMember = Member.createMember("memberId", "test!", "kang", address);
        saveId = memberRepository.save(createMember);
    }

    @Test
    void 회원가입() {
        Optional<Member> opMember = memberRepository.findById(saveId);
        Member findMember = opMember.orElse(null);
        assertThat(createMember).isEqualTo(findMember);
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
        assertThat(members.size()).isEqualTo(3);
    }

    @Test
    void 검색_by_name() {
        String name = "kang";
        List<Member> members = memberRepository.findByName(name);
        assertThat(members.size()).isEqualTo(1);
    }

}