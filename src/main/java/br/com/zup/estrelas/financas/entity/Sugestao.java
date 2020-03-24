package br.com.zup.estrelas.financas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sugestao {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long idSugestao;
    @Column
    private String descricao;
    @Column
    private TipoSugestao tipoSugestao;
    public long getIdSugestao() {
        return idSugestao;
    }
    public void setIdSugestao(long idSugestao) {
        this.idSugestao = idSugestao;
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
