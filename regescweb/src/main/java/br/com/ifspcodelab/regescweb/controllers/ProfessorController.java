package br.com.ifspcodelab.regescweb.controllers;

import br.com.ifspcodelab.regescweb.dto.RequisicaoFormProfessor;
import br.com.ifspcodelab.regescweb.models.Professor;
import br.com.ifspcodelab.regescweb.models.StatusProfessor;
import br.com.ifspcodelab.regescweb.repositories.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/professores")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    /*public ProfessorController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }*/ //Mesma coisa que o @Autowired do ProfessorRpository

    @GetMapping("")
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

    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormProfessor requisicao) {
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("listStatusProfessor", StatusProfessor.values());
        return mv;
    }

    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormProfessor requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Há erros!");

            ModelAndView mv = new ModelAndView("professores/new");
            mv.addObject("listStatusProfessor", StatusProfessor.values());
            return mv;

        } else {
            Professor professor = requisicao.toProfessor();
            this.professorRepository.save(professor);
            return new ModelAndView("redirect:/professores/" + professor.getId());
        }
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        Optional<Professor> optional = this.professorRepository.findById(id);

        if (optional.isPresent()) {
            Professor professor = optional.get();

            ModelAndView mv = new ModelAndView("professores/show");
            mv.addObject("professor", professor);
            return mv;

        } else {
            return new ModelAndView("redirect:/professores");
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RequisicaoFormProfessor requisicao) {
        Optional<Professor> optional = this.professorRepository.findById(id);

        if (optional.isPresent()) {
            Professor professor = optional.get();
            requisicao.fromProfessor(professor);

            ModelAndView mv = new ModelAndView("professores/edit");
            mv.addObject("professorId", professor.getId());
            mv.addObject("listStatusProfessor", StatusProfessor.values());
            return mv;

        } else {
            return new ModelAndView("redirect:/professores");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoFormProfessor requisicao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Há erros!");

            ModelAndView mv = new ModelAndView("professores/edit");
            mv.addObject("professorId", id);
            mv.addObject("listStatusProfessor", StatusProfessor.values());
            return mv;

        } else {
           Optional<Professor> optional = this.professorRepository.findById(id);

           if (optional.isPresent()) {
               Professor professor = requisicao.toProfessor(optional.get());
               this.professorRepository.save(professor);

               return new ModelAndView("redirect:/professores/" + professor.getId());

           } else {
               return new ModelAndView("redirect:/professores");
           }
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        try {
            this.professorRepository.deleteById(id);
            return "redirect:/professores";
        }
        catch (EmptyResultDataAccessException e) {
            return "redirect:/professores";
        }
    }
}