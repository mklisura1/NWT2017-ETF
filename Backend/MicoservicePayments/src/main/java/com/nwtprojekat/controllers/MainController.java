package com.nwtprojekat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Hare on 17.03.2017..
 */
@Controller
public class MainController {
    @RequestMapping("/")
    @ResponseBody
    public String index() {

        return "redirect:swagger-ui.html";
    }
}
