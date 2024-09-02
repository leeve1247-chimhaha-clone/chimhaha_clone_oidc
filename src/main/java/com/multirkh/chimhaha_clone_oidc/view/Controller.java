package com.multirkh.chimhaha_clone_oidc.view;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/login")
    public String a()
    {return "login.html";}
}
