package br.com.zup.estrelas.financas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.financas.entity.Investimento;
import br.com.zup.estrelas.financas.exceptions.UsuarioOuObjetivoNuloException;
import br.com.zup.estrelas.financas.service.InvestimentoService;

@RestController
@RequestMapping
public class InvestimentoController {

    @Autowired
    InvestimentoService investimentoService;

    @PutMapping(
            path = "usuarios/{idUsuario}/objetivos/{idObjetivo}/investimentos/{idInvestimento}/statusParcela")
    public List<Investimento> atualizaInvestimentoParaTrue(@PathVariable Long idUsuario,
            @PathVariable Long idObjetivo, @PathVariable Long idInvestimento)
            throws UsuarioOuObjetivoNuloException {

        return investimentoService.alteraStatusParcela(idUsuario, idObjetivo, idInvestimento, true);
    }

    @DeleteMapping(path = "usuarios/{idUsuario}/objetivos/{idObjetivo}/investimentos/{idInvestimento}/statusParcela")
    public List<Investimento> atualizaInvestimentoParaFalse(@PathVariable Long idUsuario,
            @PathVariable Long idObjetivo, @PathVariable Long idInvestimento)
            throws UsuarioOuObjetivoNuloException {

        return investimentoService.alteraStatusParcela(idUsuario, idObjetivo, idInvestimento, false);
    }
}
