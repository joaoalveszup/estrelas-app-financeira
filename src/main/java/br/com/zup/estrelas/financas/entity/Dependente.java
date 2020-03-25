package br.com.zup.estrelas.financas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dependente {

    @Id
    @Column(name = "id_dependente", nullable = false)
    private Long idDependente;
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String parentesco;

    @Column(nullable = false)
    private Float renda;

    public Long getIdDependente() {
        return idDependente;
    }

    public void setIdDependente(Long idDependente) {
        this.idDependente = idDependente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Float getRenda() {
        return renda;
    }

    public void setRenda(Float renda) {
        this.renda = renda;
    }


}
