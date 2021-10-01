package project.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "member")
    private List<Order> orders;

    public Member(String loginId, String password, String name, Address address) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public boolean isEqualLoginId(String loginId) {
        return this.loginId.equals(loginId);
    }

    public void updateMember(String password, String city, String street, String zipcode) {
        this.password = password;
        this.address = new Address(city, street, zipcode);
    }

    public static Member createMember(String loginId, String password, String name,
                                      Address address) {
        return new Member(loginId, password, name, address);
    }

}
