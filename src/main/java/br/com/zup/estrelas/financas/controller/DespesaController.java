package br.com.zup.estrelas.financas.controller;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.financas.dto.AtualizaDespesaDto;
import br.com.zup.estrelas.financas.dto.CriaDespesaDTO;
import br.com.zup.estrelas.financas.dto.DespesaDTO;
import br.com.zup.estrelas.financas.entity.Despesa;
import br.com.zup.estrelas.financas.enums.TipoDespesa;
import br.com.zup.estrelas.financas.exceptions.DespesaOuUsuarioNullException;
import br.com.zup.estrelas.financas.service.DespesaService;


@RestController
public class DespesaController {

    @Autowired
    DespesaService despesaService;

    @PostMapping(path = "/usuarios/{idUsuario}/despesas")
    public Despesa insereDespesa(@RequestBody CriaDespesaDTO criaDespesaDto,
            @PathVariable Long idUsuario) throws DespesaOuUsuarioNullException {
        return this.despesaService.insereDespesa(criaDespesaDto, idUsuario);
    }

    @GetMapping(path = "/usuarios/{idUsuario}/despesas/{idDespesa}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public DespesaDTO buscaDespesa(@PathVariable Long idUsuario, @PathVariable Long idDespesa)
            throws DespesaOuUsuarioNullException {
        return despesaService.buscaDespesa(idUsuario, idDespesa);
    }

    @DeleteMapping(path = "/usuarios/{idUsuario}/despesas/{idDespesa}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deletaDespesa(@PathVariable Long idUsuario, @PathVariable Long idDespesa)
            throws DespesaOuUsuarioNullException {
        this.despesaService.deletaDespesa(idUsuario, idDespesa);
    }

    @PutMapping(path = "/usuarios/{idUsuario}/despesas/{idDespesa}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Despesa atualizaDespesa(@PathVariable Long idUsuario, @PathVariable Long idDespesa,
            @RequestBody AtualizaDespesaDto atualizaDespesaDto)
            throws DespesaOuUsuarioNullException {
        return this.despesaService.atualizaDespesa(idUsuario, idDespesa, atualizaDespesaDto);

    }

    @GetMapping(path = "/usuarios/{idUsuario}/despesas/mes-corrente",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Despesa> despesasAvencerNoMes(@PathVariable Long idUsuario) {
        return (List<Despesa>) despesaService.despesasAVencerNoMes(idUsuario);
    }

    @GetMapping(path = "/usuarios/{idUsuario}/despesas")
    public List<DespesaDTO> buscaPorTipoDespesa(@PathVariable Long idUsuario,
            @RequestParam(value = "tipo-de-despesa") Optional<TipoDespesa> tipoDeDespesa) {
        return despesaService.buscaPorTipoDespesa(idUsuario, tipoDeDespesa);
    }
}
