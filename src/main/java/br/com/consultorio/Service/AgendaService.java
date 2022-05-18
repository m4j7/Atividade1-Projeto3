package br.com.consultorio.Service;

import br.com.consultorio.entity.Agenda;
import br.com.consultorio.entity.Historico;
import br.com.consultorio.entity.StAgendamento;
import br.com.consultorio.repository.AgendaRepository;
import br.com.consultorio.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class AgendaService {
    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    private Historico historico;

    public void insert(Agenda agenda, String observacao){
        this.validarFormulario(agenda, observacao);
        this.saveTransaction(agenda);
    }

    public void update(Agenda agenda, String observacao){
        this.validarFormulario(agenda, observacao);
        this.saveTransaction(agenda);
    }

    public Optional<Agenda> findById(Long id){
        return this.agendaRepository.findById(id);
    }

    public Page<Agenda> listAll(Pageable pageable){
        return this.agendaRepository.findAll(pageable);
    }

    @Transactional
    public void saveTransaction(Agenda agenda){
        this.agendaRepository.save(agenda);
    }

    public void validarFormulario(Agenda agenda, String observacao){

        if(agenda.getStatusAgendamento() == null){

            agenda.setStatusAgendamento(StAgendamento.pendente);

            if(agenda.getDatade() == null){
                throw new RuntimeException(("Data inicial não informada"));
            }
            if(agenda.getDataate() == null){
                throw new RuntimeException(("Data final não informada"));
            }
            if(agenda.getDatade().compareTo(LocalDateTime.now()) > 0){
                throw new RuntimeException(("Data inválida"));
            }

            Historico historico = new Historico(
                    agenda,
                    observacao,
                    agenda.getSecretaria(),
                    agenda.getPaciente(),
                    LocalDateTime.now(),
                    agenda.getStatusAgendamento());

            historicoRepository.save(historico);
        }else{

            Historico historico = new Historico(
                    agenda,
                    observacao,
                    agenda.getSecretaria(),
                    agenda.getPaciente(),
                    LocalDateTime.now(),
                    agenda.getStatusAgendamento());

            historicoRepository.save(historico);
        }
    }
}
