package br.com.consultorio.repository;

import br.com.consultorio.entity.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long>{

    /**
     *
     * @param idEspecialidade
     */
    @Modifying
    @Query("UPDATE Especialidade especialidade " +
            "SET especialidade.excluido= : dataExcluido " +
            "WHERE especialidade.id = :especialidade")

    public void updateStatus(
     @Param("dataExcluido") LocalDateTime dataExcluido,
     @Param ("especialidade") long idEspecialidade);

    Page<Especialidade> findAll(Pageable pageable);
}
