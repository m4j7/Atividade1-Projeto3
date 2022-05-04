package br.com.consultorio.repository;

import br.com.consultorio.entity.Convenio;
import br.com.consultorio.entity.Especialidade;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Long> {


    @Modifying
    @Query("UPDATE Convenio convenio " +
            "SET convenio.excluido= : dataExcluido " +
            "WHERE convenio.id = :especialidade")

    public void updateStatus(
            @Param("dataExcluido") LocalDateTime dataExcluido,
            @Param ("convenio") long idConvenio);

    Page<Convenio> findAll(Pageable pageable);
}
