package project.shop.web.dto;


import lombok.Getter;

@Getter
public class MemberUpdateForm {

    private String loginId;
    private String password;
    private String name;

    private String city;
    private String street;
    private String zipcode;

    public MemberUpdateForm(String loginId, String password, String name, String city, String street, String zipcode) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
