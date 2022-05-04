package br.com.consultorio.Service;

import br.com.consultorio.entity.Especialidade;
import br.com.consultorio.entity.Secretaria;
import br.com.consultorio.repository.EspecialidadeRepository;
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

    //Pageable = todos os elementos retornam com paginação, page size, nr de paginas
    public Page<Secretaria> listAll(Pageable pageable) {
        return this.secretariaRepository.findAll(pageable);
    }

    @Transactional // avisa pro banco que os dados vao ser alterados(manipulação de dados)
    public void update(Long id, Secretaria secretaria) {
        if(id == secretaria.getId()) {
            this.secretariaRepository.save(secretaria);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void insert(Long id, Secretaria secretaria) {
        this.secretariaRepository.save(secretaria);
    }

    @Transactional // avisa pro banco que os dados vao ser alterados
    public void updateStatus(Long id, Secretaria secretaria){
        if (id == secretaria.getId()) {
            this.secretariaRepository.updateStatus(LocalDateTime.now(), secretaria.getId());
        }
        else {
            throw new RuntimeException();
        }
    }

}
