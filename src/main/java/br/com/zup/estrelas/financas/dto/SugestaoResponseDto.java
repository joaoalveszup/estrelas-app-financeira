package br.com.zup.estrelas.financas.dto;

import br.com.zup.estrelas.financas.entity.Sugestao;
import br.com.zup.estrelas.financas.enums.TipoSugestao;

public class SugestaoResponseDto {
    private String titulo;
    private String descricao;
    private TipoSugestao tipoSugestao;

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

    public void setTiposugestao(TipoSugestao tiposugestao) {
        this.tipoSugestao = tiposugestao;
    }

    public static SugestaoResponseDto fromSugestaoResponseDto(Sugestao sugestao) {
        SugestaoResponseDto sugestaoResponseDto = new SugestaoResponseDto();
        sugestaoResponseDto.setDescricao(sugestao.getDescricao());
        sugestaoResponseDto.setTiposugestao(sugestao.getTipoSugestao());
        sugestaoResponseDto.setTitulo(sugestao.getTitulo());
        return sugestaoResponseDto;

    }

}
