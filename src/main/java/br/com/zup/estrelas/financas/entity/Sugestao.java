package br.com.zup.estrelas.financas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import br.com.zup.estrelas.financas.enums.TipoSugestao;


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
    @Enumerated(EnumType.STRING)
    private TipoSugestao tipoSugestao;

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

    public TipoSugestao getTipoSugestao() {
        return tipoSugestao;
    }

    public void setTipoSugestao(TipoSugestao tipoSugestao) {
        this.tipoSugestao = tipoSugestao;
    }

    

}
