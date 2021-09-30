package project.shop.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.shop.domain.address.Address;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;
    private String password;
    private String name;

    @Embedded
    private Address address;

    public Member(String loginId, String password, String name, Address address) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public static Member createMember(String loginId, String password, String name,
                               String city, String street, String zipcode) {

        Address address = new Address(city, street, zipcode);
        Member member = new Member(loginId, password, name, address);

        return member;
    }

    public boolean isEqualLoginId(String loginId) {
        return this.loginId == loginId;
    }

    public void updateMember(String password, String city, String street, String zipcode) {
        this.password = password;
        Address address = new Address(city, street, zipcode);
        this.address = address;
    }

    public void deleteMember() {

    }
}
