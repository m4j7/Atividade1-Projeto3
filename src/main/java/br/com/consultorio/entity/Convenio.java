package br.com.consultorio.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "convenios", schema = "public")
public class Convenio  extends AbstractEntity{
    @Getter @Setter
    @Column(name= "nome", nullable = false, length = 50)
    private String nmConvenio;
    @Getter @Setter
    @Column(name= "vlConvenio", nullable = false)
    private BigDecimal vlConvenio;

}
