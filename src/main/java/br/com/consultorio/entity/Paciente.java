package br.com.consultorio.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pacientes", schema = "public")
public class Paciente extends Pessoa {
    @Getter @Setter
    @ManyToOne
    private Convenio convenio;
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private TpAtendimento tpAtendimento;
    @Getter @Setter
    @Column(name= "dtVencimentoConv", nullable = false, length = 20)
    private LocalDateTime dtVencimentoConv;
    @Getter @Setter
    @Column(name= "nrCartaoConv", nullable = false, length = 50)
    private String nrCartaoConv;



}

