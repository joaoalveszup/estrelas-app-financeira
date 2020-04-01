package br.com.zup.estrelas.financas.dto;

import br.com.zup.estrelas.financas.entity.Avaliacao;

public class AvaliacaoDto {

    private Long idAvaliacao;

    private String comentario;

    private int notaAvaliacao;

    private String nomeUsuario;     
        
    public static AvaliacaoDto fromAvaliacao(Avaliacao avaliacao) {
        AvaliacaoDto avaliacaoDto = new AvaliacaoDto();
        avaliacaoDto.setIdAvaliacao(avaliacao.getIdAvaliacao());
        avaliacaoDto.setComentario(avaliacao.getComentario());
        avaliacaoDto.setNotaAvaliacao(avaliacao.getNotaAvaliacao());
        avaliacaoDto.setNomeUsuario(avaliacao.getUsuario().getNome());
        return avaliacaoDto;
    }
    

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nome) {
        this.nomeUsuario = nome;
    }

    public Long getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Long idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(int notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }


}
