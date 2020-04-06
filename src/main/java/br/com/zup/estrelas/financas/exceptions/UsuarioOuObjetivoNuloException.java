package br.com.zup.estrelas.financas.exceptions;

public class UsuarioOuObjetivoNuloException extends Exception {

    private static final long serialVersionUID = 1L;

    public UsuarioOuObjetivoNuloException(String mensagem) {
        super(mensagem);
    }
}
