package br.com.zup.estrelas.financas.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.entity.Despesa;
import br.com.zup.estrelas.financas.entity.Usuario;
import br.com.zup.estrelas.financas.enums.TipoDespesa;
import br.com.zup.estrelas.financas.repository.DespesaRepository;
import br.com.zup.estrelas.financas.repository.UsuarioRepository;


@Service
public class DespesaService {

    @Autowired
    DespesaRepository repository;
    @Autowired
    UsuarioRepository usuarioRepository;


    public Despesa insereDespesa(Despesa despesa) {
        Usuario usuario = usuarioRepository.findById(despesa.getIdUsuario()).get();
        for (Despesa despesaUsuario : usuario.getDespesas()) {
            despesaUsuario.getTipoDeDespesa();
            if (despesa.getTipoDeDespesa().equals(despesaUsuario.getTipoDeDespesa())
                    && !(despesa.getTipoDeDespesa().equals(TipoDespesa.OUTRO))) {
                return null;
            }
        }
        return this.repository.save(despesa);
    }

    public Despesa buscaDespesa(Long idDespesa) {

        return repository.findById(idDespesa).get();
    }

    public List<Despesa> listaDespesas() {

        return (List<Despesa>) this.repository.findAll();
    }

    public void deletaDespesa(Long idDespesa) {

        this.repository.deleteById(idDespesa);
    }

    public Despesa atualizaDespesa(Long idDespesa, Despesa despesa) {

        Despesa despesaBanco = repository.findById(idDespesa).get();

        despesaBanco.setValor(despesa.getValor());
        despesaBanco.setVencimento(despesa.getVencimento());

        return this.repository.save(despesaBanco);
    }

    private List<Despesa> despesasAVencer(LocalDate data) {

        YearMonth month = YearMonth.from(data);
        LocalDate inicioData = month.atDay(1);
        LocalDate fimData = month.atEndOfMonth();

        return this.repository.findAllByVencimentoBetween(inicioData, fimData);
    }
}
