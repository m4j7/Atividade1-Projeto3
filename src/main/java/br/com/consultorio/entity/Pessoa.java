package br.com.consultorio.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
abstract class Pessoa extends AbstractEntity {
    @Getter @Setter
    @Column(name = "nmPessoa", nullable = false, length = 50)
    private String nmPessoa;
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = true,length = 50)
    private Sexo sexo;
    @Getter @Setter
    @Column(name = "nrRg", nullable = false,length = 15, unique = true)
    private String nrRg;
    @Getter @Setter
    @Column(name = "nrCpf", nullable = false,length = 13, unique = true)
    private String nrCpf;
    @Getter @Setter
    @Column(name = "rua",length = 50, nullable = false)
    private String rua;
    @Getter @Setter
    @Column(name = "login",length = 50, nullable = false)
    private String login;
    @Getter @Setter
    @Column(name = "senha",length = 50, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    @Getter @Setter
    @Column(name = "nrTelefone", length = 50, nullable = false, unique = true)
    private String nrTelefone;
    @Getter @Setter
    @Column(name = "email",length = 50, nullable = false)
    private String email;
    @Getter @Setter
    @Column(name = "stPessoa", columnDefinition =  "BOOLEAN DEFAULT TIME" , nullable = false)
    private Boolean stPessoa;

}
