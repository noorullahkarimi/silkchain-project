package com.silkchain.Silkchain.api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
//redirect to root address
    @RequestMapping("/")
    public String index() {
        System.out.println("hello");
        return "redirect:/template/index.html";
    }
    @RequestMapping("/enter")
    public String enter() {
        System.out.println("enter");
        return "redirect:/dashboard/login.html";
    }
}
