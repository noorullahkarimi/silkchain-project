package com.silkchain.Silkchain.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    //redirect to landing page
    @RequestMapping("/")
    public String index() {
        System.out.println("hello");
        return "redirect:/template/index.html";
    }
    //redirect to login form
    @RequestMapping("/re-login")
    public String enter() {
        System.out.println("enter");
        return "redirect:/form/login.html";
    }
    //redirect to register form
    @RequestMapping("/re-register")
    public String register() {
        System.out.println("register");
        return "redirect:/form/register.html";
    }
    //redirect to admin form
    @RequestMapping("/admin")
    public String showDashboard(){
        return "redirect:/dashboard/dashboard.html";
    }
}
