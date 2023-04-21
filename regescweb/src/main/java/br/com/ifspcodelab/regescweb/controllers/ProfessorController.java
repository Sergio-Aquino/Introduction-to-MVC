package br.com.ifspcodelab.regescweb.controllers;

import br.com.ifspcodelab.regescweb.dto.RequisicaoNovoProfessor;
import br.com.ifspcodelab.regescweb.models.Professor;
import br.com.ifspcodelab.regescweb.models.StatusProfessor;
import br.com.ifspcodelab.regescweb.repositories.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    /*public ProfessorController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }*/ //Mesma coisa que o @Autowired do ProfessorRpository

    @GetMapping("/professores")
    public ModelAndView index() {
       /* Professor batman = new Professor("Batman", new BigDecimal(5000), StatusProfessor.ATIVO);
        batman.setId(1l);

        Professor coringa = new Professor("Coringa", new BigDecimal(10000), StatusProfessor.APOSENTADO);
        coringa.setId(2l);

        Professor mulherMaravilha = new Professor("Mulher Maravilha", new BigDecimal(15000), StatusProfessor.INATIVO);
        mulherMaravilha.setId(3l); */

        List<Professor> professores = this.professorRepository.findAll();

        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);
        return mv;
    }

    @GetMapping("/professor/new")
    public ModelAndView nnew() {
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("statusProfessor", StatusProfessor.values());
        return mv;
    }

    @PostMapping("/professores")
    public String create(@Valid RequisicaoNovoProfessor requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Há erros!");
            return "redirect:/professor/new";

        } else {
            Professor professor = requisicao.toProfessor();
            this.professorRepository.save(professor);
            return "redirect:/professores";
        }
    }
}