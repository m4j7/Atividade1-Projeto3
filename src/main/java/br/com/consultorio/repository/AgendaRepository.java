package br.com.consultorio.repository;

import br.com.consultorio.entity.Agenda;
import br.com.consultorio.entity.StAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository

public interface AgendaRepository extends JpaRepository <Agenda, Long>{


    @Query("SELECT Agenda FROM Agenda " +
            "WHERE :datade BETWEEN Agenda.dataate AND Agenda.dataate " +
            "AND :dataAte BETWEEN Agenda.datade AND Agenda.dataate " +
            "AND Agenda.medico = :medico " +
            "AND Agenda.paciente = :paciente")
    public List<Agenda> conflitoMedicoPaciente(
            @Param("datade") LocalDateTime dataDe,
            @Param("dataate") LocalDateTime dataAte,
            @Param("medico") Long idMedico,
            @Param("paciente") Long idPaciente
    );

    @Query("UPDATE Agenda agenda " +
            "SET agenda.statusAgendamento = :agendaStatus " +
            "WHERE agenda.id = : idAgenda")
    public void updateStatus(@Param("agendaStatus") StAgendamento agendaStatus,
                             @Param("idAgenda") Long idAgenda);
}