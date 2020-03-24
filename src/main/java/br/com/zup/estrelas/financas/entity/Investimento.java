package br.com.zup.estrelas.financas.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Investimento {

    @Id
    @Column(name = "id_investimento")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idInvestimento;

    @Column(nullable = false)
    private Long valor;

    @Column(nullable = false, name = "data_vencimento")
    private LocalDate dataVencimento;

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Long getId() {
        return idInvestimento;
    }

}