package br.com.consultorio.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agendas",schema = "public")
public class Agenda extends AbstractEntity{

    // EAGER = PEGA TODOS OS CAMPOS DA TABELA PACINETE
    //LAZY = SOMENTE O ID
    @Getter @Setter
    @JoinColumn(name= "id paciente")
    @ManyToOne(fetch = FetchType.EAGER)
    private Paciente paciente;
    @Getter @Setter
    @JoinColumn(name= "id medico")
    @ManyToOne(fetch = FetchType.EAGER)
    private Medico medico;
    @Getter @Setter
    @JoinColumn(name= "id secretaria")
    @ManyToOne(fetch = FetchType.EAGER)
    private Secretaria secretaria;
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "statusAgendamento", nullable = false)
    private StAgendamento statusAgendamento;
    @Getter @Setter
    @Column(name = "datade", nullable = false)
    private LocalDateTime datade;
    @Getter @Setter
    @Column(name = "StEncaixe", columnDefinition = "BOOLEAN DEFAULT TIME", nullable = false)
    private Boolean StEncaixe;
    @Getter @Setter
    @Column(name = "Dataate", nullable = false)
    private LocalDateTime dataate;


}

