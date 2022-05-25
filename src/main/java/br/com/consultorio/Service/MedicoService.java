package br.com.consultorio.Service;


import br.com.consultorio.entity.Medico;

import br.com.consultorio.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Optional<Medico> findById(Long id)
    {
        return this.medicoRepository.findById(id);
    }

    //Pageable = todos os elementos retornam com paginação, page size, nr de paginas
    public Page<Medico> listAll(Pageable pageable) {
        return this.medicoRepository.findAll(pageable);
    }

    @Transactional // avisa pro banco que os dados vao ser alterados(manipulação de dados)
    public void update(long id,Medico medico) {
        if(id == medico.getId()) {
            this.medicoRepository.save(medico);
        } else {
            throw new RuntimeException();
        }

    }

    @Transactional
    public void insert( Medico medico) {
        this.medicoRepository.save(medico);
    }

    @Transactional // avisa pro banco que os dados vao ser alterados
    public void updateStatus(Long id, Medico medico){
        if (id == medico.getId()) {
            this.medicoRepository.updateStatus(LocalDateTime.now(), medico.getId());
        }
        else {
            throw new RuntimeException();
        }
    }






}
