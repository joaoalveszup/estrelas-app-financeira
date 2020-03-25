package br.com.zup.estrelas.financas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.financas.entity.Investimento;
import br.com.zup.estrelas.financas.service.InvestimentoService;

@RestController
@RequestMapping("/investimentos")
public class InvestimentoController {

    @Autowired
    InvestimentoService investimentoService;

    @PostMapping
    public Investimento insere(@RequestBody Investimento investimento) {
        return this.investimentoService.insereInvestimento(investimento);
    }

    @GetMapping(path = "/{idInvestimento}")
    public Investimento buscaInvestimento(@PathVariable Long idInvestimento) {
        return this.investimentoService.buscaInvestimento(idInvestimento);
    }

    @GetMapping()
    public List<Investimento> buscaInvestimento() {
        return this.investimentoService.buscaInvestimentos();
    }

    @PutMapping("/{idInvestimento}")
    public Investimento alteraInvestimento(@PathVariable Long idInvestimento,
            @RequestBody Investimento investimento) {
        return this.investimentoService.atualiza(idInvestimento, investimento);
    }

    @DeleteMapping("/{idInvestimento}")
    public void deletaInvestimento(@PathVariable Long idInvestimento) {
        this.investimentoService.deleteInvestimento(idInvestimento);;
    }

}
