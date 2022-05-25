package br.com.consultorio.Service;
import br.com.consultorio.entity.Secretaria;
import br.com.consultorio.repository.SecretariaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SecretariaService {

    private SecretariaRepository secretariaRepository;

    public Optional<Secretaria> findById(Long id)
    {
        return this.secretariaRepository.findById(id);
    }


    public Page<Secretaria> listAll(Pageable pageable) {
        return this.secretariaRepository.findAll(pageable);
    }

    @Transactional //(manipulação de dados)
    public void update (Secretaria secretaria, Long id) {
        if(id == secretaria.getId()) {
            this.secretariaRepository.save(secretaria);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void insert( Secretaria secretaria) {
        this.secretariaRepository.save(secretaria);
    }

    @Transactional
    public void updateStatus(Secretaria secretaria, Long id){
        if (id == secretaria.getId()) {
            this.secretariaRepository.updateStatus(LocalDateTime.now(), secretaria.getId());
        }
        else {
            throw new RuntimeException();
        }
    }

}
