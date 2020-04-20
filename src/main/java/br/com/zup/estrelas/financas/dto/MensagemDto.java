package br.com.zup.estrelas.financas.dto;

public class MensagemDto {

    private String mensagem;

    public MensagemDto(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
