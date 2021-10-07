package project.shop.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.shop.domain.Member;
import project.shop.repository.member.MemberRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Long join(Member member) {
        //member validation 체크 구현 후
        validateDuplicateMember(member);
        //저장
        return memberRepository.save(member);
    }

    @Transactional
    @Override
    public void update(Long id, String password, String city, String street, String zipcode) {
        Member findMember = memberRepository.findById(id).orElseThrow(NoSuchElementException::new);
        findMember.updateMember(password, city, street, zipcode);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Member member) {
        memberRepository.delete(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.finByLoginId(member.getLoginId()).orElse(null);
        if (findMember != null) {
            throw new IllegalStateException("로그인 아이디가 중복됩니다");
        }
    }

    @Override
    public Optional<Member> findOneById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Optional<Member> findOneByLoginId(String loginId) {
        return memberRepository.finByLoginId(loginId);
    }

    @Override
    public List<Member> findOneByName(String name) {
        return memberRepository.findByName(name);
    }

    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

}
