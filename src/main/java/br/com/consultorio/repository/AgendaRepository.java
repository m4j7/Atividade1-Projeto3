package br.com.consultorio.repository;

import br.com.consultorio.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AgendaRepository extends JpaRepository <Agenda, Long>{
}