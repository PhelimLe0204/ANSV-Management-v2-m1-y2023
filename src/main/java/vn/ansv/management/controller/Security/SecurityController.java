package vn.ansv.management.controller.Security;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Principal principal) {
        return principal != null ? "redirect:/" : "login";
    }
}
