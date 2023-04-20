package br.com.ifspcodelab.regescweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello"; //O Spring vai retornar o arquivo templates/hello.html
    }
}
