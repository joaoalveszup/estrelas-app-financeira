package br.com.zup.estrelas.financas.dto;

import br.com.zup.estrelas.financas.enums.TipoSugestao;

public class SugestaoRequestDto {
    private String titulo;
    private String descricao;
    private TipoSugestao tipoSugestao;
    private Long idSugestao;
  
    
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
