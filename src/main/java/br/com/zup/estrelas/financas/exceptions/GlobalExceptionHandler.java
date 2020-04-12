package br.com.zup.estrelas.financas.exceptions;

import br.com.zup.estrelas.financas.dto.ErroDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({AvaliacaoRegraDeNegocioExeption.class,
        DependenteException.class,
        DespesaOuUsuarioNullException.class,
        UsuarioOuObjetivoNuloException.class,
        ValidaCampoECaratereException.class})
    public @ResponseBody ErroDto handleErrosDeNegocio(Exception e) {
        return new ErroDto(e.getMessage());
    }

}
