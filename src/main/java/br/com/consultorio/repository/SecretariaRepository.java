package br.com.consultorio.repository;
import br.com.consultorio.entity.Secretaria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface SecretariaRepository extends JpaRepository<Secretaria, Long > {

    @Modifying
    @Query("UPDATE Secretaria secretaria " +
            "SET secretaria.excluido= : dataExcluido " +
            "WHERE secretaria.id = :secretaria")

    public void updateStatus(
            @Param("dataExcluido") LocalDateTime dataExcluido,
            @Param ("secretaria") long idSecretaria);

    Page<Secretaria> findAll(Pageable pageable);


}
