package br.com.ifspcodelab.regescweb.controllers;

import br.com.ifspcodelab.regescweb.models.Professor;
import br.com.ifspcodelab.regescweb.models.StatusProfessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProfessorController {
    @GetMapping("/professores")
    public ModelAndView index() {
        Professor batman = new Professor("Batman", new BigDecimal(5000), StatusProfessor.ATIVO);
        batman.setId(1l);

        Professor coringa = new Professor("Coringa", new BigDecimal(10000), StatusProfessor.APOSENTADO);
        coringa.setId(2l);

        Professor mulherMaravilha = new Professor("Mulher Maravilha", new BigDecimal(15000), StatusProfessor.INATIVO);
        mulherMaravilha.setId(3l);

        List<Professor> professores = Arrays.asList(batman, coringa, mulherMaravilha);

        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);
        return mv;
    }
}
