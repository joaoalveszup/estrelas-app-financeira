package br.com.zup.estrelas.financas.dto;

import br.com.zup.estrelas.financas.entity.Avaliacao;

public class CriaAvaliacaoDto {

    private int notaAvaliacao;

    private String comentario;



    public static CriaAvaliacaoDto fromAvaliacao(Avaliacao avaliacao) {
        CriaAvaliacaoDto criaAvaliacaoDto = new CriaAvaliacaoDto();
        criaAvaliacaoDto.setComentario(avaliacao.getComentario());
        criaAvaliacaoDto.setNotaAvaliacao(avaliacao.getNotaAvaliacao());
        return criaAvaliacaoDto;
    }

    public int getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(int notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }



}
