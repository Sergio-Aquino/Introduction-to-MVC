package br.com.ifspcodelab.regescweb.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    @GetMapping("/hello-servlet")
    public String hello(HttpServletRequest request) {
        request.setAttribute("nome", "Sergio");
        return "hello"; //O Spring vai retornar o arquivo templates/hello.html
    }

    @GetMapping("/hello-model")
    public String hello(Model model) {
        model.addAttribute("nome", "Sergio Gabriel");
        return "hello"; //O Spring vai retornar o arquivo templates/hello.html
    }

    @GetMapping("/hello-mview")
    public ModelAndView hello() {
        ModelAndView mv = new ModelAndView("hello"); //Nome do arquivo html a ser exibido
        mv.addObject("nome", "Sergio Gabriel de Lima Aquino");
        return mv; //O Spring vai retornar (renderizar) o arquivo templates/hello.html
    }
}
