package project.shop.web.dto;

import lombok.Getter;
import project.shop.domain.Address;

@Getter
public class MemberListForm {

    private Long id;
    private String loginId;
    private String name;

    private Address address;

    public MemberListForm(Long id, String loginId, String name, Address address) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.address = address;
    }

}
