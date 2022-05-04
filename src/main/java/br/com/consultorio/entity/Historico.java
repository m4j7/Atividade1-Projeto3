package br.com.consultorio.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "historicos", schema = "public")
public class Historico  extends AbstractEntity{

    @Getter @Setter
    @JoinColumn(name= "id agenda")
    @ManyToOne(fetch = FetchType.EAGER)
    private Agenda agenda;
    @Getter @Setter
    @Column(name = "dsObservacao", nullable = true,length = 100)
    private String dsObservacao;
    @Getter @Setter
    @JoinColumn(name= "id_secretaria")
    @ManyToOne(fetch = FetchType.EAGER)
    private Secretaria secretaria;
    @Getter @Setter
    @JoinColumn(name= "id_paciente")
    @ManyToOne(fetch = FetchType.EAGER)
    private Paciente paciente;
    @Getter @Setter
    @Column(name = "data", nullable = false)
    private LocalDateTime data;
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private StAgendamento stAgendamento;

}
