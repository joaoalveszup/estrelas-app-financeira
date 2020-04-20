package br.com.zup.estrelas.financas.controller;

import br.com.zup.estrelas.financas.dto.MensagemDto;
import br.com.zup.estrelas.financas.dto.ObjetivoDto;
import br.com.zup.estrelas.financas.dto.SomaObjetivosMesDto;
import br.com.zup.estrelas.financas.entity.Objetivo;
import br.com.zup.estrelas.financas.exceptions.UsuarioOuObjetivoNuloException;
import br.com.zup.estrelas.financas.service.ObjetivoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ObjetivoController {

    @Autowired
    ObjetivoService objetivoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/usuarios/{idUsuario}/objetivos")
    public Objetivo insereObjetivo(@PathVariable Long idUsuario,
            @RequestBody ObjetivoDto objetivoDto) {
        return this.objetivoService.insereObjetivo(idUsuario, objetivoDto);
    }

    @GetMapping(path = "/usuarios/{idUsuario}/objetivos/{idObjetivo}")
    public ObjetivoDto buscaObjetivo(@PathVariable Long idUsuario, @PathVariable Long idObjetivo)
            throws UsuarioOuObjetivoNuloException {
        return objetivoService.buscaObjetivo(idUsuario, idObjetivo);
    }

    @GetMapping(path = "/usuarios/{idUsuario}/objetivos")
    public List<ObjetivoDto> listaObjetivos(@PathVariable Long idUsuario) {
        return objetivoService.listaObjetivos(idUsuario);
    }


    @GetMapping(path = "/usuarios/{idUsuario}/objetivos/mes-corrente")
    public SomaObjetivosMesDto recuperaValorMensalObjetivos(@PathVariable Long idUsuario) {
        return objetivoService.recuperaValorMensalObjetivos(idUsuario);
    }

    @PutMapping(path = "usuarios/{idUsuario}/objetivos/{idObjetivo}")
    public Objetivo atualizaObjetivo(@PathVariable Long idUsuario, @PathVariable Long idObjetivo,
            @RequestBody ObjetivoDto objetivoDto) throws UsuarioOuObjetivoNuloException {
        return this.objetivoService.atualizarObjetivo(idUsuario, idObjetivo, objetivoDto);
    }

    @DeleteMapping("/usuarios/{idUsuario}/objetivos/{idObjetivo}")
    public MensagemDto deletaObjetivo(@PathVariable Long idUsuario, @PathVariable Long idObjetivo)
            throws UsuarioOuObjetivoNuloException {
        return this.objetivoService.deletaObjetivo(idUsuario, idObjetivo);
    }


}
