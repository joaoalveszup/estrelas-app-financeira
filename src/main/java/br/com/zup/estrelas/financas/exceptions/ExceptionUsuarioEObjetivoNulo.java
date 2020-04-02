package br.com.zup.estrelas.financas.exceptions;

public class ExceptionUsuarioEObjetivoNulo extends Exception{

    public ExceptionUsuarioEObjetivoNulo(String mensagem) {
        super("Usuario ou bojetivo nullo");
    }
}
