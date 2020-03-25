package br.com.zup.estrelas.financas.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Objetivo {

    @Id
    @Column(name = "id_objetivo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObjetivo;

    @Column(nullable = false)
    private String nome;


    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable=false, updatable=false)
    @JsonIgnore
    private Usuario usuario;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;
    

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
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Investimento> getInvestimento() {
        return investimento;
    }

    public void setInvestimento(List<Investimento> investimento) {
        this.investimento = investimento;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
