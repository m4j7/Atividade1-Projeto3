package br.com.consultorio.Service;

import br.com.consultorio.entity.Especialidade;
import br.com.consultorio.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EspecialidadeService {

    @Autowired//injeçao de dependencias
    private EspecialidadeRepository especialidadeRepository;

    //Optional = Objeto
    public Optional<Especialidade> findById(Long id)
    {
        return this.especialidadeRepository.findById(id);
    }

    //Pageable = todos os elementos retornam com paginação, page size, nr de paginas
    public Page<Especialidade> listAll(Pageable pageable) {
        return this.especialidadeRepository.findAll(pageable);
    }

    @Transactional // avisa pro banco que os dados vao ser alterados(manipulação de dados)
    public void update(Long id, Especialidade especialidade) {
        if(id == especialidade.getId()) {
            this.especialidadeRepository.save(especialidade);
        } else {
            throw new RuntimeException();
        }

    }

    @Transactional
    public void insert( Especialidade especialidade) {
        this.especialidadeRepository.save(especialidade);
    }

    @Transactional // avisa pro banco que os dados vao ser alterados
    public void updateStatus(Long id, Especialidade especialidade){
        if (id == especialidade.getId()) {
            this.especialidadeRepository.updateStatus(LocalDateTime.now(), especialidade.getId());
        }
        else {
            throw new RuntimeException();
        }
    }


}
