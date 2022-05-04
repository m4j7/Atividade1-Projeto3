package br.com.consultorio.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "cadastro", nullable = false)
    private LocalDateTime cadastro;
    @Column(name = "atualizado")
    private LocalDateTime atualizado;
    @Column(name = "excluido")
    private LocalDateTime excluido;

    @PrePersist
    public void  AtualizarDataCadastro(){
        this.cadastro = LocalDateTime.now();
    }

    @PreUpdate
    public void  DataAtualizado(){
        this.atualizado = LocalDateTime.now();
    }


}
