package br.com.ifspcodelab.regescweb.dto;

import br.com.ifspcodelab.regescweb.models.Professor;
import br.com.ifspcodelab.regescweb.models.StatusProfessor;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

//Classe DTO (Data Transfer Object)
public class RequisicaoFormProfessor {
    @NotBlank
    @NonNull
    private String nome;
    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal salario;
    private StatusProfessor statusProfessor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatusProfessor getStatusProfessor() {
        return statusProfessor;
    }

    public void setStatusProfessor(StatusProfessor statusProfessor) {
        this.statusProfessor = statusProfessor;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Professor toProfessor() {
        Professor professor = new Professor();
        professor.setNome(this.nome);
        professor.setSalario(this.salario);
        professor.setStatusProfessor(this.statusProfessor);
        return professor;
    }

    public void fromProfessor(Professor professor) {
        this.nome = professor.getNome();
        this.salario = professor.getSalario();
        this.statusProfessor = professor.getStatusProfessor();
    }

    @Override
    public String toString() {
        return "RequisicaoFormProfessor{" + "nome= " + nome  + ", salario= " + salario + ", statusProfessor= " + statusProfessor + '}';
    }
}