package br.com.zup.estrelas.financas.dto;

import br.com.zup.estrelas.financas.entity.Sugestao;
import br.com.zup.estrelas.financas.enums.TipoSugestao;

public class SugestaoRequestDto {
    private String titulo;
    private String descricao;
    private TipoSugestao tipoSugestao;

    public static SugestaoRequestDto fromSugestao(Sugestao sugestao) {
        SugestaoRequestDto sugestaoRequestDto = new SugestaoRequestDto();
        sugestaoRequestDto.setTitulo(sugestao.getTitulo());
        sugestaoRequestDto.setDescricao(sugestao.getDescricao());
        sugestaoRequestDto.setTipoSugestao(sugestao.getTipoSugestao());
       
        return sugestaoRequestDto;
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
