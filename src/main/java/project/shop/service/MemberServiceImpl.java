package project.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.shop.domain.member.Member;
import project.shop.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Long join(Member member) {
        //member validation 체크 구현 후
        validateDuplicateMember(member);
        //저장
        return memberRepository.save(member);
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
