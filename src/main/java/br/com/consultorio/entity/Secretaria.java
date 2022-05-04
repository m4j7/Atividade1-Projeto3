package br.com.consultorio.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "secretarias", schema = "public")

public class Secretaria extends Pessoa{
    @Getter
    @Setter
    @Column(name = "nrPis", nullable = false, unique = true)
    private String nrPis;
    @Getter
    @Setter
    @Column(name = "dtContratacao", nullable = false)
    private LocalDateTime dtContratacao;
    @Getter
    @Setter
    @Column(name = "salario", nullable = false)
    private BigDecimal salario;


}
