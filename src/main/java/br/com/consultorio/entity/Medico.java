package br.com.consultorio.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "medicos", schema = "public")
public class Medico extends Pessoa{
    @Getter @Setter
    @Column(name= "nrCrm", nullable = false, length = 50, unique = true)
    private String nrCrm;
    @Getter @Setter
    @Digits(integer = 5, fraction = 3)
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;
    @Getter @Setter
    @Digits(integer = 5, fraction = 3)
    @Column(name = "porcenParticipacao", nullable = false)
    private BigDecimal porcenParticipacao;
    @Getter @Setter
    @Column(name = "consultorio", nullable = false,length = 50)
    private String consultorio;
    @Getter @Setter
    @JoinColumn(name= "id especialidade")
    @ManyToOne(fetch = FetchType.EAGER)
    private Especialidade nmEspecialidade;


}
