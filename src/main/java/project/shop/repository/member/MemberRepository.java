package project.shop.repository.member;

import project.shop.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

   Long save(Member member);

   void delete(Member member);

   Optional<Member> findById(Long id);

   List<Member> findByName(String name);

   Optional<Member> finByLoginId(String loginId);

   List<Member> findAll();

}
