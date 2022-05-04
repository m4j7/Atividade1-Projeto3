package br.com.consultorio.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "especialidades", schema = "public")

public class Especialidade extends AbstractEntity {
    @Getter @Setter
    @Column(name= "nome", nullable = false, length = 50, unique = true)
    private String nmEspecialidade;

}
