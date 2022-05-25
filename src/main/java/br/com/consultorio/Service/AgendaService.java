package br.com.consultorio.Service;

import br.com.consultorio.entity.*;
import br.com.consultorio.repository.AgendaRepository;
import br.com.consultorio.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Optional;

public class AgendaService {
    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    private Historico historico;

    public void insert(Agenda agenda, Secretaria secretaria){
        this.validationInsert(agenda, secretaria);
        this.agendaRepository.save(agenda);
    }

    public void update(Agenda agenda){
        this.validationUpdate(agenda);
        this.saveTransaction(agenda);
    }

    public void updateStatusRejeitado(Agenda agenda, Secretaria secretaria)
    {
        this.updateStatusRejeitado(agenda, secretaria);
        this.saveTransaction(agenda);
    }

    public void updateStatusAprovado(Agenda agenda, Secretaria secretaria)
    {
        this.updateStatusAprovado(agenda, secretaria);
        this.saveTransaction(agenda);
    }

    public void updateStatusCancelado(Agenda agenda, Secretaria secretaria, Paciente paciente)
    {
        this.updateStatusCancelado(agenda, secretaria, paciente);
        this.saveTransaction(agenda);
    }

    public void updateStatusCompareceu(Agenda agenda, Secretaria secretaria)
    {
        this.updateStatusCompareceu(agenda, secretaria);
    }

    public void updateStatusNaoCompareceu(Agenda agenda, Secretaria secretaria)
    {
        this.updateStatusNaoCompareceu(agenda, secretaria);
        this.saveTransaction(agenda);
    }

    public void updateStatusPendenteParaRejeitado(Agenda agenda, Secretaria secretaria)
    {
        if(secretaria != null)
        {
            Assert.isTrue(agendaRepository.findById(agenda.getId()).get().getStatusAgendamento().equals(StAgendamento.pendente), "Assert = false");
            Assert.isTrue(agenda.getStatusAgendamento().equals(StAgendamento.rejeitado), "Assert = false");

            agendaRepository.updateStatus(agenda.getStatusAgendamento(), agenda.getId());

            Historico historico = new Historico(
                    agenda,
                    agenda.getObservacao(),
                    agenda.getSecretaria(),
                    agenda.getPaciente(),
                    LocalDateTime.now(),
                    agenda.getStatusAgendamento());
            historicoRepository.save(historico);
        }
    }


    public void updateStatusPendenteParaAprovado(Agenda agenda, Secretaria secretaria)
    {
        if(secretaria != null)
        {
            Assert.isTrue(agendaRepository.findById(agenda.getId()).get().getStatusAgendamento().equals(StAgendamento.pendente), "Assert = false");
            Assert.isTrue(agenda.getStatusAgendamento().equals(StAgendamento.aprovado), "Assert = false");

            agendaRepository.updateStatus(agenda.getStatusAgendamento(), agenda.getId());

            Historico historico = new Historico(
                    agenda,
                    agenda.getObservacao(),
                    agenda.getSecretaria(),
                    agenda.getPaciente(),
                    LocalDateTime.now(),
                    agenda.getStatusAgendamento());
            historicoRepository.save(historico);

            historicoRepository.save(historico);
        }
    }

    public void updateStatusPendente0uAprovadoParaCancelado(Agenda agenda, Secretaria secretaria, Paciente paciente)
    {
        if(secretaria != null || paciente != null)
        {
            if(agendaRepository.getById(agenda.getId()).getStatusAgendamento().equals(StAgendamento.pendente)
                    || agendaRepository.getById(agenda.getId()).getStatusAgendamento().equals(StAgendamento.aprovado))
            {
                agendaRepository.updateStatus(agenda.getStatusAgendamento(), agenda.getId());

                Historico historico = new Historico(
                        agenda,
                        agenda.getObservacao(),
                        agenda.getSecretaria(),
                        agenda.getPaciente(),
                        LocalDateTime.now(),
                        agenda.getStatusAgendamento());

                historicoRepository.save(historico);
            }
        }
    }

    public void updateStatusAprovadoParaCompareceu(Agenda agenda, Secretaria secretaria)
    {
        if(secretaria != null)
        {
            Assert.isTrue(agendaRepository.getById(agenda.getId()).getStatusAgendamento().equals(StAgendamento.aprovado), "Assets = false");
            Assert.isTrue(dataPassada(agenda.getDatade(), agenda.getDataate()), "Assets = false");

            agendaRepository.updateStatus(agenda.getStatusAgendamento(), agenda.getId());

            Historico historico = new Historico(
                    agenda,
                    agenda.getObservacao(),
                    agenda.getSecretaria(),
                    agenda.getPaciente(),
                    LocalDateTime.now(),
                    agenda.getStatusAgendamento());

            historicoRepository.save(historico);

        }
    }

    public void updateStatusAprovadoParaNaoCompareceu(Agenda agenda, Secretaria secretaria)
    {
        if(secretaria != null)
        {
            Assert.isTrue(agendaRepository.getById(agenda.getId()).getStatusAgendamento().equals(StAgendamento.aprovado), "Assets = false");
            Assert.isTrue(dataPassada(agenda.getDatade(), agenda.getDataate()), "Assets = false");

            agendaRepository.updateStatus(agenda.getStatusAgendamento(), agenda.getId());

            Historico historico = new Historico(
                    agenda,
                    agenda.getObservacao(),
                    agenda.getSecretaria(),
                    agenda.getPaciente(),
                    LocalDateTime.now(),
                    agenda.getStatusAgendamento());

            historicoRepository.save(historico);
        }
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

    public void validationUpdate(Agenda agenda)
    {
        if(!agenda.getStEncaixe())
        {
            validacoesPadroes(agenda);
        }else
        {
            Assert.isTrue(horarioValido(agenda.getDatade()), "Assets = false");
            Assert.isTrue(horarioValido(agenda.getDataate()), "Assets = false");
            Assert.isTrue(horariosMedicosEPacientes(agenda), "Assets = false");
        }
    }

    public void validationInsert(Agenda agenda, Secretaria secretaria)
    {
        if(secretaria != null)
        {
            validacoesPadroes(agenda);
            agenda.setStatusAgendamento(StAgendamento.aprovado);
        }else
        {
            validacoesPadroes(agenda);
            agenda.setStatusAgendamento(StAgendamento.pendente);
        }
    }

    private boolean dataValida(LocalDateTime dataDe, LocalDateTime dataAte)
    {
        if(dataDe.isAfter(LocalDateTime.now())
                &&
                dataAte.isAfter(LocalDateTime.now()))
        {
            if(dataDe.isBefore(dataAte))
            {
                return true;
            }
        }
        return false;
    }

    private boolean dataPassada(LocalDateTime dataDe, LocalDateTime dataAte)
    {
        if(dataDe.isBefore(LocalDateTime.now())
                && dataAte.isBefore(LocalDateTime.now()))
        {
            return true;
        }
        return false;
    }

    private boolean horarioValido(LocalDateTime data)
    {
        if(data.getHour() > 8 && data.getHour() < 12
                ||
                data.getHour() > 14 && data.getHour() < 18)
        {
            return true;
        }
        return false;
    }

    private boolean diaValido(LocalDateTime data)
    {
        return !data.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                &&
                !data.getDayOfWeek().equals(DayOfWeek.SUNDAY)
                ? false : true;
    }

    private boolean horariosMedicosEPacientes(Agenda agenda)
    {
        if(agendaRepository.conflitoMedicoPaciente(
                agenda.getDatade(),
                agenda.getDataate(),
                agenda.getMedico().getId(),
                agenda.getPaciente().getId()
        ).size() > 0)
        {
            return true;
        }
        return false;
    }

    private void validacoesPadroes(Agenda agenda)
    {
        Assert.isTrue(!agenda.getStEncaixe(), "Assets = false");
        Assert.isTrue(dataValida(agenda.getDatade(), agenda.getDataate()), "Assets = false");
        Assert.isTrue(horarioValido(agenda.getDatade()), "Assets = false");
        Assert.isTrue(horarioValido(agenda.getDatade()), "Assets = false");
        Assert.isTrue(diaValido(agenda.getDatade()), "Assets = false");
        Assert.isTrue(diaValido(agenda.getDataate()), "Assets = false");
        Assert.isTrue(horariosMedicosEPacientes(agenda), "Assets = false");

    }


}
