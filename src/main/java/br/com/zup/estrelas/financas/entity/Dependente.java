package br.com.zup.estrelas.financas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.zup.estrelas.financas.dto.CriaDependenteDto;
import br.com.zup.estrelas.financas.dto.DependenteDto;
import br.com.zup.estrelas.financas.enums.Parentesco;

@Entity

public class Dependente {

    @Id
    @Column(name = "id_dependente", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDependente;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Parentesco parentesco;

    @Column(nullable = false)
    private Float renda;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false,
            updatable = false)
    @JsonIgnore
    private Usuario usuario;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    public static Dependente fromDto(DependenteDto dependenteDto, Long idUsuario,
            Long idDependente) {
        Dependente dependente = new Dependente();
        dependente.setIdDependente(idDependente);
        dependente.setNome(dependenteDto.getNome());
        dependente.setRenda(dependenteDto.getRenda());
        dependente.setParentesco(dependenteDto.getParentesco());
        dependente.setIdUsuario(idUsuario);
        return dependente;
    }

    public static Dependente fromCriacaoDto(CriaDependenteDto criaDependenteDto, Long idUsuario) {
        Dependente dependente = new Dependente();
        dependente.setNome(criaDependenteDto.getNome());
        dependente.setRenda(criaDependenteDto.getRenda());
        dependente.setParentesco(criaDependenteDto.getParentesco());
        dependente.setIdUsuario(idUsuario);
        return dependente;
    }

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

    public Float getRenda() {
        return renda;
    }

    public boolean setRenda(Float renda) {
        this.renda = renda;
        return true;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

}
