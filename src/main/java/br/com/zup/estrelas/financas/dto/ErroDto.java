package br.com.zup.estrelas.financas.dto;

public class ErroDto {

    private String mensagem;

    public ErroDto(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
