package br.com.zup.estrelas.financas.controller;

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
            path = "usuarios/{idUsuario}/objetivos/{idObjetivo}/investimentos/{idInvestimento}/pagar")
    public Investimento realizaPagamento(@PathVariable Long idUsuario,
            @PathVariable Long idObjetivo, @PathVariable Long idInvestimento)
            throws UsuarioOuObjetivoNuloException {

        return investimentoService.alteraStatusInvestimento(idUsuario, idObjetivo, idInvestimento, true);
    }

    @DeleteMapping(path = "usuarios/{idUsuario}/objetivos/{idObjetivo}/investimentos/{idInvestimento}/pagar")
    public Investimento desfazPagamento(@PathVariable Long idUsuario,
            @PathVariable Long idObjetivo, @PathVariable Long idInvestimento)
            throws UsuarioOuObjetivoNuloException {

        return investimentoService.alteraStatusInvestimento(idUsuario, idObjetivo, idInvestimento, false);
    }
}
