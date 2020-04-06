package br.com.zup.estrelas.financas.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.financas.dto.AtualizaDespesaDto;
import br.com.zup.estrelas.financas.dto.CriaDespesaDTO;
import br.com.zup.estrelas.financas.dto.DespesaDTO;
import br.com.zup.estrelas.financas.entity.Despesa;
import br.com.zup.estrelas.financas.exception.DespesaOuUsuarioNull;
import br.com.zup.estrelas.financas.service.DespesaService;


@RestController
public class DespesaController {

    @Autowired
    DespesaService despesaService;

    @PostMapping(path = "/usuarios/{idUsuario}/despesas")
    public Despesa insereDespesa(@RequestBody CriaDespesaDTO criaDespesaDto,
            @PathVariable Long idUsuario) throws DespesaOuUsuarioNull {
        return this.despesaService.insereDespesa(criaDespesaDto, idUsuario);
    }

    @GetMapping(path = "/usuarios/{idUsuario}/despesas/{idDespesa}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public DespesaDTO buscaDespesa(@PathVariable Long idUsuario, @PathVariable Long idDespesa) throws DespesaOuUsuarioNull {
        return despesaService.buscaDespesa(idUsuario, idDespesa);
    }

    @GetMapping(path = "/usuarios/{idUsuario}/despesas",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<DespesaDTO> listaDespesas(@PathVariable Long idUsuario) {
        return (List<DespesaDTO>) despesaService.listaDespesas(idUsuario);
    }

    @DeleteMapping(path = "/usuarios/{idUsuario}/despesas/{idDespesa}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deletaDespesa(@PathVariable Long idUsuario, @PathVariable Long idDespesa)
            throws DespesaOuUsuarioNull {
        this.despesaService.deletaDespesa(idUsuario, idDespesa);
    }

    @PutMapping(path = "/usuarios/{idUsuario}/despesas/{idDespesa}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Despesa atualizaDespesa(@PathVariable Long idUsuario, @PathVariable Long idDespesa,
            @RequestBody AtualizaDespesaDto atualizaDespesaDto) throws DespesaOuUsuarioNull {
        return this.despesaService.atualizaDespesa(idUsuario, idDespesa, atualizaDespesaDto);

    }

    @GetMapping(path = "/usuarios/{idUsuario}/despesas/mes-corrente",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Despesa> despesasAvencerNoMes(@PathVariable Long idUsuario) {
        return (List<Despesa>) despesaService.despesasAVencerNoMes(idUsuario);
    }
}
