package br.com.zup.estrelas.financas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Sugestao {


    @Column(name = "id_sugestao")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSugestao;

    @Column( nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "tipo_sugestao", nullable = false)
    private String tipoSugestao;

    public Long getIdSugestao() {
        return idSugestao;
    }

    public void setIdSugestao(Long idSugestao) {
        this.idSugestao = idSugestao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoSugestao() {
        return tipoSugestao;
    }

    public void setTipoSugestao(String tipoSugestao) {
        this.tipoSugestao = tipoSugestao;
    }

    

}
