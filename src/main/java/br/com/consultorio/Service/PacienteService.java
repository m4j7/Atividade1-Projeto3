package br.com.consultorio.Service;

import br.com.consultorio.entity.Medico;
import br.com.consultorio.entity.Paciente;
import br.com.consultorio.entity.TpAtendimento;
import br.com.consultorio.repository.PacienteRepository;
import br.com.consultorio.repository.SecretariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void insert(Paciente paciente){
       ValidarFormurio(paciente);
       insertTransaction(paciente);
    }

    @Transactional
    public void insertTransaction (Paciente paciente){
        this.pacienteRepository.save(paciente);
    }

    public void ValidarFormurio(Paciente paciente) {



        if (paciente.getTpAtendimento().equals(TpAtendimento.convenio)){

            if(paciente.getConvenio() == null || paciente.getConvenio().getId() == null){
                throw  new RuntimeException(("Convenio nao informado"));
            }
            if( paciente.getConvenio().getId() == null){
                throw new RuntimeException("Tipo Atendimento = Convenio. convenio não informado");
            }
            if(paciente.getNrCartaoConv() == null ){
                throw new RuntimeException(("Tipo Atendimento = Cartao do convenio não informado "));
            }

            if(paciente.getDtVencimentoConv() == null ){
                throw new RuntimeException(("Tipo Atendimento = Convenio. Data vencimento do cartão não informada"));
            }

            if(paciente.getDtVencimentoConv().compareTo(LocalDateTime.now()) > 0){
                throw new RuntimeException(("Tipo Atendimento = Convenio. Data vencimento do cartão esta expirada"));
            }
        }

        if(paciente.getTpAtendimento().equals(TpAtendimento.plano)){
            paciente.setConvenio(null);
            paciente.setNrCartaoConv(null);
            paciente.setDtVencimentoConv(null);
        }

    }

    @Transactional
    public void updateStatus (Long id, Paciente paciente){
        if (id == paciente.getId()){
            this.pacienteRepository.UpdateDataExcluido(LocalDateTime.now(), paciente.getId() );
        }
    }

    public Optional<Paciente> findById(Long id)
    {
        return this.pacienteRepository.findById(id);
    }

    public Page<Paciente> listAll(Pageable pageable) {
        return this.pacienteRepository.findAll(pageable);
    }



}
