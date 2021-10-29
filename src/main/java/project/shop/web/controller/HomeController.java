package project.shop.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.shop.domain.Member;
import project.shop.web.login.Login;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String homeLogin(@Login Member loginMember, Model model) {
        if (loginMember == null) {
            return "home";
        }
        model.addAttribute("loginMember", loginMember);
        return "loginHome";
    }

}
