package br.com.zup.estrelas.financas.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Objetivo {

    @Id
    @Column(name = "id_objetivo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idObjetivo;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    private List<Investimento> investimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdObjetivo() {
        return idObjetivo;
    }

    public List<Investimento> getInvestimento() {
        return investimento;
    }

    public void setInvestimento(List<Investimento> investimento) {
        this.investimento = investimento;
    }

}
