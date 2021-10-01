package project.shop.service;

import project.shop.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Long join(Member member);

    void update(Long id, String password, String city, String street, String zipcode);

    void delete(Member member);

    Optional<Member> findOneById(Long id);

    Optional<Member> findOneByLoginId(String loginId);

    List<Member> findOneByName(String name);

    List<Member> findMembers();
}
