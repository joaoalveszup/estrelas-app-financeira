package br.com.zup.estrelas.financas.exception;

public class DespesaOuUsuarioNullException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public DespesaOuUsuarioNullException(String mensagem) {
        super(mensagem);
    }

}
