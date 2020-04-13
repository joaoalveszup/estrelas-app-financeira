package br.com.zup.estrelas.financas.exceptions;

import br.com.zup.estrelas.financas.dto.ErroDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final int STR_NOME_DO_CAMPO = 0;
    private static final int IGNORA_POS_PONTO = 1;

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({AvaliacaoRegraDeNegocioExeption.class,
        DependenteException.class,
        DespesaOuUsuarioNullException.class,
        UsuarioOuObjetivoNuloException.class,
        ValidaCampoECaratereException.class})
    public @ResponseBody ErroDto handleErrosDeNegocio(Exception e) {
        return new ErroDto(e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody List<ErroDto> handleValidationError(MethodArgumentNotValidException e) {

        List<ErroDto> errosDeValidacao = new ArrayList<>();

        for (ObjectError erro : e.getBindingResult().getAllErrors()) {
            String baseStr = erro.getCodes()[STR_NOME_DO_CAMPO];
            StringBuilder mensagemASerRetornada = new StringBuilder();

            mensagemASerRetornada.append("O campo ");
            mensagemASerRetornada.append(baseStr.substring(baseStr.lastIndexOf(".") + IGNORA_POS_PONTO));
            mensagemASerRetornada.append(" ");
            mensagemASerRetornada.append(erro.getDefaultMessage());
            errosDeValidacao.add(new ErroDto(mensagemASerRetornada.toString()));
        }

        return errosDeValidacao;
    }


}
