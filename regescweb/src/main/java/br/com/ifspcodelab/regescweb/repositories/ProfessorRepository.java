package br.com.ifspcodelab.regescweb.repositories;

import br.com.ifspcodelab.regescweb.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> { }
