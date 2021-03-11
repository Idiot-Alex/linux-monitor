package com.hotstrip.linux.monitor.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }
}
