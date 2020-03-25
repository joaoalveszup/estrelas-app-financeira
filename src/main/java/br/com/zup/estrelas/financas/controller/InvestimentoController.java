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
import br.com.zup.estrelas.financas.repository.InvestimentoRepository;

@RestController
@RequestMapping("/investimento")
public class InvestimentoController {
    
    @Autowired
    InvestimentoRepository investimentoRepository;
    
    @PostMapping
    public Investimento insere(@RequestBody Investimento investimento) {
        return this.investimentoRepository.save(investimento);
    }
    
    @GetMapping(path = "/{idInvestimento}")
    public Investimento buscaInvestimento(@PathVariable Long idInvestimento) {
        return this.investimentoRepository.findById(idInvestimento).get();
    }
    
    @GetMapping()
    public List<Investimento> buscaInvestimento() {
        return this.investimentoRepository.findAll();
    }
    
    @PutMapping("/{idInvestimento}")
    public Investimento alteraInvestimento(@PathVariable Long idInvestimento, @RequestBody Investimento investimento) {
        Investimento investimentos = investimentoRepository.findById(idInvestimento).get();
        
        investimentos.setDataVencimento(investimento.getDataVencimento());
        investimentos.setValor(investimento.getValor());
        
        return this.investimentoRepository.save(investimentos);
    }
    
    @DeleteMapping("/{idInvestimento}")
    public void deletaInvestimento(@PathVariable Long idInvestimento) {
        this.investimentoRepository.deleteById(idInvestimento);
    }

}
