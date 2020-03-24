package br.com.zup.estrelas.financas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dependente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idDependente;
    
    @Column(nullable = false)
    private String nome;
    
    @Column
    private String parentesco;
    
    @Column
    private Float renda;

    public Long getIdDependente() {
        return idDependente;
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
