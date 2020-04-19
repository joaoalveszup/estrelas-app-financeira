package br.com.zup.estrelas.financas.exceptions;

public class ValidaCampoECaratereException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ValidaCampoECaratereException (String mensagem) {
        super(mensagem);
    }
}
